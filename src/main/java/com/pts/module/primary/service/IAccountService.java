package com.pts.module.primary.service;

import com.pts.module.primary.dto.AccountRequestDto;
import com.pts.module.primary.model.AccountModel;

public interface IAccountService {

    AccountModel saveAccount(AccountRequestDto entity) throws Exception;

    AccountModel getAccountByUsername(String username) throws Exception;

}
