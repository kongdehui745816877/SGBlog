package com.kong.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kong.constants.SystemConstants;
import com.kong.domain.dto.RoleDao;
import com.kong.domain.entity.Article;
import com.kong.domain.entity.Role;
import com.kong.domain.entity.RoleMenu;
import com.kong.domain.entity.UserRole;
import com.kong.domain.vo.PageVo;
import com.kong.mapper.RoleMapper;
import com.kong.mapper.RoleMenuMapper;
import com.kong.service.RoleMenuService;
import com.kong.service.RoleService;
import com.kong.utils.BeanCopyUtils;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 角色信息表(Role)表服务实现类
 *
 * @author makejava
 * @since 2022-10-17 15:23:20
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMenuMapper roleMenuMapper;

    @Override
    public List<String> selectRoleKeyByUserId(Long id) {
        //判断是否是管理员 如果是返回集合中只需要有admin
        if(id == 1L){
            List<String> roleKey = new ArrayList<>();
            roleKey.add("admin");
            return roleKey;
        }
        //否则查询用户所有具有的角色信息
        return getBaseMapper().selectRoleKeyByUserId(id);
    }

    @Override
    public PageVo selectArticlePage(RoleDao roleDao, Integer pageNum, Integer pageSize) {
        LambdaQueryWrapper<Role> queryWrapper =new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.hasText(roleDao.getRoleName()),Role::getRoleName,roleDao.getRoleName());
        queryWrapper.like(StringUtils.hasText(roleDao.getStatus()),Role::getStatus,roleDao.getStatus());


        Page<Role> page = new Page<>(pageNum,pageSize);
        page(page,queryWrapper);

        //封装数据返回
        return new PageVo(page.getRecords(),page.getTotal());

    }

    @Override
    @Transactional
    public void insertRole(Role role) {
        save(role);
        //System.out.println(role.getId());
        if(role.getMenuIds()!=null&&role.getMenuIds().length>0){
            insertRoleMenu(role.getId(),role.getMenuIds());
        }
    }


    @Override
    public void updateRole(Role role) {
        updateById(role);
        roleMenuMapper.deleteRoleMenuByRoleId(role.getId());
        insertRoleMenu(role.getId(),role.getMenuIds());
    }

    @Override
    public List<Role> selectRoleAll() {
        return  list(Wrappers.<Role>lambdaQuery().eq(Role::getStatus, SystemConstants.NORMAL));
    }

    @Override
    public List<Long> selectRoleIdByUserId(Long userId) {
        return getBaseMapper().selectRoleIdByUserId(userId);
    }



    private void insertRoleMenu(Long id, Long[] menuIds) {
        roleMenuMapper.insertAll(id,menuIds);

    }
}
