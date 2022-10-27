package com.kong.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kong.domain.dto.RoleDao;
import com.kong.domain.entity.Role;
import com.kong.domain.vo.PageVo;

import java.util.List;


/**
 * 角色信息表(Role)表服务接口
 *
 * @author makejava
 * @since 2022-10-17 15:23:20
 */
public interface RoleService extends IService<Role> {

    List<String> selectRoleKeyByUserId(Long id);

    PageVo selectArticlePage(RoleDao roleDao, Integer pageNum, Integer pageSize);

    void insertRole(Role role);

    void updateRole(Role role);

    List<Role> selectRoleAll();

    List<Long> selectRoleIdByUserId(Long userId);
}


