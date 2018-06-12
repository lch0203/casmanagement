package cn.ynou.casmanagement.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;
//
//@Entity
//@Table
//@Embeddable
////user_group_mappings UserGroupMappings
//public class UserGroupMappings {
//
//    static class UserGroupComposedKey implements Serializable {
//        private  Integer userId;
//        private  Integer groupId;
//
//        public Integer getUserId() {
//            return userId;
//        }
//
//        public void setUserId(Integer userId) {
//            this.userId = userId;
//        }
//
//        public Integer getGroupId() {
//            return groupId;
//        }
//
//        public void setGroupId(Integer groupId) {
//            this.groupId = groupId;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            UserGroupComposedKey that = (UserGroupComposedKey) o;
//            return Objects.equals(userId, that.userId) &&
//                    Objects.equals(groupId, that.groupId);
//        }
//
//        @Override
//        public int hashCode() {
//
//            return Objects.hash(userId, groupId);
//        }
//    }
//
//    @EmbeddedId
//    private UserGroupComposedKey id;
//    private  Integer userId;
//    private  Integer groupId;
//
//    @Column(name="user_id")
//    public Integer getUserId() {
//        return id.getUserId();
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    @Column(name="group_id")
//    public Integer getGroupId() {
//        return id.getGroupId();
//    }
//
//    public void setGroupId(Integer groupId) {
//        this.groupId = groupId;
//    }
//}

//@Entity
//@Table(name = "user_group_mappings")
//public class UserGroupMappings implements Serializable {
//    @Id
//    UserGroupMappingsKey key;
//
//    @Column(name="user_id")
//    public Integer getUserId() {
//        return key.getUserId();
//    }
//
//    public void setUserId(Integer userId) {
//        this.key.setUserId(userId);
//    }
//
//    @Column(name="group_id")
//    public Integer getGroupId() {
//        return key.getGroupId();
//    }
//
//    public void setGroupId(Integer groupId) {
//        this.key.setGroupId(groupId);
//    }
//
//    public UserGroupMappingsKey getKey() {
//        return key;
//    }
//
//    public void setKey(UserGroupMappingsKey key) {
//        this.key = key;
//    }
//
//    public UserGroupMappings(){
//
//    }
//
//    public UserGroupMappings(Integer userId,Integer groupId){
//        setUserId(userId);
//        setGroupId(groupId);
//    }
//
//    public UserGroupMappings(UserGroupMappingsKey key){
//        this.key = key;
//    }
//
//
//
//    @Embeddable
//    public static class UserGroupMappingsKey implements  Serializable {
//
//        private Integer userId;
//        private Integer groupId;
//        public UserGroupMappingsKey(Integer userId,Integer groupId){
//            this.userId = userId;
//            this.groupId = groupId;
//        }
//        public UserGroupMappingsKey(){}
//
//
//        public Integer getUserId() {
//            return userId;
//        }
//
//        public void setUserId(Integer userId) {
//            this.userId = userId;
//        }
//
//
//        public Integer getGroupId() {
//            return groupId;
//        }
//
//        public void setGroupId(Integer groupId) {
//            this.groupId = groupId;
//        }
//
//        @Override
//        public int hashCode() {
//            return super.hashCode();
//        }
//
//        @Override
//        public boolean equals(Object obj) {
//            if(obj instanceof UserGroupMappingsKey){
//                UserGroupMappingsKey key = (UserGroupMappingsKey)obj;
//                if(this.getGroupId() == key.getGroupId() && this.getUserId() == key.getUserId()){
//                    return true;
//                }
//            }
//            return false;
//        }
//    }

//}

public class UserGroupMappings implements Serializable {
    private Integer userId;
    private Integer groupId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }
}