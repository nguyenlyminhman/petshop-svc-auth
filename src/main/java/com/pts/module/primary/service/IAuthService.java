package com.pts.module.primary.service;

import com.pts.module.primary.dto.LoginRequestDto;
import com.pts.module.primary.dto.TokenRequestDto;
import com.pts.module.primary.model.AccountModel;
import com.pts.module.primary.model.AuthResponseModel;

public interface IAuthService {
    AuthResponseModel loginHandle(LoginRequestDto dto);
    AccountModel verifyHandle(TokenRequestDto dto);
}
