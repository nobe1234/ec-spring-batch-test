// FIXME:不要なコメントはpush時は削除しましょう
//package jp.co.sample.ecommerce_a.controller;
//
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.SessionAttributes;
//import org.springframework.web.bind.support.SessionStatus;
//
//import jp.co.sample.ecommerce_a.domain.User;
//
//@Controller
//@RequestMapping("/logout")
//@SessionAttributes("user")
//public class LogoutController {
//
//	@RequestMapping(value = "sessionInvalidate")
//	public String sessionInvalidate(User user, SessionStatus sessionStatus) {
//		sessionStatus.setComplete();
//		return "redirect:/showItem/index";
//	}
//
//}
