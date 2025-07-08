package com.pts.module.primary.controller;

import com.pts.common.ResponseObject;
import com.pts.module.primary.dto.LoginRequestDto;
import com.pts.module.primary.dto.TokenRequestDto;
import com.pts.module.primary.model.AccountModel;
import com.pts.module.primary.model.AuthResponseModel;
import com.pts.module.primary.service.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.v1}/auth")
public class AuthController {

    @Autowired
    private IAuthService iAuthService;

    @RequestMapping(value = "/login", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseObject<AuthResponseModel> login(@RequestBody LoginRequestDto dto) {
        try {
            AuthResponseModel rs = iAuthService.loginHandle(dto);
            return ResponseObject.ok(rs) ;
        } catch (Exception e) {
            return ResponseObject.error(400, "Error while fetching data");
        }
    }

    @RequestMapping(value = "/logout", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseObject<AuthResponseModel> logout(@RequestBody TokenRequestDto dto) {
        try {
//            AuthResponseModel rs = iUltramanService.searchBySpec(dto);
            return ResponseObject.ok(null) ;
        } catch (Exception e) {
            return ResponseObject.error(400, "Error while fetching data");
        }
    }

    @RequestMapping(value = "/verify", produces = "application/json;charset=utf-8", method = RequestMethod.POST)
    public ResponseObject<AccountModel> verify(@RequestBody TokenRequestDto dto) {
        try {
            AccountModel rs = iAuthService.verifyHandle(dto);
            return ResponseObject.ok(rs) ;
        } catch (Exception e) {
            return ResponseObject.error(400, "Error while fetching data");
        }
    }
}
