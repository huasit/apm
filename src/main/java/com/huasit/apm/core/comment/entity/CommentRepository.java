package com.huasit.apm.core.comment.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.List;

/**
 *
 */
@Transactional
public interface CommentRepository extends CrudRepository<Comment, Long>, JpaSpecificationExecutor<Comment>  {

    /**
     *
     */
    @Query("from Comment where target=:target and targetId=:targetId order by createTime asc")
    List<Comment> findByTarget(@Param("target") String target, @Param("targetId") Long targetId);
}