package jp.co.sample.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 505ページへ飛ばすコントローラ.
 * 
 * @author soheinobe
 *
 */
@Controller
@RequestMapping("/common")
public class CommonController {

	/**
	 * エラー画面に遷移する.
	 * 
	 * @return 遷移先画面
	 */
	@RequestMapping("/maintenance")
	public String maintenance() {
		return "error/505error";
	}
}
