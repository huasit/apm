package com.huasit.apm.core.user.service;

import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserRepository;
import com.huasit.apm.core.user.entity.UserToken;
import com.huasit.apm.core.user.entity.UserTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
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
    UserTokenRepository userTokenRepository;
}