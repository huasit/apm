package com.huasit.apm.core.user.service;

import com.huasit.apm.business.thirdparty.entity.ThirdpartyRepository;
import com.huasit.apm.business.thirdparty.entity.UserLink;
import com.huasit.apm.business.thirdparty.entity.UserLinkRepository;
import com.huasit.apm.core.role.entity.Role;
import com.huasit.apm.core.role.entity.RoleGroupUser;
import com.huasit.apm.core.role.entity.RoleGroupUserRepository;
import com.huasit.apm.core.role.entity.RoleRepository;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserRepository;
import com.huasit.apm.core.user.entity.UserToken;
import com.huasit.apm.core.user.entity.UserTokenRepository;
import com.huasit.apm.system.exception.SystemException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

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
        User user = this.userRepository.findUserById(id);
        if(user != null) {
            user.setRoleGroups(this.roleGroupUserRepository.findByUserId(id));
        }
        return user;
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
                if (form.getName() != null && !"".equals(form.getName())) {
                    predicates.add(cb.like(root.get("name").as(String.class), "%"+  form.getName().trim() + "%"));
                }
                if(form.getThirdpartyId() != null) {
                    predicates.add(cb.equal(root.get("thirdparty").get("id").as(Long.class), form.getThirdpartyId()));
                }
                if(!CollectionUtils.isEmpty(form.getTypes())) {
                    predicates.add((root.get("type").as(User.Type.class).in(form.getTypes())));
                }
                if(!CollectionUtils.isEmpty(form.getStates())) {
                    predicates.add((root.get("state").as(User.State.class).in(form.getStates())));
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
    public List<User> getByThirdpartyId(Long thirdpartyId, User loginUser) {
        return this.userRepository.findByThirdpartyId(thirdpartyId);
    }

    /**
     *
     */
    public void save(User form, User loginUser) {
        form.setModifyId(loginUser.getId());
        form.setModifyTime(new Date());
        if (form.getId() == null) {
            form.setCreatorId(form.getModifyId());
            form.setCreateTime(form.getModifyTime());
        } else {
            User db = this.userRepository.findUserById(form.getId());
            if (db == null) {
                throw new SystemException(30000);
            }
            if(StringUtils.isEmpty(form.getPassword())) {
                form.setPassword(db.getPassword());
            }
            form.setCreatorId(db.getCreatorId());
            form.setCreateTime(db.getCreateTime());
        }
        User check = this.userRepository.findByUsername(form.getUsername());
        if(check != null && !check.getId().equals(form.getId())) {
            throw new SystemException(20100);
        }
        if(form.getThirdparty() != null && form.getThirdparty().getId() != null) {
            form.setThirdparty(this.thirdpartyRepository.findThirdpartyById(form.getThirdparty().getId()));
        } else {
            form.setThirdparty(null);
        }
        this.userRepository.save(form);
        if(form.getRoleGroups() != null && form.getRoleGroups().size() > 0) {
            this.roleGroupUserRepository.deleteByUserId(form.getId());
            for(RoleGroupUser gu : form.getRoleGroups()) {
                gu.setUserId(form.getId());
            }
            this.roleGroupUserRepository.saveAll(form.getRoleGroups());
        }
    }

    /**
     *
     */
    public void updatePassword(String password, User loginUser) {
        this.userRepository.updatePassword(loginUser.getId(), password, loginUser.getId(), new Date());
    }

    /**
     *
     */
    public void updateState(Long id,User.State state, User loginUser) {
        this.userRepository.updateState(id, state, loginUser.getId(), new Date());
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
    public List<Role> getUserRoles(Long userId) {
        return this.roleRepository.findByUserId(userId);
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
    RoleRepository roleRepository;

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

    /**
     *
     */
    @Autowired
    ThirdpartyRepository thirdpartyRepository;

    /**
     *
     */
    @Autowired
    RoleGroupUserRepository roleGroupUserRepository;
}