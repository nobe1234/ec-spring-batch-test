package jp.co.sample.ecommerce_a.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.service.OrderService;

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

	/**
	 * 注文履歴を表示する.
	 * 
	 * ログイン中の情報を使って、データベースからユーザ自身の履歴を引き出す。
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String toOrderHistory() {

		return null;
	}

}
