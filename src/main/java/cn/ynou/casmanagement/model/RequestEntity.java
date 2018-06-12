package cn.ynou.casmanagement.model;

import java.io.Serializable;

public class RequestEntity  {
    private String serviceId;
    private Object data;

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
