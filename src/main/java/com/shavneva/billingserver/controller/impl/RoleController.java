package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.converter.IMapper;
import com.shavneva.billingserver.dto.RoleDto;
import com.shavneva.billingserver.entities.Role;
import com.shavneva.billingserver.service.impl.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role, RoleDto> {
    @Autowired
    public RoleController(RoleService role, IMapper<Role, RoleDto> mapper) {
        super(role, mapper);
    }

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostFilter("hasRole('ROLE_ADMIN')")
    public List<RoleDto> getAll() {
        return super.getAll();
    }
}
