package cn.ynou.casmanagement.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//public class ResponseMessages {
//    ERQUEST_SUCCESSFUL("请求成功..."),
//    ERQUEST_CODE_ERROR("请求码不正确..."),
//    NO_PERMISSION("无权访问..."),
//    USER_EXIST("用户已存在..."),
//    TEMP_USER_GROUP_ERROR("临时用户所属部门id错误...."),
//    PERMISSION_ERROT("权限错误...");
//
//    private String desc;
//    private ResponseMessages(String desc){
//        this.desc = desc;
//    }
//
//    public String getDesc() {
//        return desc;
//    }
public class ResponseMessages {

    private static Map<Integer, String> MESSAGES;
    static {
        MESSAGES = new HashMap<>();
        MESSAGES.put(200, "请求成功");
        MESSAGES.put(300, "服务码不正确");
        MESSAGES.put(301, "用户已存在");
        MESSAGES.put(302, "用户不存在");
        MESSAGES.put(303, "部门编号错误");
        MESSAGES.put(304, "旧密码不正确");
        MESSAGES.put(305, "密码不是一个有效的MD5值");
        MESSAGES.put(306, "组名称错误");
        MESSAGES.put(400, "无权访问");
        MESSAGES.put(401, "权限错误");
        MESSAGES.put(402, "临时用户所属部门id错误");
        MESSAGES.put(403, "请求参数错误");
    }

    public static Map<Integer, String> getMessages(){
        return MESSAGES;
    }

    public static String getMessages(Integer key){
        return MESSAGES.get(key);
    }
}
