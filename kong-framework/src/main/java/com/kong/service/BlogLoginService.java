package com.kong.service;

import com.kong.domain.ResponseResult;
import com.kong.domain.entity.User;

public interface BlogLoginService {
    ResponseResult login(User user);

    ResponseResult logout();
}
