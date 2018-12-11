package jp.co.sample.ecommerce_a.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.OrderItem;
import jp.co.sample.ecommerce_a.domain.OrderTopping;
import jp.co.sample.ecommerce_a.domain.User;
import jp.co.sample.ecommerce_a.form.InsertItemForm;
import jp.co.sample.ecommerce_a.repository.IdGenerator;
import jp.co.sample.ecommerce_a.repository.OrderItemRepository;
import jp.co.sample.ecommerce_a.repository.OrderRepository;
import jp.co.sample.ecommerce_a.repository.OrderToppingRepository;

/**
 * 商品をカートに追加するコントローラ.
 * 
 * @author junpei.oyama
 *
 */
@Controller
@RequestMapping("/insertItem")
public class InsertItemController {

	@Autowired
	public OrderRepository orderRepository;

	@Autowired
	public OrderItemRepository orderItemRepository;

	@Autowired
	public OrderToppingRepository orderToppingRepository;

	@Autowired
	private IdGenerator idGenerator;

	@Autowired
	public HttpSession session;

	@ModelAttribute
	public InsertItemForm setUpInsertItemForm() {
		return new InsertItemForm();
	}

	/**
	 * 商品をカートに追加するメソッド.
	 * 
	 * @param form 入力情報を受け取るフォーム
	 * @return 注文画面
	 */
	@RequestMapping("/insert")
	public String insertCart(InsertItemForm form, Model model, @AuthenticationPrincipal LoginUser loginUser) {
		// FIXME:不要なコメントはpush時は削除しましょう
//		Order order = new Order();
//		System.out.println(1 + "   " + order);
//		System.out.println(user.getId());
//		
//		if(user.getId() != null) {
//			order = orderRepository.findByUserIdAndStatus(user.getId(), 0);
//		}

//		User user = loginUser.getUser();

		Integer userId;
		if (loginUser == null) {
			userId = session.getId().hashCode();
		} else {
			User user = loginUser.getUser();
			userId = user.getId();
		}

		int status = 0;
		int totalPrice = 0;

//		System.out.println(user.getName());
//		System.out.println(user.getId());

		Order order = orderRepository.findByUserIdAndStatus(userId, status);

		if (order == null) {
			order = new Order();
			System.out.println("--------------------------------------------------------------------");
//			if(user.getId() == null) {
//				order.setUserId((session.getId()).hashCode());				
//			}
//			order.setUserId((session.getId()).hashCode());
//			System.out.println("----INSERT ITEMのorderid-----");
//			System.out.println(order.getId());
//			System.out.println("----INSERT ITEMのorderid-----");

			order.setUserId(userId);
			order.setStatus(status);

			// totalPriceは注文完了時にupdateする.ここではとりあえず0を入れておく
			order.setTotalPrice(totalPrice);

			if (order.getId() == null) {
				String OrderNumber = idGenerator.genetateId(); // TODO:createSeqenceを作成
				order.setOrder_number(OrderNumber);
				// order.set; // TODO:シーケンス操作をリポジトる
				orderRepository.insert(order);
			} else {
				orderRepository.updateReturn(order);
			}
		}
		System.out.println(order);

		OrderItem orderItem = new OrderItem();
		orderItem.setOrderId(order.getId());
		System.out.println("----INSERT ITEMのordernumber-----");
		System.out.println(order.getOrder_number());
		System.out.println("----INSERT ITEMのordernumber-----");

		orderItem.setItemId(form.getItemId());
		orderItem.setSize(form.getSize());
		orderItem.setQuantity(form.getQuantity());

		// test
//		orderItem.setItemId(1);
//		orderItem.setSize('M');
//		orderItem.setQuantity(1);

//		orderItem.setId(orderItemRepository.insert(orderItem).getId());
		orderItemRepository.insert(orderItem);

		for (Integer toppingId : form.getToppingList()) {
			OrderTopping orderTopping = new OrderTopping();
			orderTopping.setOrderItemId(orderItem.getId());
			orderTopping.setToppingId(toppingId);
			orderToppingRepository.insert(orderTopping);
		}

		System.out.println(form.getToppingList());

		// test
//		List<Integer> testList = new LinkedList<>();
//		testList.add(1);
//		testList.add(2);		

		// test
//		for (Integer toppingId : testList) {
//			orderTopping.setOrderItemId(orderItem.getId());
//			orderTopping.setToppingId(toppingId);
//			orderToppingRepository.insert(orderTopping);
//		}

		return "redirect:/viewCartContent/view";
	}

}
