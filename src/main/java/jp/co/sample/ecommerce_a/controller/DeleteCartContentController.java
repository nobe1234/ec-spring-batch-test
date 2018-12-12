package jp.co.sample.ecommerce_a.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.OrderItem;
import jp.co.sample.ecommerce_a.repository.OrderItemRepository;
import jp.co.sample.ecommerce_a.repository.OrderRepository;
import jp.co.sample.ecommerce_a.service.Userservice;

/**
 * ショッピングカートから商品を削除するコントローラ.
 * 
 * @author junpei.oyama
 *
 */
@Controller
@RequestMapping("/delete")
public class DeleteCartContentController {

	@Autowired
	private OrderItemRepository orderItemRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private Userservice userService;

	/**
	 * idを指定してショッピングカートから商品を削除するメソッド.
	 * 
	 * @param id order_itemのid
	 * @return ショッピングカート一覧表示ページ
	 */
	@RequestMapping("/delete")
	private String delete(Integer orderItemId, @AuthenticationPrincipal LoginUser loginUser) {
		orderItemRepository.deleteById(orderItemId);

		// もし、注文した商品を全て消した場合、注文テーブルも削除しなければならない。
		Integer userId = userService.loginCheckUser(loginUser);
		List<Order> orderList = new ArrayList<>();
		orderList = orderRepository.findByOwnAllOrder(userId, false);

		List<OrderItem> orderItemList = null;
		for (Order order : orderList) {
			orderItemList = order.getOrderList();
		}

		if (orderItemList == null) {
			orderRepository.deleteByUserId(userId);
		}

		return "redirect:/viewCartContent/view";
	}

}
