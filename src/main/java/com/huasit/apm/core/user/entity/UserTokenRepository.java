package com.huasit.apm.core.user.entity;

import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 *
 */
@Transactional
public interface UserTokenRepository extends CrudRepository<UserToken, Long>  {

}