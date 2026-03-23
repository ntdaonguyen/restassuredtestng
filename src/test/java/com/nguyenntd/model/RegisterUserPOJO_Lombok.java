package com.nguyenntd.model;

import lombok.*;

@Data // Bao gồm Getter và Setter
// @Getter
// @Setter
@AllArgsConstructor
@NoArgsConstructor
// @RequiredArgsConstructor
@Builder
public class RegisterUserPOJO_Lombok {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private int userStatus;
}
