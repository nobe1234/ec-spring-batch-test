package jp.co.sample.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 会社＆商品紹介ページ関連のコントローラ.
 * 
 * @author soheinobe
 * 
 *
 */
@RequestMapping("/introductionCompany")
@Controller
public class IntroductionCompanyController {

	/**
	 * 会社＆商品紹介ページを表示する.
	 * 
	 * @return
	 */
	@RequestMapping("/index")
	public String index() {
		return "help/cm";
	}

}
