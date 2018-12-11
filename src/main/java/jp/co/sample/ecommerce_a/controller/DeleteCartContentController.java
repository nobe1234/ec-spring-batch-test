package jp.co.sample.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.repository.OrderItemRepository;

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
	
	/**
	 * idを指定してショッピングカートから商品を削除するメソッド.
	 * 
	 * @param id order_itemのid
	 * @return ショッピングカート一覧表示ページ
	 */
	@RequestMapping("/delete")
	private String delete(Integer orderItemId) {
		
		// FIXME:デバッグ用のsysoutはpush時は削除しましょう
		System.out.println(orderItemId);
		orderItemRepository.deleteById(orderItemId);
		
		
		return "redirect:/viewCartContent/view";
	}
	
}
