package com.pts.module.primary.controller;

import com.pts.common.ResponseObject;
import com.pts.module.primary.dto.AccountRequestDto;
import com.pts.module.primary.model.AccountModel;
import com.pts.module.primary.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.v1}/account")
public class AccountController {

    @Autowired
    private IAccountService iAccountService;

    @RequestMapping(value = "/create", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseObject<AccountModel> findAllSpecs(@RequestBody AccountRequestDto dto) {
        try {
            AccountModel rs = iAccountService.saveAccount(dto);
            return ResponseObject.ok(rs) ;
        } catch (Exception e) {
            return ResponseObject.error(400, "Error while fetching data");
        }
    }


    @RequestMapping(value = "/info", produces = "application/json;charset=utf-8", method = RequestMethod.GET)
    public ResponseObject<AccountModel> findAllSpecs(@RequestParam("username") String username) {
        try {
            AccountModel rs = iAccountService.getAccountByUsername(username);
            return ResponseObject.ok(rs) ;
        } catch (Exception e) {
            return ResponseObject.error(400, "Error while fetching data");
        }
    }

}
