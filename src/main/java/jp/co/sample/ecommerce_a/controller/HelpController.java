package jp.co.sample.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Q&Aを表示するコントローラ.
 * 
 * @author soheinobe
 *
 */
@Controller
@RequestMapping("/help")
public class HelpController {

	/**
	 * ヘルプ画面を表示する.
	 * 
	 * @return
	 */
	@RequestMapping("")
	public String toHelp() {
		return "help/loginQuestionAndAnswer";
	}
	
}
