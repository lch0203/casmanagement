package cn.ynou.casmanagement.repository;

import cn.ynou.casmanagement.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Integer> {
}
