package com.huasit.apm.core.flow.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface FlowRepository extends CrudRepository<Flow, Long>, JpaSpecificationExecutor<Flow>  {

    /**
     *
     */
    @Query("from Flow")
    List<Flow> findAll();

    /**
     *
     */
    List<Flow> findByTarget(String target);
}