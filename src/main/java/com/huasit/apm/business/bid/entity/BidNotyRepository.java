package com.huasit.apm.business.bid.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface BidNotyRepository extends CrudRepository<BidNoty, Long>, JpaSpecificationExecutor<BidNoty> {

    /**
     *
     */
    @Query("from BidNoty where id=:id")
    BidNoty findBidNotyById(@Param("id") Long id);
    /**
     *
     */
    @Query("from BidNoty where bidId=:bidId")
    BidNoty findBidNotyByBidId(@Param("bidId") Long bidId);
}
