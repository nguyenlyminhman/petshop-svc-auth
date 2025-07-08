package com.pts.module.primary.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseModel {
    private String accountId;
    private String fullName;
    private String accessToken;
}
