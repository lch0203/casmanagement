package cn.ynou.casmanagement.model;

import java.util.HashMap;
import java.util.Map;

public class ServiceEntity {
    private static Map<String,String> serviceId;
    static {
        serviceId = new HashMap<>();
        serviceId.put("253ea","ZCGL");  //需要和数据库对应
        serviceId.put("011f7","VCOM");
        serviceId.put("0f6f6","CJGL");
    }
    public  static  Map<String,String> getRequestCodeMap(){
        return serviceId;
    }

    public static boolean serviceIdExists(String serviceIdKey){
        return serviceId.containsKey(serviceIdKey);
    }

    public static String getServiceIdValue(String serviceIdKey){
        return serviceId.get(serviceIdKey);
    }
}
