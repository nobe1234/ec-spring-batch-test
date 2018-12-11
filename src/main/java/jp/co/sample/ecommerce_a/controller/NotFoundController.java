package jp.co.sample.ecommerce_a.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
//FIXME:javadoc漏れ
public class NotFoundController implements ErrorController {

	private static final String PATH = "/error";

	@RequestMapping(PATH)
	public String getErrorPath() {
		return "error/notFound";
	}
}
