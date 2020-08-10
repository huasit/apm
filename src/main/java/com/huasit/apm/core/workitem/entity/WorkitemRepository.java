package com.huasit.apm.core.workitem.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 *
 */
@Transactional
public interface WorkitemRepository extends CrudRepository<Workitem, Long>, JpaSpecificationExecutor<Workitem>  {

    /**
     *
     */
    @Query("from Workitem w where w.approver.id=:approverId")
    Page<Workitem> findByApproverId(@Param("approverId") Long approverId, Pageable pageable);
}