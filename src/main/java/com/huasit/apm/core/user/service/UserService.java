package com.huasit.apm.core.user.service;

import com.huasit.apm.core.user.entity.*;
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
import java.util.UUID;

/**
 *
 */
@Service
@Transactional
public class UserService {

    /**
     *
     */
    public User getUserById(Long id) {
        return this.userRepository.findUserById(id);
    }

    /**
     *
     */
    public UserLink getUserLinkById(Long id) {
        return this.userLinkRepository.findUserLinkById(id);
    }

    /**
     *
     */
    public Page<User> list(User form, int page, int pageSize, User loginUser) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize, Sort.by(Sort.Order.desc("id")));
        return this.userRepository.findAll(new Specification<User>() {
            @Override
            public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                predicates.add(cb.equal(root.get("del").as(boolean.class), false));
                predicates.add(cb.equal(root.get("thirdParty").as(boolean.class), form.isThirdParty()));
                if (form.getName() != null && !"".equals(form.getName())) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%"+  form.getName().trim() + "%"));
                }
                if(!loginUser.isAdmin()) {
                    predicates.add(cb.equal(root.get("id").as(Long.class), loginUser.getId()));
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
    public void save(User form, User loginUser) {
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        if (form.getId() == null) {
            form.setThirdParty(true);
            form.setCreatorId(form.getModifyId());
            form.setCreateTime(form.getModifyTime());
        } else {
            User db = this.userRepository.findUserById(form.getId());
            if (db == null || db.isDel()) {
                throw new SystemException(30000);
            }
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        User check = this.userRepository.findByUsername(form.getUsername());
        if(check != null && !check.getId().equals(form.getId())) {
            throw new SystemException(20100);
        }
        this.userRepository.save(form);
    }

    /**
     *
     */
    public void delete(Long id, User loginUser) {
        this.userRepository.updateDel(id, true, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public User getLoginUserByUsername(String username) {
        return this.userRepository.findLoginUserByUsername(username);
    }

    /**
     *
     */
    public User getLoginUserByUsernameAndPassword(String username, String password) {
        return this.userRepository.findLoginUserByUsernameAndPassword(username, password);
    }

    /**
     *
     */
    public User getLoginUserByToken(String token) {
        return this.userRepository.findLoginUserByToken(token);
    }

    /**
     *
     */
    public UserToken createUserToken(User user, String ip) {
        UserToken userToken = new UserToken();
        userToken.setCreateTime(new Date());
        userToken.setEnable(true);
        userToken.setToken(UUID.randomUUID().toString());
        userToken.setUserId(user.getId());
        userToken.setLoginIp(ip);
        this.userTokenRepository.save(userToken);
        return userToken;
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
    UserLinkRepository userLinkRepository;

    /**
     *
     */
    @Autowired
    UserTokenRepository userTokenRepository;
}