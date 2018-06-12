package cn.ynou.casmanagement.model;

import cn.ynou.casmanagement.utils.ResponseMessages;

import java.io.Serializable;

public class ResponseEntity implements Serializable {
    private Integer responseCode;
    private String responseMessage;
    private Object data;

    public ResponseEntity(){
    }

    public ResponseEntity(Integer responseCode,String responseMessage,Object data){
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.data = data;
    }

    public  ResponseEntity(Integer responseCode,Object data){
        this.responseCode = responseCode;
        this.responseMessage = ResponseMessages.getMessages().get(responseCode);
        this.data = data;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
