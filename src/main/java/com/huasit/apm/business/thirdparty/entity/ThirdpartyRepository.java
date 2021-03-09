package com.huasit.apm.business.thirdparty.entity;


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
public  interface ThirdpartyRepository extends CrudRepository<Thirdparty, Long>, JpaSpecificationExecutor<Thirdparty> {


    /**
     *
     */
    @Query("from Thirdparty where id=:id")
    Thirdparty findThirdpartyById(@Param("id") Long id);

    /**
     *
     */
    @Modifying
    @Query("update Thirdparty t set t.del=:del,t.modifyId=:modifyId,t.modifyTime=:modifyTime where t.id=:id")
    void updateDel(@Param("id") Long id, @Param("del") boolean del, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);
}
