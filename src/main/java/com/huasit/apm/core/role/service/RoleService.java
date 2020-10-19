package com.huasit.apm.core.role.service;

import com.huasit.apm.core.role.entity.Role;
import com.huasit.apm.core.role.entity.RoleRepository;
import com.huasit.apm.core.role.entity.RoleUser;
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
public class RoleService {

    /**
     *
     */
    public boolean checkUserHasRole(Long userId, String roleKey) {
        return this.roleRepository.checkUserHasRole(userId, roleKey) != null;
    }

    /**
     *
     */
    public Role getRoleById(Long id) {
        return this.roleRepository.findRoleById(id);
    }

    /**
     *
     */
    public Page<Role> list(Role form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.roleRepository.findAll(new Specification<Role>() {
            @Override
            public Predicate toPredicate(Root<Role> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
    public void save(Role form, User loginUser) {
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        if (form.getId() == null) {
            form.setCreatorId(form.getModifyId());
            form.setCreateTime(form.getModifyTime());
        } else {
            Role db = this.roleRepository.findRoleById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setRkey(db.getRkey());
            form.setName(db.getName());
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        if(form.getUsers() != null) {
            for(RoleUser u : form.getUsers()) {
                if(u.getUser() == null || u.getUser().getId() == null) {
                    continue;
                }
                User user = this.userRepository.findUserById(u.getUser().getId());
                u.setUser(user);
            }
        }
        this.roleRepository.save(form);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.roleRepository.updateDel(id, true, loginUser.getId(), new Date());
    }

    /**
     *
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     *
     */
    @Autowired
    UserRepository userRepository;
}