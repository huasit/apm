package com.huasit.apm.core.dept.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface DeptRepository extends CrudRepository<Dept, Long>, JpaSpecificationExecutor<Dept>  {

    /**
     *
     */
    @Query("from Dept")
    List<Dept> findAll();
}