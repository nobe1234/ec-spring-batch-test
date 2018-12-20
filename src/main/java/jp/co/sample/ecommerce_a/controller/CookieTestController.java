package jp.co.sample.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cookie")
public class CookieTestController {

	@RequestMapping("/")
	public String index() {
		return "cookie/cookie-test";
	}

}
