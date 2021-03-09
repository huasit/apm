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
public interface RoleGroupUserRepository extends CrudRepository<RoleGroupUser, Long>, JpaSpecificationExecutor<RoleGroupUser>  {

    /**
     *
     */
    @Modifying
    @Query("delete from RoleGroupUser where userId=?1")
    void deleteByUserId(Long userId);

    /**
     *
     */
    List<RoleGroupUser> findByUserId(Long userId);

    /**
     *
     */
    @Query("from RoleGroupUser u where u.groupId in (select groupId from RoleGroupRole where roleId in (select id from Role where del=false and rkey=?1)) and u.groupId in (select id from RoleGroup where del=false)")
    List<RoleGroupUser> findByRoleRkey(String rkey);
}