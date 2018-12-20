package jp.co.sample.ecommerce_a.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonMappingException;

import jp.co.sample.ecommerce_a.service.SaleService;

@RestController
@RequestMapping("/restResponseChart")
public class RestResponseChartController {

	@Autowired
	private SaleService saleService;

	/**
	 * 新規のTODOを追加する.
	 * 
	 * @param todo 新規投稿TODO
	 * @return 更新の反映されたTODO
	 */
	@ResponseBody // アノテーションにJSONエンコーダが内蔵されているのでJSにオブジェクトを直接リターンできる
//	@PostMapping("/post") //こちらはRequestMappingのPOST用のアノテーション　可読性を高める
	@RequestMapping(value = "/post", method = { RequestMethod.GET, RequestMethod.POST }) // method属性をつけることでリクエストのGETやPOSTなどのmethodオプションで指定します。両方指定できる。
	public List<Integer> post(String year) throws JsonParseException, JsonMappingException, IOException {

		System.out.println("Ajax呼ばれてます！");
		System.out.println(year);

		List<Integer> monthlySaleList = saleService.findByYear(year);
		return monthlySaleList;
	}
}
