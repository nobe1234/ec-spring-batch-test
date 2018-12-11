package jp.co.sample.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.repository.OrderRepository;
import jp.co.sample.ecommerce_a.repository.UserRepository;

@Controller
@RequestMapping("/deleteUser")
// FIXME:javadoc漏れ
public class DeleteUserController {

	@Autowired
	public OrderRepository orderRepository;
	
	@Autowired
	public UserRepository userRepository;
	
	@RequestMapping("/delete")
	public String delete() {
		return "unsubscribe";
	}
	
	@RequestMapping("/deleteUser")
	public String deleteUser(@AuthenticationPrincipal LoginUser loginUser) {
		
		Integer id = loginUser.getUser().getId();
		
		userRepository.deleteById(id);
		orderRepository.deleteByUserId(id);
		
		return "unsubscribeComplete";
	}
	
}
