package xml.team7.userservice.dto;

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

}