package cn.ynou.casmanagement.model;

public class RespEntity {
    private int code;
    private String msg;
    private Object data;

    public RespEntity(RespCode respCode) {
        this.code = respCode.getCode();
        this.msg = respCode.getMsg();
    }

    public RespEntity(RespCode respCode, Object data) {
        this(respCode);
        this.data = data;
    }

    public RespEntity(int code,String msg,Object data){
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
}
