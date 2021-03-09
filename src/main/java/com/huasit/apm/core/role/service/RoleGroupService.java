package com.huasit.apm.core.role.service;

import com.huasit.apm.core.role.entity.*;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserRepository;
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
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleGroupService {

    /**
     *
     */
    public RoleGroup getRoleGroupById(Long id) {
        RoleGroup roleGroup = this.roleGroupRepository.findRoleGroupById(id);
        if(roleGroup != null) {
            roleGroup.setRoles(this.roleGroupRoleRepository.findByGroupId(id));
        }
        return roleGroup;
    }

    /**
     *
     */
    public Page<RoleGroup> list(RoleGroup form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.roleGroupRepository.findAll(new Specification<RoleGroup>() {
            @Override
            public Predicate toPredicate(Root<RoleGroup> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public void save(RoleGroup form, User loginUser) {
        if (form.getId() == null) {
            form.setCreatorId(loginUser.getId());
            form.setCreateTime(new Date());
        } else {
            RoleGroup db = this.roleGroupRepository.findRoleGroupById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        this.roleGroupRepository.save(form);
        this.roleGroupRoleRepository.deleteByGroupId(form.getId());
        if(form.getRoles() != null && form.getRoles().size() > 0) {
            for(RoleGroupRole gr : form.getRoles()) {
                gr.setGroupId(form.getId());
            }
            this.roleGroupRoleRepository.saveAll(form.getRoles());
        }
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.roleGroupRepository.updateDel(id, true, loginUser.getId(), new Date());
    }

    /**
     *
     */
    @Autowired
    UserRepository userRepository;

    /**
     *
     */
    @Autowired
    RoleGroupRepository roleGroupRepository;

    /**
     *
     */
    @Autowired
    RoleGroupRoleRepository roleGroupRoleRepository;

    /**
     *
     */
    @Autowired
    RoleGroupUserRepository roleGroupUserRepository;
}