package com.kong.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kong.domain.entity.Role;
import com.kong.domain.entity.RoleMenu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


/**
 * 角色和菜单关联表(RoleMenu)表数据库访问层
 *
 * @author makejava
 * @since 2022-10-23 15:09:56
 */
@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {


    void insertAll(@Param("id") Long id, @Param("menuIds") Long[] menuIds);

    @Delete("delete from sys_role_menu where role_id = #{id}")
    void deleteRoleMenuByRoleId(Long id);
}

