package com.huasit.apm.core.file.entity;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

/**
 *
 */
@Transactional
public interface FileRepository extends CrudRepository<File, Long>, JpaSpecificationExecutor<File>  {

}