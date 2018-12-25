package jp.co.sample.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.repository.OrderRepository;
import jp.co.sample.ecommerce_a.repository.UserRepository;

/**
 * 退会処理関連を行うコントローラ.
 * 
 * @author soheinobe
 *
 */
@Controller
@RequestMapping("/deleteUser")
public class DeleteUserController {

	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	public UserRepository userRepository;

	/**
	 * 退会ページを表示する.
	 * 
	 * @return 退会ページ
	 */
	@RequestMapping("/delete")
	public String delete() {
		return "unsubscribe";
	}

	/**
	 * ユーザーの削除、退会完了ページを表示する.
	 * 
	 * @param loginUser
	 * @return 退会完了画面
	 */
	@RequestMapping("/deleteUser")
	public String deleteUser(@AuthenticationPrincipal LoginUser loginUser) {

		Integer id = loginUser.getUser().getId();

		userRepository.deleteById(id);
		orderRepository.deleteByUserId(id);

		return "unsubscribeComplete";
	}

}
