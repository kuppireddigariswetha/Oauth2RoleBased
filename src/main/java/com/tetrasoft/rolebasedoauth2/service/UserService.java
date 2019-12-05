package com.tetrasoft.rolebasedoauth2.service;

import com.tetrasoft.rolebasedoauth2.dto.UserDto;
import com.tetrasoft.rolebasedoauth2.model.User;

import java.util.List;

public interface UserService {

    UserDto save(UserDto user);
    List<UserDto> findAll();
    User findOne(long id);
    void delete(long id);
}
