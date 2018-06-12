package cn.ynou.casmanagement.service;

import cn.ynou.casmanagement.model.Users;
import cn.ynou.casmanagement.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class UsersService {
    @Autowired
    UsersRepository usersRepository;

    public List<Users> findAll() {
        return usersRepository.findAll();
    }

    public List<Users> findByDepname(String depname) {
        return usersRepository.findByDepname(depname);
    }

    public List<Users> findByDepid(Integer depid) {
        return usersRepository.findByDepid(depid);
    }

    public Users save(Users user) {
        return usersRepository.save(user);
    }

    public Users findByUid(String uid) {
        if (usersRepository.findByUid(uid).size() != 0) {
            return usersRepository.findByUid(uid).get(0);
        }
        return  null;
    }



}
