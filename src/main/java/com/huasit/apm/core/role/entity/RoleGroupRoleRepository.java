package com.huasit.apm.core.role.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface RoleGroupRoleRepository extends CrudRepository<RoleGroupRole, Long>, JpaSpecificationExecutor<RoleGroupRole>  {

    /**
     *
     */
    @Modifying
    @Query("delete from RoleGroupRole where groupId=?1")
    void deleteByGroupId(Long groupId);

    /**
     *
     */
    List<RoleGroupRole> findByGroupId(Long groupId);
}