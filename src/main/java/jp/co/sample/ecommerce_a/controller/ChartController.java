package jp.co.sample.ecommerce_a.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.databind.JsonMappingException;

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

		// このメソッドは表示のみにしようします。
//		Map<Integer, List<Integer>> yearAndMonthlySaleMap = new LinkedHashMap<>();
//		List<Integer> monthlySaleList = new ArrayList<>();
//
//		// 最大年数＝見たい年数
//		final int MAX_QUANTITY = 2018;
//		for (int i = 2016; i <= MAX_QUANTITY; i++) {
//			List<Sale> saleList = saleService.findByYear(i);
//			for (Sale sale : saleList) {
//				Integer monthlySale = sale.getMonthlySales();
//				monthlySaleList.add(monthlySale);
//			}
//			yearAndMonthlySaleMap.put(i, monthlySaleList);
//		}
//		model.addAttribute("yearAndMonthlySaleMap", yearAndMonthlySaleMap);

//		List<String> yearAndMonthlySaleList = new ArrayList<>();
//		List<Integer> monthlySaleList = new ArrayList<>();
//
//		// 最大年数＝見たい年数
//		final int MAX_QUANTITY = 2018;
//		for (int i = 2016; i <= MAX_QUANTITY; i++) {
//			List<Sale> saleList = saleService.findByYear(i);
//			for (Sale sale : saleList) {
//				Integer monthlySale = sale.getMonthlySales();
//				monthlySaleList.add(monthlySale);
//			}
//			yearAndMonthlySaleList.add(i + "" + monthlySaleList);
//		}
//
//		model.addAttribute("yearAndMonthlySaleList", yearAndMonthlySaleList);
//
//		String lastestYear = "2018";
//
//		List<Integer> monthlySaleList = saleService.findByYear(lastestYear);
//
//		model.addAttribute("monthlySaleList", monthlySaleList);

		return "chart/chart";
	}

	/**
	 * 新規のTODOを追加する.
	 * 
	 * @param todo 新規投稿TODO
	 * @return 更新の反映されたTODO
	 */
	@ResponseBody // アノテーションにJSONエンコーダが内蔵されているのでJSにオブジェクトを直接リターンできる
	@PostMapping("/post")
	public List<Integer> post(String year) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("Ajax呼ばれてます！");
		System.out.println(year);
		// readValueメソッドの第一引数にJSONを文字列にしたものを渡し、第二引数でパースしたいクラスを指定
//		Sale sale = new ObjectMapper().readValue(year, Sale.class);

		// パースした？年数を取得
//		String selectYear = sale.getYear();
//		System.out.println(selectYear);

		List<Integer> monthlySaleList = saleService.findByYear(year);
		return monthlySaleList;
	}

}
