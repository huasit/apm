package com.huasit.apm.core.role.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 *
 */
@Transactional
public interface RoleRepository extends CrudRepository<Role, Long>, JpaSpecificationExecutor<Role>  {

    /**
     *
     */
    @Query("from Role where del=false and id in (select roleId from RoleGroupRole where groupId in (select id from RoleGroup where del=false) and groupId in (select groupId from RoleGroupUser where userId=?1))")
    List<Role> findByUserId(Long userId);

    /**
     *
     */
    @Query("from Role where id=:id")
    Role findRoleById(@Param("id") Long id);

    /**
     *
     */
    @Query("from Role where rkey=?1")
    Role findByRkey(String rkey);

    /**
     *
     */
    @Query("from Role r where r.del=false and r.rkey=:rkey and exists (select 1 from RoleGroupUser ru where ru.userId=:userId and ru.groupId in (select groupId from RoleGroupRole where roleId=r.id) and ru.groupId in (select id from RoleGroup where del=false))")
    Role checkUserHasRole(@Param("userId") Long userId, @Param("rkey") String rkey);

    /**
     *
     */
    @Modifying
    @Query("update Role r set r.del=:del,r.modifyId=:modifyId,r.modifyTime=:modifyTime where r.id=:id")
    void updateDel(@Param("id") Long id, @Param("del") boolean del, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);
}