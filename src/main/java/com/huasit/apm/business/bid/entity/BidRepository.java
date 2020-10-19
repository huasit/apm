package com.huasit.apm.business.bid.entity;

import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserLink;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Date;

@Transactional
public interface BidRepository extends CrudRepository<Bid, Long>, JpaSpecificationExecutor<Bid> {

    /**
     *
     */
    @Query("from Bid where id=:id")
    Bid findBidById(@Param("id") Long id);

    /**
     *
     */
    @Query("select max(auditNo) from Bid where auditNo is not null")
    String findMaxAuditNo();

    /**
     *
     */
    @Modifying
    @Query("update Bid set del=:del,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateDel(@Param("id") Long id, @Param("del") boolean del, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Modifying
    @Query("update Bid set status=:status,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateStatus(@Param("id") Long id, @Param("status") int status, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Modifying
    @Query("update Bid set status=:status,auditNo=:auditNo,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateStatusAndAuditNo(@Param("id") Long id, @Param("status") int status, @Param("auditNo") String auditNo, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Modifying
    @Query("update Bid set status=:status,assigned=:assigned,assignedLink=:assignedLink,auditType=:auditType,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateStatusAndAssigned(@Param("id") Long id, @Param("status") int status, @Param("assigned") User assigned, @Param("assignedLink") UserLink assignedLink, @Param("auditType") String auditType, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Modifying
    @Query("update Bid set status=:status,prepareViewDate=:prepareViewDate,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateWhileSurveyPrepare(@Param("id") Long id, @Param("status") int status, @Param("prepareViewDate") Date prepareViewDate, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);
}
