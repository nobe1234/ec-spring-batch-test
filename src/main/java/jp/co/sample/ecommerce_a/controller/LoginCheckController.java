package jp.co.sample.ecommerce_a.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;

/**
 * 注文画面を表示するコントローラー.
 * 
 * @author yume.hirata
 *
 */
@Controller
@RequestMapping("/loginCheck")
public class LoginCheckController {
		
	/**
	 * ユーザーが非ログイン時、ログイン画面に遷移する.
	 * （ログイン時の振り分け先は考慮しない 1127）
	 * 
	 * @return	ログイン画面/注文確認画面
	 */
	@RequestMapping("/")
	public String loginCheck(@AuthenticationPrincipal LoginUser loginUser){
		if(loginUser == null) {
			return "login";
		}
		return "redirect:/order/toOrder";
	}

}