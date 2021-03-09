package com.huasit.apm.business.bid.service;

import com.huasit.apm.business.bid.entity.BidNoty;
import com.huasit.apm.business.bid.entity.BidNotyRepository;
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
public class BidNotyService {
    /**
     *
     */
    public BidNoty getBidNotyByBidId(Long bidId) {
        return this.bidNotyRepository.findBidNotyByBidId(bidId);
    }

    /**
     *
     */
    public Page<BidNoty> list(BidNoty form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.bidNotyRepository.findAll(new Specification<BidNoty>() {
            @Override
            public Predicate toPredicate(Root<BidNoty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public void save(BidNoty form, User loginUser) {
        BidNoty db;
        if (form.getId() != null) {
            db = this.bidNotyRepository.findBidNotyById(form.getId());
        } else  {
            db = this.bidNotyRepository.findBidNotyByBidId(form.getBidId());
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
        this.bidNotyRepository.save(form);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.bidNotyRepository.deleteById(id);
    }

    /**
     *
     */
    @Autowired
    BidNotyRepository bidNotyRepository;
}
