package com.socialnetwork.sg.model.dto;

import com.socialnetwork.sg.model.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role = Role.USER;
    private Boolean isRepresentative = false;
    private Boolean isGroup = false;
}
