package com.huasit.apm.core.user.service;

import com.huasit.apm.core.role.service.RoleService;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserRepository;
import com.huasit.apm.core.user.entity.UserToken;
import com.huasit.apm.system.exception.SystemException;
import com.huasit.apm.system.util.WebUtil;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.Date;

/**
 *
 */
@Service
@Transactional
public class UserLoginService {

	/**
	 *
	 */
	@Value("${server.context-path:}")
	private String contextPath;

	/**
	 *
	 */
	public static final String USER_IN_REQUEST = "loginUser";

	/**
	 *
	 */
	public static final String TOKEN_IN_COOKIE = "apm_token";

	/**
	 *
	 */
	public static final String USERNAME_IN_COOKIE = "apm_user";

	/**
	 *
	 */
	public User getLoginUser(HttpServletRequest request) {
		User user = (User) request.getAttribute(USER_IN_REQUEST);
		if (null != user) {
			return this.checkUserRole(user);
		}
		String token = request.getHeader("Authorization");
		if(token != null && token.startsWith("Bearer ")) {
			token = token.substring(7);
		} else {
			token = WebUtil.getCookies(request, TOKEN_IN_COOKIE);
		}
		if (token == null || "".equals(token)) {
			return null;
		}
		user = this.userService.getLoginUserByToken(token);
		request.setAttribute(USER_IN_REQUEST, user);
		return this.checkUserRole(user);
	}

	/**
	 *
	 */
	public User checkUserRole(User user) {
		if(user == null) {
			return null;
		}
		if(new Long(1).equals(user.getId()) || this.roleService.checkUserHasRole(user.getId(), "admin")) {
			user.setAdmin(true);
		}
		return user;
	}

	/**
	 *
	 */
	public User userLogin(String username, String password, HttpServletRequest request) {
		User user = this.userService.getLoginUserByUsernameAndPassword(username, password);
		if (user == null) {
			return null;
		}
		UserToken userToken = this.userService.createUserToken(user, WebUtil.getIpAddress(request));
		user.setToken(userToken);
		return user;
	}

	/**
	 *
	 */
	public User userLoginByThirdParty(AttributePrincipal principal, HttpServletRequest request, HttpServletResponse response) throws Exception {
		User user = this.userService.getLoginUserByUsername(principal.getName());
		BASE64Decoder decoder = new BASE64Decoder();
		if (user == null) {
			Date now = new Date();
			user = new User();
			user.setDel(false);
			user.setThirdParty(false);
			user.setCreatorId(0L);
			user.setCreateTime(now);
			user.setModifyId(0L);
			user.setModifyTime(now);
			user.setUsername(principal.getName());
			user.setPassword("***");
			user.setName(new String(decoder.decodeBuffer(principal.getAttributes().get("ACPNAME").toString()), "UTF-8"));
			user.setEmail("");
			user.setTelphone("");
			this.userRepository.save(user);
		}
		UserToken userToken = this.userService.createUserToken(user, WebUtil.getIpAddress(request));
		user.setToken(userToken);
		this.setCookie(user, request, response);
		return user;
	}

	/**
	 *
	 */
	public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getRequestURI().contains("/login/")) {
			return true;
		}
		User loginUser = this.getLoginUser(request);
		if (loginUser != null) {
			return true;
		}
		throw new SystemException(10000);
	}

	/**
	 *
	 */
	public void setCookie(User loginUser, HttpServletRequest request, HttpServletResponse response) throws Exception {
		WebUtil.addCookie(response, USERNAME_IN_COOKIE, loginUser.getUsername());
		WebUtil.addCookie(response, TOKEN_IN_COOKIE, loginUser.getToken().getToken());
		request.setAttribute(USER_IN_REQUEST, loginUser);
	}

	/**
	 *
	 */
	public void removeCookie(HttpServletRequest request, HttpServletResponse response) {
		request.removeAttribute(USER_IN_REQUEST);
		WebUtil.cleanCookies(response, TOKEN_IN_COOKIE);
		WebUtil.cleanCookies(response, USERNAME_IN_COOKIE);
	}

	/**
	 *
	 */
	@Autowired
	UserService userService;

	/**
	 *
	 */
	@Autowired
	RoleService roleService;

	/**
	 *
	 */
	@Autowired
	UserRepository userRepository;
}