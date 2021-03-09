package com.huasit.apm.core.workitem.entity;

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
public interface WorkitemHisRepository extends CrudRepository<WorkitemHis, Long>, JpaSpecificationExecutor<WorkitemHis>  {

    /**
     *
     */
    @Query("from WorkitemHis w where w.id in (select max(id) from WorkitemHis where stage!='apply' and approver.id=?1 group by target,targetId)")
    Page<WorkitemHis> findByApproverId(Long approverId, Pageable pageable);


    /**
     *
     */
    @Query("from WorkitemHis w where w.id in (select max(id) from WorkitemHis where stage=?1 and approver.id=?2 group by target,targetId)")
    Page<WorkitemHis> findByStageAndApproverId(String stage, Long approverId,Pageable pageable);
}