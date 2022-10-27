package com.kong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kong.domain.entity.RoleMenu;


public interface RoleMenuService extends IService<RoleMenu> {

    void deleteRoleMenuByRoleId(Long id);
}