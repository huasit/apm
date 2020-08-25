package com.huasit.apm.core.role.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 *
 */
@Transactional
public interface RoleRepository extends CrudRepository<Role, Long>, JpaSpecificationExecutor<Role>  {

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
    @Query("from Role r where r.del=false and r.rkey=:rkey and exists (select 1 from RoleUser ru where ru.user.id=:userId and r.id=ru.rid)")
    Role checkUserHasRole(@Param("userId") Long userId, @Param("rkey") String rkey);
}