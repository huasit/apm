package com.huasit.apm.core.user.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

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
    @Query("from User where del=false and login=true and username=?1 and password=?2")
    User findLoginUserByUsernameAndPassword(String username, String password);

    /**
     *
     */
    @Query("from User where del=false and login=true and id in (select userId from UserToken where enable=true and token=?1)")
    User findLoginUserByToken(String token);
}