package com.huasit.apm.business.construction.service;

import com.huasit.apm.business.construction.entity.Construction;
import com.huasit.apm.business.construction.entity.ConstructionRepository;
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
public class ConstructionService {

    /**
     *
     */
    public Page<Construction> list(Construction form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.constructionRepository.findAll(new Specification<Construction>() {
            @Override
            public Predicate toPredicate(Root<Construction> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("del").as(boolean.class), false));
                predicates.add(cb.equal(root.get("creatorId").as(Long.class), loginUser.getId()));
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
    public Construction getById(Long id) {
        return this.constructionRepository.findConstructionById(id);
    }

    /**
     *
     */
    public void add(Construction form, User loginUser) {
        form.setDel(false);
        form.setCreatorId(loginUser.getId());
        form.setCreateTime(new Date());
        form.setModifyId(form.getCreatorId());
        form.setModifyTime(form.getCreateTime());
        this.constructionRepository.save(form);
    }

    /**
     *
     */
    public void update(Construction form, User loginUser) {
        Construction db = this.constructionRepository.findConstructionById(form.getId());
        if(db == null) {
            return;
        }
        db.setName(form.getName());
        db.setContact(form.getContact());
        db.setTelphone(form.getTelphone());
        db.setModifyId(loginUser.getId());
        db.setModifyTime(new Date());
        this.constructionRepository.save(db);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        Construction construction = this.constructionRepository.findConstructionById(id);
        if(construction == null) {
            return;
        }
        construction.setDel(true);
        construction.setModifyId(loginUser.getId());
        construction.setModifyTime(new Date());
        this.constructionRepository.save(construction);
    }

    /**
     *
     */
    @Autowired
    ConstructionRepository constructionRepository;
}
