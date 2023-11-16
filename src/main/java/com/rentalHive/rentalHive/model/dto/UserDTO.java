package com.rentalHive.rentalHive.model.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private long userId;
    private String name;
    private String email;
    private String telephone;
    private int role;
}
