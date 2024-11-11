package com.xg.supermarket.controller;

import com.xg.supermarket.pojo.Role;
import com.xg.supermarket.service.RoleService;
import com.xg.supermarket.utils.ReMap;
import com.xg.supermarket.utils.ReMapUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class RoleController {
    @Autowired
    private RoleService roleService;
    @RequiresPermissions("roles:view")
    @RequestMapping("roles")
    public String roles(){
        return "roles";
    }



    @RequiresPermissions("role:add")
    @PostMapping("role/listAll")
    @ResponseBody
    public Map listAll(){
        Map map = new HashMap<>();

        List<Role> roles = roleService.listAll();

        map.put("rows",roles);
        return map;
    }
    /*添加*/
    @RequiresPermissions("role:add")
    @PostMapping("role/addRole")
    @ResponseBody
    public ReMap addRole(Role role){
        roleService.addRole(role);
        return ReMapUtil.success("添加成功").setStatus("success");
    }
    /*修改*/
    @RequiresPermissions("role:update")
    @PostMapping("role/updateRole")
    @ResponseBody
    public ReMap updateRole(Role role){
        roleService.updateRole(role);
        return ReMapUtil.success("修改成功").setStatus("success");
    }
    /*删除*/
    @RequiresPermissions("role:del")
    @PostMapping("role/delRole")
    @ResponseBody
    public ReMap delRole(Integer rid){
        roleService.delRole(rid);
        return ReMapUtil.success("删除成功").setStatus("success");
    }
}
