package com.huasit.apm.business.thirdparty.service;

import com.huasit.apm.business.thirdparty.entity.Thirdparty;
import com.huasit.apm.business.thirdparty.entity.ThirdpartyRepository;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.system.exception.SystemException;
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
public class ThirdpartyService {

    /**
     *
     */
    public Thirdparty getThirdpartyById(Long id) {
        return this.thirdpartyRepository.findThirdpartyById(id);
    }

    /**
     *
     */
    public Page<Thirdparty> list(Thirdparty form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.thirdpartyRepository.findAll(new Specification<Thirdparty>() {
            @Override
            public Predicate toPredicate(Root<Thirdparty> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("del").as(boolean.class), false));
                if (form.getName() != null && !"".equals(form.getName())) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%"+  form.getName().trim() + "%"));
                }
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
    public void save(Thirdparty form, User loginUser) {
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        if (form.getId() == null) {
            form.setCreatorId(form.getModifyId());
            form.setCreateTime(form.getModifyTime());
        } else {
            Thirdparty db = this.thirdpartyRepository.findThirdpartyById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        this.thirdpartyRepository.save(form);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.thirdpartyRepository.updateDel(id, true, loginUser.getId(), new Date());
    }

    /**
     *
     */
    @Autowired
    ThirdpartyRepository thirdpartyRepository;
}
