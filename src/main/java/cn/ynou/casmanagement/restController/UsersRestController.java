package cn.ynou.casmanagement.restController;

import cn.ynou.casmanagement.model.*;
import cn.ynou.casmanagement.service.UsersService;
import cn.ynou.casmanagement.utils.Base64Util;
import cn.ynou.casmanagement.utils.EnumRM;
import cn.ynou.casmanagement.utils.ResponseMessages;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"user"})
@RestController
@RequestMapping("/user")
public class UsersRestController {

    @Autowired
    UsersService usersService;

    /***
     * 根据部门id查询部门下所有用户
     * @param depid
     * @param serviceId
     * @return
     */
    @GetMapping("/list/depid/{depid}/{serviceId}")
    public ResponseEntity getUserList(@PathVariable String depid, @PathVariable String serviceId) {
//  public List<Users> getUserList(@PathVariable String depid,@PathVariable String serviceId) {
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400,  null); //serviceId不存在....
        }
        List<Users> userList = null;
        if (depid.equals("all") || depid == null) {
            userList = usersService.findAll();
        } else {
            userList = usersService.findByDepid(Integer.parseInt(depid));
        }
        if (userList.size() == 0) { //部门编号不存在
            return new ResponseEntity(303, null);
        }
        for (Users temp : userList) {
            temp.setPassword("");
        }
        return new ResponseEntity(200,  userList);
//        return new ResponseEntity(200,ResponseMessages.getMessages(200),Base64Util.getBASE64(JSON.toJSONString(userList)));
    }

    /***
     *根据用户uid查询用户信息
     * @param uid
     * @return
     */
    @GetMapping("/list/uid/{uid}/{serviceId}")
    public ResponseEntity getUserByUid(@PathVariable String uid, @PathVariable String serviceId) {
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400,  null); //serviceId不存在....
        }
        Users user = usersService.findByUid(uid);
        if (user == null) { //用户不存在
            return new ResponseEntity(302,  null);
        }
        user.setPassword("");
        return new ResponseEntity(200,  user);
    }


    @PostMapping("/add")
    /***
     * 添加单个用户
     */
    public ResponseEntity addOneUser(@RequestBody JSONObject request) {
        ResponseEntity re = null;
        String serviceId = request.getString("serviceId");
        Users user = null;
        try {
            user = request.getObject("data", Users.class);
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity(403,  null);
        }
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400,  null); //serviceId不存在....
        }
        if(user.getDepid() == null || user.getUid() ==null || user.getPassword()==null || user.getUsername()==null){
            return new ResponseEntity(403,  null);
        }
        if (user.getPassword().length() != 32) { //密码不是有效的md5值
            return new ResponseEntity(305, null);
        }
        if (user.getDepid() != 101) {
            return new ResponseEntity(402, null);
        }
        if (usersService.findByUid(user.getUid()) != null) {
            return new ResponseEntity(301,  null);
        }
        user.setDepname(ServiceEntity.getRequestCodeMap().get(serviceId) + " - 临时用户");
        re = new ResponseEntity(200, usersService.save(user));
        return re;
    }

    @PostMapping("/updatepwd")
    public ResponseEntity updateUserPassword(@RequestBody JSONObject request) {
        String serviceId = request.getString("serviceId");
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400,null); //serviceId不存在....
        }
        UsersPasUpdate userUpdate;
        try {
            userUpdate = request.getObject("data", UsersPasUpdate.class);
        } catch (Exception e) {
            e.getStackTrace();
            return new ResponseEntity(403, null);
        }
        if(userUpdate.getUid() == null || userUpdate.getNewPassword() == null || userUpdate.getOldPassword() == null){
            return new ResponseEntity(403, null);
        }
        Users user = usersService.findByUid(userUpdate.getUid());
        if (user == null) {
            return new ResponseEntity(302, null);
        }
        if (!user.getPassword().equals(userUpdate.getOldPassword())) {
            return new ResponseEntity(304, null);
        }
        if (userUpdate.getNewPassword().length() != 32) { //密码不是有效的md5值
            return new ResponseEntity(305, null);
        }
        user.setPassword(userUpdate.getNewPassword());
        return new ResponseEntity(200,  usersService.save(user));
    }


}
