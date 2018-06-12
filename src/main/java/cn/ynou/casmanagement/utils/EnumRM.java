package cn.ynou.casmanagement.utils;

public enum EnumRM {
    ERQUEST_SUCCESSFUL(200),
    ERQUEST_CODE_ERROR(300),
    USER_EXIST(301),
    NO_PERMISSION(400),
    PERMISSION_ERROT(401),
    TEMP_USER_GROUP_ERROR(402);

    private Integer desc;
    private EnumRM(Integer desc){
        this.desc = desc;
    }

    public Integer getDesc() {
        return desc;
    }
}
