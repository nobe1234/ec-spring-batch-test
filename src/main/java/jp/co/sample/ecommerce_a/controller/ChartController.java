package jp.co.sample.ecommerce_a.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * グラフを表示するコントローラ.
 * 
 * @author soheinobe
 *
 */
@RequestMapping("/chart")
@Controller
public class ChartController {

	/**
	 * グラフページを表示する.
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
//		String label[] = { "a", "b", "c", "d", "e", "f", "g" };
//
//		// 縦軸
//		// 具体的な値です。int型で、この値も適当です。
//		int point[] = { 5, 3, 7, 1, 8, 3, 4, };
//
//		// Modelに格納します。ビュー側でグラフ用の配列を受け取れるようにしておきます。
//		model.addAttribute("label", label);
//		model.addAttribute("point", point);
		return "chart/chart";
	}
}
