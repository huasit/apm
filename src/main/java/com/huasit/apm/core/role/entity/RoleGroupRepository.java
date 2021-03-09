package com.huasit.apm.core.role.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;

/**
 *
 */
@Transactional
public interface RoleGroupRepository extends CrudRepository<RoleGroup, Long>, JpaSpecificationExecutor<RoleGroup>  {

    /**
     *
     */
    @Query("from RoleGroup where id=:id")
    RoleGroup findRoleGroupById(@Param("id") Long id);

    /**
     *
     */
    @Modifying
    @Query("update RoleGroup r set r.del=:del,r.modifyId=:modifyId,r.modifyTime=:modifyTime where r.id=:id")
    void updateDel(@Param("id") Long id, @Param("del") boolean del, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);
}