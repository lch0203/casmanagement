package cn.ynou.casmanagement.restController;

import ch.qos.logback.classic.boolex.GEventEvaluator;
import cn.ynou.casmanagement.model.*;
import cn.ynou.casmanagement.service.GroupUsersService;
import cn.ynou.casmanagement.service.GroupsService;
import cn.ynou.casmanagement.utils.ResponseMessages;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags={"group"})
@RestController
@RequestMapping("/group")
public class GroupsRestController {

    @Autowired
    GroupUsersService groupUsersService;

    @Autowired
    GroupsService groupsService;

    /***
     * 根据组名得到组信息
     * @param groupName -》 all为参数返回所有组信息
     * @param serviceId
     * @return
     */
    @GetMapping("/list/{groupName}/{serviceId}")
    public ResponseEntity getGroupsByName(@PathVariable String groupName, @PathVariable String serviceId){
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400, null); //serviceId不存在....
        }
        List<Groups> group= groupsService.findByName(groupName);
        if(group.size()== 0){
            return new ResponseEntity(306, null); //serviceId不存在....
        }
        return new ResponseEntity(200, group);
    }

    /***
     *根据组名列出所有该组中的用户信息
     *
     */
    @GetMapping("/listusers/{groupName}/{serviceId}")
    public ResponseEntity getUserListByGourpName(@PathVariable String groupName, @PathVariable String serviceId){
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400,null); //serviceId不存在....
        }
        List<Groups> group= groupsService.findByName(groupName);
        if(group.size()== 0){
            return new ResponseEntity(306,  null); //serviceId不存在....
        }
        GroupUsers groupUsers = groupUsersService.getGroupUsersByGourpName(groupName);
        return new ResponseEntity(200, groupUsers);
    }

    /***
     * 添加一个到多个用户到组中
     * @param request
     * @return
     */
    @PostMapping("/addusers")
    public ResponseEntity addUserListToGroup(@RequestBody JSONObject request){
        String serviceId = request.getString("serviceId");
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400, null); //serviceId不存在....
        }
        GroupUsers in = null;
        try {
            in = request.getObject("data", GroupUsers.class);
        }catch (Exception e){
            return new ResponseEntity(403, null); //serviceId不存在....
        }
        if(!ServiceEntity.getRequestCodeMap().get(serviceId).equals(in.getGroupName())){
            return new ResponseEntity(401,  null); //serviceId不存在....
        }
        if(in.getUserList()==null){
            return new ResponseEntity(403,  null); //serviceId不存在....
        }
        GroupUsers out = groupUsersService.addUserListToGroup(in.getGroupName(),in.getUserList());
        return new ResponseEntity(200, out);
    }

    /***
     *
     * @param request
     * @return
     */
    @PostMapping("/removeusers")
    public ResponseEntity removeUserListFromGroup(@RequestBody JSONObject request) {
        String serviceId = request.getString("serviceId");
        if (!ServiceEntity.serviceIdExists(serviceId)) {
            return new ResponseEntity(400, null); //serviceId不存在....
        }
        GroupUsers in = null;
        try {
            in = request.getObject("data", GroupUsers.class);
        }catch (Exception e){
            return new ResponseEntity(403,null); //serviceId不存在....
        }
        if(!ServiceEntity.getRequestCodeMap().get(serviceId).equals(in.getGroupName())){
            return new ResponseEntity(401, null); //serviceId不存在....
        }
        if(in.getUserList()==null){
            return new ResponseEntity(403, null); //serviceId不存在....
        }
        GroupUsers out = groupUsersService.removeUserListFromGroup(in.getGroupName(),in.getUserList());
        return new ResponseEntity(200, out);
    }
}
