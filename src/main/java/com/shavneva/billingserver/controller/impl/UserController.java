package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.converter.impl.AccountMapper;
import com.shavneva.billingserver.converter.impl.RoleMapper;
import com.shavneva.billingserver.converter.impl.TariffMapper;
import com.shavneva.billingserver.converter.impl.UserMapper;
import com.shavneva.billingserver.dto.AccountDto;
import com.shavneva.billingserver.dto.RoleDto;
import com.shavneva.billingserver.dto.TariffDto;
import com.shavneva.billingserver.dto.UserDto;
import com.shavneva.billingserver.entities.Account;
import com.shavneva.billingserver.entities.Role;
import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.entities.User;
import com.shavneva.billingserver.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController extends BaseController<User, UserDto> {

    private final UserService userService;
    @Autowired
    public UserController(UserService userService, UserMapper userMapper, UserService userService1) {
        super(userService, userMapper);
        this.userService = userService1;
    }
    @Override
    public UserDto create(UserDto userDto) {
        return super.create(userDto);
    }

    @GetMapping("/users-with-tariffs-and-accounts")
    public ResponseEntity<List<UserDto>> getAllUsersWithTariffsAndAccounts() {
        List<User> users = userService.getAll();
        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {
            UserMapper userMapper = new UserMapper();
            UserDto userDto = userMapper.mapToDto(user);

            // Получаем информацию о тарифе пользователя
            Tariff tariff = user.getTariff();
            TariffMapper tariffMapper = new TariffMapper();
            TariffDto tariffDto = tariffMapper.mapToDto(tariff);
            userDto.setTariff(tariffDto);

            // Получаем информацию о счете пользователя
            Account account = user.getAccount();
            AccountMapper accountMapper = new AccountMapper();
            AccountDto accountDto = accountMapper.mapToDto(account);
            userDto.setAccount(accountDto);

            userDtos.add(userDto);
        }

        return ResponseEntity.ok(userDtos);
    }

    @GetMapping("/user-role")
    public ResponseEntity<List<UserDto>> getAllUsersWithRoles() {
        List<User> users = userService.getAll();
        List<UserDto> userDtos = new ArrayList<>();

       /* for (User user : users) {
            UserMapper userMapper = new UserMapper();
            UserDto userDto = userMapper.mapToDto(user);

            // Получаем информацию о роли пользователя
            Role role = user.getRole();
            RoleMapper roleMapper = new RoleMapper();
            RoleDto roleDto = roleMapper.mapToDto(role);
            userDto.setRole(roleDto);

            userDtos.add(userDto);
        }*/

        return ResponseEntity.ok(userDtos);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    @PostFilter("hasRole('ROLE_ADMIN') or filterObject.email == authentication.name")
    public List<UserDto> getAll() {
        return super.getAll();
    }

    @Override
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public UserDto getById(int id) {
        return super.getById(id);
    }

    @Override
    @PreAuthorize("#newDTO.email == authentication.principal.email or hasRole('ROLE_ADMIN')")
    public UserDto update(UserDto newDTO) {
        return super.update(newDTO);
    }

    @Override
    @PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        super.delete(id);
    }

}