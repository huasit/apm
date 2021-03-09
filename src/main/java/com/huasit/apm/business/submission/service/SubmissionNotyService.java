package com.huasit.apm.business.submission.service;

import com.huasit.apm.business.submission.entity.SubmissionNoty;
import com.huasit.apm.business.submission.entity.SubmissionNotyRepository;
import com.huasit.apm.core.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class SubmissionNotyService {
    /**
     *
     */
    public SubmissionNoty getSubmissionNotyBySubmissionId(Long submissionId) {
        return this.submissionNotyRepository.findSubmissionNotyBySubmissionId(submissionId);
    }

    /**
     *
     */
    public Page<SubmissionNoty> list(SubmissionNoty form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.submissionNotyRepository.findAll(new Specification<SubmissionNoty>() {
            @Override
            public Predicate toPredicate(Root<SubmissionNoty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                if (predicates.size() > 0) {
                    Predicate[] array = new Predicate[predicates.size()];
                    query.where(predicates.toArray(array));
                }
                return query.getRestriction();
            }
        }, pageRequest);
    }

    /**
     *
     */
    public void save(SubmissionNoty form, User loginUser) {
        SubmissionNoty db;
        if (form.getId() != null) {
            db = this.submissionNotyRepository.findSubmissionNotyById(form.getId());
        } else  {
            db = this.submissionNotyRepository.findSubmissionNotyBySubmissionId(form.getSubmissionId());
        }
        if (db == null) {
            form.setCreatorId(loginUser.getId());
            form.setCreateTime(new Date());
        } else {
            form.setId(db.getId());
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        this.submissionNotyRepository.save(form);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.submissionNotyRepository.deleteById(id);
    }

    /**
     *
     */
    @Autowired
    SubmissionNotyRepository submissionNotyRepository;
}
