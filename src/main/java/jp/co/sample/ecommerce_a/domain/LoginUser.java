package jp.co.sample.ecommerce_a.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

import jp.co.sample.ecommerce_a.domain.User;

/**
 * 利用者のログイン情報を格納するエンティティ.
 * 
 * @author soheinobe
 *
 */
public class LoginUser extends org.springframework.security.core.userdetails.User {

	private static final long serialVersionUID = 1L;

	/** ユーザー情報 */
	private final User user;

	public LoginUser(User user, Collection<GrantedAuthority> authorityList) {
		super(user.getEmail(), user.getPassword(), authorityList);
		this.user = user;
	}

	public User getUser() {
		return user;
	}

}
