package com.demo.service;

import com.demo.dto.RoleDTO;
import com.demo.dto.UserDTO;
import com.demo.exception.NotFoundException;
import com.demo.exception.RegistrationNotApprovedException;
import com.demo.model.Privilege;
import com.demo.model.Role;
import com.demo.model.User;
import com.demo.model.UserTokenState;
import com.demo.repository.PrivilegeRepository;
import com.demo.repository.RoleRepository;
import com.demo.repository.UserRepository;
import com.demo.security.JwtAuthenticationRequest;
import com.demo.security.TokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.*;

@Service
@Slf4j
public class CustomUserDetailsService implements UserDetailsService{

    private TokenUtils tokenUtils;
    private AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private RoleRepository roleRepository;
    private ModelMapper modelMapper;
    private PrivilegeRepository privilegeRepository;

    @Autowired
    public CustomUserDetailsService(TokenUtils tokenUtils, AuthenticationManager authenticationManager,
                                    UserRepository userRepository, PasswordEncoder passwordEncoder,
                                    RoleRepository roleRepository, ModelMapper modelMapper,
                                    PrivilegeRepository privilegeRepository) {
        this.tokenUtils = tokenUtils;
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
        this.privilegeRepository = privilegeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        log.info("Custom user repository");
        User user = userRepository.findByUsername(s);
        if(user != null)
            return user;
        else throw new UsernameNotFoundException(String.format("no user found with username '%s'", s));
    }

    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        String username = currentUser.getName();

        if (authenticationManager != null) {
            log.debug("Re-authenticating user '" + username + "' for password change request.");
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        } else {
            log.debug("No authentication manager set. can't change Password!");
            return;
        }

        log.debug("Changing password for user '" + username + "'");

        User user = (User) loadUserByUsername(username);


        user.setPassword(passwordEncoder.encode(newPassword));
        user.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        userRepository.save(user);
    }

    public ResponseEntity<?> register(UserDTO userDTO) {
        log.info("User service - registration function");
        User username = this.userRepository.findByUsername(userDTO.getUsername());
        if(username != null){
            return new ResponseEntity<>("Username already in use",HttpStatus.BAD_REQUEST);
        }

        User user = User.builder()
                .name(userDTO.getName())
                .surname(userDTO.getSurname())
                .username(userDTO.getUsername())
                .email(userDTO.getEmail())
                .address(userDTO.getAddress())
                .phone(userDTO.getPhone())
                .password(passwordEncoder.encode(userDTO.getPassword()))
                .isAdmin(false)
                .enabled(true)
                .deleted(false)
                .imeKompanije("")
                .poslovniID("")
                .lastPasswordResetDate(new Timestamp(System.currentTimeMillis()))
                .build();

        user.setPrivileges(new ArrayList<>());
        if(userDTO.getRoles().get(0).equals("ROLE_AGENT")){
            user.setImeKompanije(userDTO.getImeKompanije());
            user.setPoslovniID(userDTO.getPoslovniID());
        }else if(userDTO.getRoles().get(0).equals("ROLE_ADMIN")){
            user.setAdmin(true);
        }else if(userDTO.getRoles().get(0).equals("ROLE_USER")){
            user.setEnabled(false);
            user.setLastPasswordResetDate(null);
        }

        Role role = this.roleRepository.findByName(userDTO.getRoles().get(0));
        user.setRoles(new ArrayList<Role>());
        user.getRoles().add(role);
        userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    public ResponseEntity<?> login(JwtAuthenticationRequest jwtAuthenticationRequest, HttpServletResponse httpServletResponse) throws AuthenticationException, IOException {
        log.info("User service - login function reached");
        final Authentication authentication =
                authenticationManager.authenticate
                        (new UsernamePasswordAuthenticationToken(
                                jwtAuthenticationRequest.getUsername(),
                                jwtAuthenticationRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = (User) authentication.getPrincipal();
        if (user.getLastPasswordResetDate() == null) {
            throw new RegistrationNotApprovedException("Your registration request is not yet approved. Please be patient we are working on it");
        }

        String jwt = tokenUtils.generateToken(user.getUsername());
        int expiresIn = tokenUtils.getExpiredIn();
        String refresh = tokenUtils.generateRefreshToken(user.getUsername());
        String username = user.getUsername();
        String role = user.getRoles().iterator().next().getName();
        Long id = user.getId();

        return ResponseEntity.ok(new UserTokenState(jwt, expiresIn, username, refresh, role, id));
    }

    private User getUserById(Long userId) {
        return this.userRepository.findById(userId).orElseThrow(() -> new NotFoundException("User with given id was not found."));
    }

    public ResponseEntity<?> verify(String token) {
        log.info("Custom user details service - verify token");

        String username = tokenUtils.getUsernameFromToken(token);;
        log.info("User from token - " + username);
        User userDetails = (User) loadUserByUsername(username);;

        boolean isValid = false;
        if (token != null) {
            isValid = this.tokenUtils.validateToken(token, userDetails);
        }
        if (!isValid) {
            return new ResponseEntity<>(false, HttpStatus.UNAUTHORIZED);
        }
        // Add roles and permissions
        RoleDTO roleDTO = new RoleDTO();
        roleDTO.setUsername(username);
        if (userDetails != null) {
            roleDTO.setRoles(new HashSet<>());
            for (GrantedAuthority role : userDetails.getAuthorities()) {
                roleDTO.getRoles().add(role.getAuthority());
//                for (Privilege privilege : role.getPrivileges())
//                    roleDTO.getPrivileges().add(privilege.getName());
            }
        }

        return new ResponseEntity<>(roleDTO, HttpStatus.OK);
    }

    private Collection<? extends GrantedAuthority> getAuthorities(
            User user) {

        return getGrantedAuthorities(getPrivileges(user.getPrivileges()));
    }

    private List<String> getPrivileges(Collection<Privilege> privilegess) {

        List<String> privileges = new ArrayList<>();
        for (Privilege item : privilegess) {
            privileges.add(item.getName());
        }
        return privileges;
    }

    private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }


    public User findByUsername(String username) {
        return this.userRepository.findByUsername(username);
    }

    public ResponseEntity<?> getUser(String username) {
        User user = this.findByUsername(username);
        UserDTO userDTO = modelMapper.map(user,UserDTO.class);
        return  new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    public User getLoggedInUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return this.findByUsername(username);
    }

    public ResponseEntity<?> getUsers() {
        List<User> users = this.userRepository.findAllByDeleted(false);
        List<UserDTO> userDTOS = new ArrayList<>();
        for(User u: users){
            if (u.getRoles().iterator().next().getName().equals("ROLE_USER") && u.getLastPasswordResetDate() != null){
                UserDTO userDTO = modelMapper.map(u, UserDTO.class);
                userDTO.setRoles(new ArrayList<>());
                for(Role r: u.getRoles()){
                    userDTO.getRoles().add(r.getName());
                }
                for(Privilege p: u.getPrivileges()){
                    userDTO.getRoles().add(p.getName());
                }
                userDTOS.add(userDTO);
            }
        }
        return new ResponseEntity<>(userDTOS, HttpStatus.OK);
    }

    public ResponseEntity<?> getRequests() {
        List<User> requests = this.userRepository.findByLastPasswordResetDate(null);
        if (requests.size() == 0)
            return new ResponseEntity<>(HttpStatus.OK);

        List<UserDTO> users = new ArrayList<>();
        for (User user: requests) {
                users.add(modelMapper.map(user, UserDTO.class));
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    public ResponseEntity<?> accept(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with given  id was not found"));
        user.setLastPasswordResetDate(new Timestamp(System.currentTimeMillis()));
        user.setEnabled(true);
        this.userRepository.save(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<?> reject(Long id) {
        User user = this.userRepository.findById(id).orElseThrow(() -> new NotFoundException("User with given  id was not found"));
        this.userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    public void enable(Long userId) {
        User user = getUserById(userId);
        user.setEnabled(true);
        this.userRepository.save(user);
    }

    public void disable(Long userId) {
        User user = getUserById(userId);
        user.setEnabled(false);
        this.userRepository.save(user);
    }

    public void delete(Long userId) {
        User user  = this.userRepository.getOne(userId);
        user.setEnabled(false);
        user.setDeleted(true);
        this.userRepository.save(user);
    }
}
