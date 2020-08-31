package com.demo.dto;

import com.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String name;
    private String surname;
    private String username;
    private String password;
    private String phone;
    private boolean isAdmin;
    private boolean enabled;
    private boolean deleted;
    private String address;
    private String imeKompanije;
    private String poslovniID;
    private Timestamp lastPasswordResetDate;
    private List<String> roles;
    private String email;
    private Integer numCancelled;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.surname = user.getSurname();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.phone = user.getPhone();
        this.isAdmin = user.isAdmin();
        this.enabled = user.isEnabled();
        this.deleted = user.isDeleted();
        this.address = user.getAddress();
        this.imeKompanije = user.getImeKompanije();
        this.poslovniID = user.getPoslovniID();
        this.lastPasswordResetDate = user.getLastPasswordResetDate();
        this.email = user.getEmail();
    }
}