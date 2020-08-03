package com.huasit.apm.business.material.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface MaterialGroupRepository extends CrudRepository<MaterialGroup, Long>, JpaSpecificationExecutor<Material>  {

    /**
     *
     */
    @Query("from MaterialGroup where del=false order by id desc")
    List<MaterialGroup> findAll();

    /**
     *
     */
    @Query("from MaterialGroup where id=?1")
    MaterialGroup findMaterialById(Long id);
}