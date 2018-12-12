package jp.co.sample.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.User;
import jp.co.sample.ecommerce_a.repository.UserRepository;

/**
 * 実際の処理を行うサービスクラス.
 * 
 * パスワードのハッシュ化や照合など。
 * 
 * @author soheinobe
 *
 */
@Service
public class Userservice {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	/**
	 * ユーザーを登録するメソッド.
	 * 
	 * @param user
	 */
	public void insert(User user) {
		userRepository.insert(user);
	}

	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public Integer loginCheckUser(@AuthenticationPrincipal LoginUser loginUser) {
		User user = loginUser.getUser();
		Integer userId = user.getId();
		return userId;
	}

	/**
	 * 入力されたパスワードを暗号化する.
	 * 
	 * @param rawPassword 暗号化前のパスワード(元のパスワード)
	 * @return 暗号化後のパスワード
	 */
	public String encodePassword(String rawPassword) {
		String encodedPassword = passwordEncoder.encode(rawPassword);
		return encodedPassword;
	}

}
