package homeWorkEight.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateUser {
    private String firstName;
    private String lastName;
    private String password;
    private int userStatus;
    private String phone;
    private int id;
    private String email;
    private String username;
}
