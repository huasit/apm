package com.huasit.apm.business.submission.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

@Transactional
public interface SubmissionNotyRepository extends CrudRepository<SubmissionNoty, Long>, JpaSpecificationExecutor<SubmissionNoty> {

    /**
     *
     */
    @Query("from SubmissionNoty where id=:id")
    SubmissionNoty findSubmissionNotyById(@Param("id") Long id);
    /**
     *
     */
    @Query("from SubmissionNoty where submissionId=:submissionId")
    SubmissionNoty findSubmissionNotyBySubmissionId(@Param("submissionId") Long submissionId);
}
