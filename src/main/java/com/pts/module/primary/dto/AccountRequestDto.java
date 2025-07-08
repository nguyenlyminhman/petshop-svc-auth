package com.pts.module.primary.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountRequestDto {
    private String username;
    private String password;
    private String fullname;
    private String email;
}
