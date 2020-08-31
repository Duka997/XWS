package xml.team7.userservice.model;

import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.InheritanceType.JOINED;

@Entity
@Inheritance(strategy=JOINED)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private boolean enabled;

    @Column
    private boolean isAdmin;

    @Column
    private String phone;

    @Column
    private String poslovniID;

    @Column
    private String imeKompanije;

    @Column
    private String address;

    @Column
    private String email;

    @Column
    private Timestamp lastPasswordResetDate;

    @Column
    private Integer numCancelled;

    @Column
    private boolean deleted;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "users_privileges",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "privilege_id", referencedColumnName = "id"))
    private Collection<Privilege> privileges;

    @Override
    public Collection<Role> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
}