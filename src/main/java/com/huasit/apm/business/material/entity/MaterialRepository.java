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
public interface MaterialRepository extends CrudRepository<Material, Long>, JpaSpecificationExecutor<Material>  {

    /**
     *
     */
    @Query("from Material where del=false order by id desc")
    List<Material> findAll();

    /**
     *
     */
    @Query("from Material where id=?1")
    Material findMaterialById(Long id);
}