package com.shavneva.billingserver.controller.impl;

import com.shavneva.billingserver.controller.BaseController;
import com.shavneva.billingserver.controller.ICrudController;
import com.shavneva.billingserver.converter.impl.ServicesMapper;
import com.shavneva.billingserver.converter.impl.TariffMapper;
import com.shavneva.billingserver.dto.ServiceDto;
import com.shavneva.billingserver.dto.TariffDto;
import com.shavneva.billingserver.entities.Services;
import com.shavneva.billingserver.entities.Tariff;
import com.shavneva.billingserver.service.ICrudService;
import com.shavneva.billingserver.service.impl.ServicesService;
import com.shavneva.billingserver.service.impl.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@RequestMapping("/tariffs")
public class TariffController extends BaseController<Tariff, TariffDto> {

    @Autowired
    public TariffController(TariffService tariffService, TariffMapper tariffMapper) {
        super((ICrudService<Tariff>) tariffService, tariffMapper);
    }
    @Override
    //@PreAuthorize("permitAll()")
    public TariffDto create(TariffDto tariffDto) {
        return super.create(tariffDto);
    }

    @Override
    //@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
    //@PostFilter("hasRole('ROLE_ADMIN') or filterObject.email == authentication.name")
    public List<TariffDto> getAll() {
        return super.getAll();
    }

    @Override
    //@PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public TariffDto getById(int id) {
        return super.getById(id);
    }

    @Override
    //@PreAuthorize("#newDTO.email == authentication.principal.email or hasRole('ROLE_ADMIN')")
    public TariffDto update(TariffDto newDto) {
        return super.update(newDto);
    }

    @Override
    //@PreAuthorize("principal.userId == #id or hasRole('ROLE_ADMIN')")
    public void delete(int id) {
        super.delete(id);
    }
}
