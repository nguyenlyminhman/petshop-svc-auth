package com.pts.module.primary.service.impl;

import com.pts.common.AbstractService;
import com.pts.entity.primary.AccountEntity;
import com.pts.module.primary.dto.AccountRequestDto;
import com.pts.module.primary.model.AccountModel;
import com.pts.module.primary.repository.AccountRepository;
import com.pts.module.primary.service.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AccountServiceImpl extends AbstractService implements IAccountService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private final PasswordEncoder passwordEncoder;

    public AccountServiceImpl(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public AccountModel saveAccount(AccountRequestDto dto) throws Exception {
        AccountEntity entity = new AccountEntity();
        AccountModel responseModel = new AccountModel();
        try {
            entity = modelMapper.map(dto, AccountEntity.class);
            entity.setPassword(passwordEncoder.encode(dto.getPassword()));
            entity.setCreatedAt(LocalDateTime.now());
            entity.setCreatedBy("SA");
            entity = accountRepository.save(entity);

            responseModel = modelMapper.map(entity, AccountModel.class);
            return responseModel;
        } catch (Exception e) {
            throw new Exception("Error when creating new account");
        }
    }

    @Override
    public AccountModel getAccountByUsername(String username) throws Exception {
        AccountModel responseModel = new AccountModel();
        try {
            AccountEntity entity = accountRepository.findOneByUsername(username);
            responseModel = modelMapper.map(entity, AccountModel.class);

            return responseModel;
        } catch (Exception e) {
            throw new Exception("Error when fetching account");
        }
    }
}
