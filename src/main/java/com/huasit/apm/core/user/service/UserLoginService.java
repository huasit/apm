package com.huasit.apm.core.user.service;

import com.huasit.apm.system.util.WebUtil;
import com.huasit.apm.core.user.entity.User;
import com.huasit.apm.core.user.entity.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

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
			return user;
		}
		String token = WebUtil.getCookies(request, TOKEN_IN_COOKIE);
		if (token == null || "".equals(token)) {
			return null;
		}
		user = this.userService.getLoginUserByToken(token);
		request.setAttribute(USER_IN_REQUEST, user);
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
	public boolean checkLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getRequestURI().contains("/login/")) {
			return true;
		}
		User loginUser = this.getLoginUser(request);
		if (loginUser != null) {
			return true;
		}
		response.sendRedirect(contextPath + "/login/");
		return false;
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
}