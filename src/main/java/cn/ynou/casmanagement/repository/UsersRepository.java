package cn.ynou.casmanagement.repository;

import cn.ynou.casmanagement.model.Users;
import org.omg.PortableServer.LIFESPAN_POLICY_ID;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users,Integer> {

    public List<Users> findByDepname(String depname);
    public List<Users> findByDepid(Integer depid);
    public List<Users> findByUid(String uid);
}
