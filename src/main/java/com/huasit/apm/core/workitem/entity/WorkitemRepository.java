package com.huasit.apm.core.workitem.entity;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface WorkitemRepository extends CrudRepository<Workitem, Long>, JpaSpecificationExecutor<Workitem> {

    /**
     *
     */
    @Query("select w from Workitem w where w.approver.id=?1")
    Page<Workitem> findByApproverId(Long approverId, Pageable pageable);

    /**
     *
     */
    @Query("select w from Workitem w where w.stage in (?1) and w.approver.id=?2")
    Page<Workitem> findByStagesAndApproverId(List<String> stages, Long approverId, Pageable pageable);

    /**
     *
     */
    @Query(value = "select a.target,a.target_id,a.stage,b.status,a.reach_time,cur from (select target,target_id,stage,max(reach_time) reach_time,cur from (select target,target_id,stage,reach_time,true cur from workitem union ALL select target,target_id,stage,reach_time,false cur from workitem_his) a where a.target=?1 and a.target_id=?2 group by target,target_id,stage) a left join flow b on a.target=b.target and a.stage=b.stage order by a.reach_time", nativeQuery = true)
    List<Object[]> findReachTime(String target, Long targetId);

    /**
     *
     */
    @Modifying
    @Query("delete from Workitem w where w.target=?1 and w.targetId=?2 and w.stage=?3")
    void delete(String target, Long targetId, String stage);
}