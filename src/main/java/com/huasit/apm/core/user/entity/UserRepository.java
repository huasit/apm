package com.huasit.apm.core.user.entity;

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
public interface UserRepository extends CrudRepository<User, Long>, JpaSpecificationExecutor<User>  {

    /**
     *
     */
    @Query("from User where id=?1")
    User findUserById(Long id);

    /**
     *
     */
    @Query("from User where username=?1")
    User findByUsername(String username);

    /**
     *
     */
    @Query("from User where del=false and username=?1")
    User findLoginUserByUsername(String username);

    /**
     *
     */
    @Query("from User where del=false and username=?1 and password=?2")
    User findLoginUserByUsernameAndPassword(String username, String password);

    /**
     *
     */
    @Query("from User where del=false and id in (select userId from UserToken where enable=true and token=?1)")
    User findLoginUserByToken(String token);

    /**
     *
     */
    @Modifying
    @Query("update User set del=:del,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateDel(@Param("id") Long id, @Param("del") boolean del, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);
}