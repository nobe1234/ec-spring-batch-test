package jp.co.sample.ecommerce_a.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.OrderItem;
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

		Order order = orderRepository.findByUserIdAndStatusForShowCart(userId, status);
		List<OrderItem> orderItemList = order.getOrderList();
		if (orderItemList != null) {
			model.addAttribute("order", order);
		}

		// TODO:JSPとここのorderの動きを確認。orderItemが一件もないのに「カートに商品がありません」と出なかったらおかしい
		// もしかしたらJSPのforeach文の箇所をうまくいじればなんとかなるかもしれない。そもそもorderはなくならないのだから、orderITemで判定すれば良いのでは？？
		System.out.println("viewCartContent" + order);

		return "cart";
	}

}
