package com.xg.supermarket.service;


import com.github.pagehelper.PageInfo;
import com.xg.supermarket.pojo.User;

public interface UserService {
    PageInfo<User> pageUserBy(Integer pageNum, Integer pageSize, String name, String uno, String tel);
    int addUser(User user);
    int updateUser(User user);
    int delUser(Integer uid);
}
