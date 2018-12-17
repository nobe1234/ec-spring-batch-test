package jp.co.sample.ecommerce_a.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.Sale;
import jp.co.sample.ecommerce_a.form.ChartRequestForm;
import jp.co.sample.ecommerce_a.service.SaleService;

/**
 * グラフを表示するコントローラ.
 * 
 * @author soheinobe
 *
 */
@RequestMapping("/chart")
@Controller
public class ChartController {

	@Autowired
	private SaleService saleService;

	/**
	 * フォームを受け取るためのオブジェクトを生成する.
	 * 
	 * @return
	 */
	@ModelAttribute
	public ChartRequestForm setUpForm() {
		return new ChartRequestForm();
	}

	/**
	 * グラフページを表示する.
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {

		Map<Integer, Integer> yearAndMonthlySaleMap = new LinkedHashMap<>();
		final int MAX_QUANTITY = 2018;
		for (int i = 2016; i <= MAX_QUANTITY; i++) {
			List<Sale> saleList = saleService.findByYear(i);
			for (Sale sale : saleList) {
				Integer monthlySale = sale.getMonthlySales();
				yearAndMonthlySaleMap.put(i, monthlySale);
			}
		}

		model.addAttribute("yearAndMonthlySaleMap", yearAndMonthlySaleMap);

//		List<Sale> saleList = saleService.findAll();
//
//		List<Integer> monthlySaleList = new ArrayList<>();
//		for (Sale sale : saleList) {
//			Integer monthlySale = sale.getMonthlySales();
//			monthlySaleList.add(monthlySale);
//			model.addAttribute("monthlySaleList", monthlySaleList);
//		}

		return "chart/chart";
	}
}
