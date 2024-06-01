package com.socialnetwork.sg.model.dto;

import com.socialnetwork.sg.model.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {


    private Integer userId;
    private String userName;
    private String firstName;
    private String lastName;
    private String contactNo;
//    @JsonIgnoreProperties("privileges")
    private Collection<Role> roles;
}
