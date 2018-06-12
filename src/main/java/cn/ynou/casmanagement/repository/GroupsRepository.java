package cn.ynou.casmanagement.repository;

import cn.ynou.casmanagement.model.Groups;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GroupsRepository extends JpaRepository<Groups,Integer> {
    public List<Groups> findByName(String name);
}
