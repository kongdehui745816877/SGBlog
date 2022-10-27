package com.kong.controller;

import com.kong.domain.ResponseResult;
import com.kong.domain.entity.LoginUser;
import com.kong.domain.entity.Menu;
import com.kong.domain.entity.User;
import com.kong.domain.vo.AdminUserInfoVo;
import com.kong.domain.vo.RouterVo;
import com.kong.domain.vo.UserInfoVo;
import com.kong.enums.AppHttpCodeEnum;
import com.kong.exception.SystemException;
import com.kong.service.BlogLoginService;
import com.kong.service.LoginService;
import com.kong.service.MenuService;
import com.kong.service.RoleService;
import com.kong.utils.BeanCopyUtils;
import com.kong.utils.RedisCache;
import com.kong.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private MenuService menuService;

    @Autowired
    private RoleService roleService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        if(!StringUtils.hasText(user.getUserName())){
            //提示 必须要传用户名
            throw  new SystemException(AppHttpCodeEnum.REQUIRE_USERNAME);
        }
        return loginService.login(user);
    }

    @PostMapping("/user/logout")
    public ResponseResult logout(){
        return loginService.logout();
    }

    @GetMapping("/getInfo")
    public ResponseResult<AdminUserInfoVo> getInfo(){
        //获取当前登录的用户
        LoginUser loginUser = SecurityUtils.getLoginUser();
        //根据用户id查询权限信息
        List<String> perms=menuService.selectPermsByUserId(loginUser.getUser().getId());
        //根据用户id查询角色信息
        List<String> roleKeyList=roleService.selectRoleKeyByUserId(loginUser.getUser().getId());


        //获取用户信息
        User user = loginUser.getUser();
        UserInfoVo userInfoVo = BeanCopyUtils.copyBean(user, UserInfoVo.class);
        //封装数据返回
        AdminUserInfoVo adminUserInfoVo=new AdminUserInfoVo(perms,roleKeyList,userInfoVo);
        return ResponseResult.okResult(adminUserInfoVo);
    }

    @GetMapping("/getRouters")
    public ResponseResult<RouterVo> getRouters(){
        Long userId = SecurityUtils.getUserId();
        //查询menu 结果是tree的形式
        List<Menu> menus=menuService.selectRouterMenuTreeByUserId(userId);
        //封装数据返回0
        return ResponseResult.okResult(new RouterVo(menus));
    }





}
