package cn.ynou.casmanagement.repository;

import cn.ynou.casmanagement.model.GroupServiceMappings;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupServiceMappingsRepository extends JpaRepository<GroupServiceMappings,GroupServiceMappings.GroupServiceMappingsKey> {
}
