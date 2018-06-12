package cn.ynou.casmanagement.service;

import cn.ynou.casmanagement.model.GroupUsers;
import cn.ynou.casmanagement.model.Groups;
import cn.ynou.casmanagement.model.UserGroupMappings;
import cn.ynou.casmanagement.model.Users;
import cn.ynou.casmanagement.repository.UsersRepository;
import io.swagger.models.auth.In;
import org.apache.catalina.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class GroupUsersService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private GroupsService groupsService;


    @Autowired
    private UsersRepository usersRepository;

    /***
     * 更加组名得到该组下所有的用户....
     * @param groupName
     * @return
     */
    public GroupUsers getGroupUsersByGourpName(String groupName) {
        Object[] parm = new Object[]{groupName};
        String getUsersSql = "SELECT * FROM users WHERE id IN\n" +
                "\t(SELECT user_id from user_group_mappings WHERE group_id in\n" +
                "\t\t(SELECT id FROM groups WHERE name = ?)\n" +
                ")";
        String getGroupSql = "SELECT * FROM groups WHERE name = ?";
        List<Users> listUsers = jdbcTemplate.query(getUsersSql, new UsersRowMapper(), parm);
        List<Groups> groups = jdbcTemplate.query(getGroupSql, new GroupsRowMapper(), parm);
        if (listUsers != null && listUsers.size() > 0 && groups != null && groups.size() > 0) {
            GroupUsers groupUsers = new GroupUsers();
            groupUsers.setGroupId(groups.get(0).getId());
            groupUsers.setGroupName(groups.get(0).getName());
            groupUsers.setUserList(listUsers);
            return groupUsers;
        } else {
            return null;
        }
    }

    public GroupUsers addUserListToGroup(String groupName, List<Users> usersList) {
        Integer groupId = -1;
        String insertSql = "INSERT INTO user_group_mappings(user_id,group_id) values(?,?)";
        String querySql = "SELECT * FROM user_group_mappings WHERE user_id = ? AND group_id = ?";
        try {
            groupId = groupsService.findByName(groupName).get(0).getId();
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        if (groupId == -1) {
            return null;
        }
        if(usersList == null){
            return null;
        }
        for (Users temp : usersList) { //剔除不存在的用户
            if (usersRepository.findByUid(temp.getUid()).size() > 0) { //用户不存在排除
                List<UserGroupMappings> list = jdbcTemplate.query(querySql, new UserGroupMappingsRowMapper(), new Object[]{usersRepository.findByUid(temp.getUid()).get(0).getId(), groupId});
                if (list.size() > 0) { //数据已经存在
                    continue;
                }
                jdbcTemplate.update(insertSql, new Object[]{usersRepository.findByUid(temp.getUid()).get(0).getId(), groupId});
            }
        }
        return getGroupUsersByGourpName(groupName);
    }

    public GroupUsers removeUserListFromGroup(String groupName, List<Users> usersList) {
        Integer groupId = -1;
        String deleteSql = "DELETE FROM user_group_mappings WHERE user_id = ? AND group_id = ?";
        try {
            groupId = groupsService.findByName(groupName).get(0).getId();
        } catch (NullPointerException e) {
            return null;
        } catch (Exception e) {
            return null;
        }
        if (groupId == -1) {
            return null;
        }
        if(usersList == null){
            return null;
        }
        for (Users temp : usersList) {
            if (usersRepository.findByUid(temp.getUid()).size() > 0) { //用户不存在排除
                jdbcTemplate.update(deleteSql, new Object[]{usersRepository.findByUid(temp.getUid()).get(0).getId(), groupId});
            }
        }
        return getGroupUsersByGourpName(groupName);
    }


    class UserGroupMappingsRowMapper implements RowMapper<UserGroupMappings> {

        @Override
        public UserGroupMappings mapRow(ResultSet resultSet, int i) throws SQLException {
            UserGroupMappings userGroupMappings = new UserGroupMappings();
            userGroupMappings.setUserId(resultSet.getInt("user_id"));
            userGroupMappings.setGroupId(resultSet.getInt("group_id"));
            return userGroupMappings;
        }
    }

    class GroupsRowMapper implements RowMapper<Groups> {
        @Override
        public Groups mapRow(ResultSet resultSet, int i) throws SQLException {
            Groups groups = new Groups();
            groups.setId(resultSet.getInt("id"));
            groups.setName(resultSet.getString("name"));
            return groups;
        }
    }

    class UsersRowMapper implements RowMapper<Users> {
        @Override
        public Users mapRow(ResultSet resultSet, int i) throws SQLException {
            Users user = new Users();
            user.setId(resultSet.getInt("id"));
            user.setUid(resultSet.getString("uid"));
            user.setPassword("");
            user.setUsername(resultSet.getString("username"));
            user.setDepid(resultSet.getInt("depid"));
            user.setDepname(resultSet.getString("depname"));
            user.setCategory(resultSet.getString("category"));
            user.setIdnum(resultSet.getString("idnum"));
            user.setExpired(resultSet.getInt("expired"));
            user.setDisabled(resultSet.getInt("disabled"));
            user.setEmail(resultSet.getString("email"));
            user.setPhone(resultSet.getString("phone"));
            return user;
        }
    }

}
