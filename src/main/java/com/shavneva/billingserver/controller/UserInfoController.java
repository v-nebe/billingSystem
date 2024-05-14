package com.shavneva.billingserver.controller;

import com.shavneva.billingserver.converter.impl.AccountMapper;
import com.shavneva.billingserver.converter.impl.TariffMapper;
import com.shavneva.billingserver.converter.impl.UserMapper;
import com.shavneva.billingserver.dto.UserInfoDTO;
import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.service.impl.AccountService;
import com.shavneva.billingserver.service.impl.TariffService;
import com.shavneva.billingserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    private final UserService userService;
    private final TariffService tariffService;
    private final AccountService accountService;

    @Autowired
    public UserInfoController(UserService userService, TariffService tariffService, AccountService accountService) {
        this.userService = userService;
        this.tariffService = tariffService;
        this.accountService = accountService;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserInfoDTO> getUserInfo(@PathVariable Integer userId) {
        User user = userService.findById(userId);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }

        Tariff tariff = (Tariff) tariffService.findByUserId(userId);
        Account account = (Account) accountService.findByUserId(userId);

        UserInfoDTO userInfoDTO = new UserInfoDTO();
        UserMapper userMapper = new UserMapper();
        TariffMapper tariffMapper = new TariffMapper();
        AccountMapper accountMapper = new AccountMapper();

        userInfoDTO.setUser(userMapper.mapToDto(user));
        userInfoDTO.setTariff(tariffMapper.mapToDto(tariff));
        userInfoDTO.setAccount(accountMapper.mapToDto(account));

        return ResponseEntity.ok(userInfoDTO);
    }
}