package com.huasit.apm.core.user.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 *
 */
@Transactional
public interface UserLinkRepository extends CrudRepository<UserLink, Long>, JpaSpecificationExecutor<User>  {

    /**
     *
     */
    @Query("from UserLink where id=?1")
    UserLink findUserLinkById(Long id);
}