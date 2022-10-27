package com.kong.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kong.domain.entity.UserRole;
import com.kong.mapper.UserRoleMapper;
import com.kong.service.UserRoleService;
import org.springframework.stereotype.Service;

/**
 * 用户和角色关联表(UserRole)表服务实现类
 *
 * @author makejava
 * @since 2022-10-23 22:43:53
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

}
