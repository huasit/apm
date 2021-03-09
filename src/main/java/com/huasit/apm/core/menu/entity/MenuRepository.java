package com.huasit.apm.core.menu.entity;

import com.huasit.apm.core.user.entity.User;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface MenuRepository extends CrudRepository<Menu, Long>, JpaSpecificationExecutor<User>  {

    /**
     *
     */
    @Query("from Menu where del=false and auth in (?1) order by orderIndex asc")
    List<Menu> findByAuth(List<Integer> auths);

    /**
     *
     */
    @Query("from Menu where del=false order by orderIndex asc")
    List<Menu> findAll();

    /**
     *
     */
    @Query("from Menu where id=:id")
    Menu findMenuById(Long id);
}