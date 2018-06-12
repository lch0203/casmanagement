package cn.ynou.casmanagement.model;

import javax.persistence.*;
import java.io.Serializable;
//
//@Entity
//@Embeddable
//public class GroupServiceMappings {
//
//    static class GroupServiceComposedKey {
//        private Integer groupId;
//        private Integer serviceId;
//
//        public Integer getGroupId() {
//            return groupId;
//        }
//
//        public void setGroupId(Integer groupId) {
//            this.groupId = groupId;
//        }
//
//        public Integer getServiceId() {
//            return serviceId;
//        }
//
//        public void setServiceId(Integer serviceId) {
//            this.serviceId = serviceId;
//        }
//    }
//
//    @EmbeddedId
//    private GroupServiceComposedKey id;
//    private Integer groupId;
//    private Integer serviceId;
//
//    @Column(name="group_id")
//    public Integer getGroupId() {
//        return id.getGroupId();
//    }
//
//    public void setGroupId(Integer groupId) {
//        this.groupId = groupId;
//    }
//
//    @Column(name="service_id")
//    public Integer getServiceId() {
//        return id.getServiceId();
//    }
//
//    public void setServiceId(Integer serviceId) {
//        this.serviceId = serviceId;
//    }
//}

@Entity
@Table(name = "group_service_mappings")
public class GroupServiceMappings implements Serializable {
    @Id
    GroupServiceMappingsKey key;

    @Embeddable
    public class GroupServiceMappingsKey implements Serializable{
        private Integer groupId;
        private Integer serviceId;

        public Integer getGroupId() {
            return groupId;
        }

        public void setGroupId(Integer groupId) {
            this.groupId = groupId;
        }

        public Integer getServiceId() {
            return serviceId;
        }

        public void setServiceId(Integer serviceId) {
            this.serviceId = serviceId;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }

        @Override
        public boolean equals(Object obj) {
            if(obj instanceof GroupServiceMappingsKey){
                GroupServiceMappingsKey key = (GroupServiceMappingsKey)obj;
                if(this.getGroupId() == key.getGroupId() && this.getServiceId() == key.getServiceId()){
                    return true;
                }
            }
            return false;
        }
    }

}
