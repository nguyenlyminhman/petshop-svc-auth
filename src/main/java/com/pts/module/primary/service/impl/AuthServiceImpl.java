package com.pts.module.primary.service.impl;

import com.pts.adm.security.JwtService;
import com.pts.common.AbstractService;
import com.pts.entity.primary.AccountEntity;
import com.pts.module.primary.dto.LoginRequestDto;
import com.pts.module.primary.dto.TokenRequestDto;
import com.pts.module.primary.model.AccountModel;
import com.pts.module.primary.model.AuthResponseModel;
import com.pts.module.primary.repository.AccountRepository;
import com.pts.module.primary.service.IAuthService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AuthServiceImpl extends AbstractService implements IAuthService {

    private ModelMapper modelMapper;
    private AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;


    @Override
    public AuthResponseModel loginHandle(LoginRequestDto dto) {
        AccountModel accountModel = new AccountModel();
        AuthResponseModel authResponseModel =  new AuthResponseModel();
        try {
            AccountEntity accountEntity = accountRepository.findOneByUsername(dto.getUsername());
            if (accountEntity == null)
                throw new BadCredentialsException("Invalid account");

            if (!passwordEncoder.matches(dto.getPassword(), accountEntity.getPassword())) {
                throw new BadCredentialsException("Invalid account");
            }

            accountModel = modelMapper.map(accountEntity, AccountModel.class);
            String token = jwtService.generateToken(accountModel);

            authResponseModel.setAccountId(accountEntity.getUsername());
            authResponseModel.setFullName(accountModel.getFullname());
            authResponseModel.setAccessToken(token);

            return authResponseModel;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public AccountModel verifyHandle(TokenRequestDto dto) {
        AccountModel accountModel = new AccountModel();
        try {
            Boolean isValidToken = jwtService.isTokenValid(dto.getToken());
            if (!isValidToken) {
                throw new BadCredentialsException("Invalid account");
            }

            String username = jwtService.extractUsername(dto.getToken());
            AccountEntity accountEntity = accountRepository.findOneByUsername(username);
            accountModel = modelMapper.map(accountEntity, AccountModel.class);

            return accountModel;
        } catch (RuntimeException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
