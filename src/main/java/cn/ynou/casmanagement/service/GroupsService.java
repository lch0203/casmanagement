package cn.ynou.casmanagement.service;

import cn.ynou.casmanagement.model.GroupServiceMappings;
import cn.ynou.casmanagement.model.Groups;
import cn.ynou.casmanagement.model.UserGroupMappings;
import cn.ynou.casmanagement.repository.GroupServiceMappingsRepository;
import cn.ynou.casmanagement.repository.GroupsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupsService {

    @Autowired
    private GroupsRepository groupsRepository;
//    @Autowired
//    private UserGroupMappingsRepository userGroupMappingsRepository;
    @Autowired
    private GroupServiceMappingsRepository groupServiceMappingsRepository;


    public List<Groups> findByName(String groupName){
        if(groupName.equals("all")) {
            return groupsRepository.findAll();
        }else{
            return groupsRepository.findByName(groupName);
        }
    }


    public List<UserGroupMappings> getGroupUsers(Integer groupId){
//        return userGroupMappingsRepository.findByGroupId(groupId);
        return null;
    }
}
