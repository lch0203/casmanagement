package cn.ynou.casmanagement.restController;

import cn.ynou.casmanagement.model.RespCode;
import cn.ynou.casmanagement.model.RespEntity;
import cn.ynou.casmanagement.model.Users;
import cn.ynou.casmanagement.service.UsersService;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UsersService usersService;

    @GetMapping("list")
    public List<Users> getUsersList() {
        return usersService.findAll();
    }

    @PostMapping("/add")
    public RespEntity addOneUser(@RequestBody Users users){
        System.out.println(users);
        RespEntity re =  new RespEntity(1,"SUCCESS",usersService.save(users));
        return re;
    }
}
