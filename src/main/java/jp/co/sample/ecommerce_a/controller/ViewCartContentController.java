package jp.co.sample.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.User;
import jp.co.sample.ecommerce_a.repository.OrderRepository;

/**
 * カートの中身を表示するコントローラ.
 * 
 * @author junpei.oyama
 *
 */
@Controller
@RequestMapping("/viewCartContent")
public class ViewCartContentController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private HttpSession session;

	/**
	 * ショッピングカートの中身を表示するメソッド.
	 * 
	 * @param model     モデル
	 * @param loginUser ログインしているユーザー情報
	 * @return ショッピングカート画面
	 */
	@RequestMapping("/view")
	public String viewCart(Model model, @AuthenticationPrincipal LoginUser loginUser) {

//		User user = loginUser.getUser();

		Integer userId;
		if (loginUser == null) {
			userId = session.getId().hashCode();
		} else {
			User user = loginUser.getUser();
			userId = user.getId();
		}

		Integer status = 0;

		// FIXME:testというコメントは後から見た人に混乱を与えそう
		// test
		Order order = orderRepository.findByUserIdAndStatusForShowCart(userId, status);
//		System.out.println("----viewcartのorderid-----");
//		System.out.println(order.getId());
//		System.out.println("----viewcartのorderid-----");
		// FIXME:不要なコメントはpush時は削除しましょう
//		Order order = orderRepository.findByUserIdAndStatusForShowCart(user.getId(), 0);

//		List<Order> orderList = orderRepository.findByUserIdAndStatusForShowCart(userId, status);
//		List<OrderItem> orderList = order.getOrderList();
//		System.out.println("orderList" + orderList);

		model.addAttribute("order", order);
		System.out.println("viewCartContent" + order);

		return "cart";
	}

}
