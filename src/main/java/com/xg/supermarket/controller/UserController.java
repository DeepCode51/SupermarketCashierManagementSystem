package com.xg.supermarket.controller;

import com.github.pagehelper.PageInfo;
import com.xg.supermarket.pojo.User;
import com.xg.supermarket.service.RoleService;
import com.xg.supermarket.service.UserService;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @RequiresPermissions("users:view")
    @RequestMapping("users")
    public String users(Map map){
        map.put("roles",roleService.listAll());
        return "users";
    }

    /*分页查询*/
    @RequiresPermissions("user:page")
    @RequestMapping("/user/pageUsers")
    @ResponseBody
    public Map pageUsers(@RequestParam(name = "pageNum",defaultValue = "1")Integer pageNum, @RequestParam(name = "pageSize",defaultValue = "0")Integer pageSize, String name, String uno, String tel){
        PageInfo<User> userPageInfo = userService.pageUserBy(pageNum, pageSize, name, uno, tel);
        Map map = new HashMap();
        map.put("total",userPageInfo.getTotal());
        map.put("rows",userPageInfo.getList());
        return map;
    }

    /*添加用户*/
    @RequiresPermissions("user:add")
    @PostMapping("user/addUser")
    @ResponseBody
    public ReMap addUser(User user){
        userService.addUser(user);
        return ReMapUtil.success("添加成功").setStatus("success");
    }
    /*修改*/
    @RequiresPermissions("user:update")
    @PostMapping("user/updateUser")
    @ResponseBody
    public ReMap updateUser(User user){
        userService.updateUser(user);
        return ReMapUtil.success("修改成功").setStatus("success");
    }
    /*删除*/
    @RequiresPermissions("user:del")
    @PostMapping("user/delUser")
    @ResponseBody
    public ReMap updateUser(Integer uid){
        userService.delUser(uid);
        return ReMapUtil.success("删除成功").setStatus("success");
    }
}
