package com.huasit.apm.business.bid.entity;

import com.huasit.apm.business.thirdparty.entity.Thirdparty;
import com.huasit.apm.core.user.entity.User;
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
    @Query(nativeQuery = true, value = "SELECT max(convert(SUBSTRING(audit_no,5,3),signed))+1 FROM bid where audit_no like ?1")
    Integer findMaxAuditNo(String prefix);

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
    @Query("update Bid set status=:status,assigned=:assigned,thirdparty=:thirdparty,auditType=:auditType,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateStatusAndAssigned(@Param("id") Long id, @Param("status") int status, @Param("assigned") User assigned, @Param("thirdparty") Thirdparty thirdparty, @Param("auditType") String auditType, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Modifying
    @Query("update Bid set status=:status,memberIds=:memberIds,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateStatusAndMemberIds(@Param("id") Long id, @Param("status") int status, @Param("memberIds") String memberIds, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);

    /**
     *
     */
    @Modifying
    @Query("update Bid set status=:status,prepareViewDate=:prepareViewDate,modifyId=:modifyId,modifyTime=:modifyTime where id=:id")
    void updateWhileSurveyPrepare(@Param("id") Long id, @Param("status") int status, @Param("prepareViewDate") Date prepareViewDate, @Param("modifyId") Long modifyId, @Param("modifyTime") Date modifyTime);
}
