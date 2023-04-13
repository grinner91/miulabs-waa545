package com.miu.lab2.entity.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
public class UserDto {
    long id;
    private String email;
    private String firstname;
    private String lastname;
}
