package jp.co.sample.ecommerce_a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.service.OrderService;
import jp.co.sample.ecommerce_a.service.Userservice;

/**
 * 注文履歴を表示するコントローラー.
 * 
 * @author soheinobe
 *
 */
@RequestMapping("/orderHistory")
@Controller
public class OrderHistoryController {

	/** 注文関連の処理を行う、サービスクラスを注入. */
	@Autowired
	private OrderService orderService;

	@Autowired
	private Userservice userService;

	/**
	 * 注文履歴を表示する.
	 * 
	 * ログイン中の情報を使って、データベースからユーザ自身の履歴を引き出す。
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String toOrderHistory(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		Integer userId = userService.loginCheckUser(loginUser);

		List<Order> orderList = new ArrayList<>();
		orderList = orderService.findByOwnAllOrder(userId);

		model.addAttribute("orderList", orderList);

		// TODO:JSPを作成し、フォワード先を指定
		return "orderHistory";
	}

}
