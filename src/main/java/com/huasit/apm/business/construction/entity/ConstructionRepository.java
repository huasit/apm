package com.huasit.apm.business.construction.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface ConstructionRepository extends CrudRepository<Construction, Long>, JpaSpecificationExecutor<Construction>  {

    /**
     *
     */
    @Query("from Construction where del=false order by id desc")
    List<Construction> findAll();

    /**
     *
     */
    @Query("from Construction where id=?1")
    Construction findConstructionById(Long id);
}