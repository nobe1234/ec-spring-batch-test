package jp.co.sample.ecommerce_a.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.BrowsingHistoryWithCookie;
import jp.co.sample.ecommerce_a.domain.Item;
import jp.co.sample.ecommerce_a.domain.Topping;
import jp.co.sample.ecommerce_a.form.InsertItemForm;
import jp.co.sample.ecommerce_a.repository.ItemRepository;
import jp.co.sample.ecommerce_a.repository.ToppingRepository;

/**
 * 商品の詳細情報を処理するコントローラー.
 * 
 * @author yu.terauchi
 *
 */
@Controller
@Transactional
@RequestMapping("/showItemDetail")
public class ShowItemDetailController {

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private ToppingRepository toppingRepository;

//	@Autowired
//	private BrowsingHistoryWithCookie browseCookie;

	@ModelAttribute
	public InsertItemForm setUpInsertItemForm() {
		return new InsertItemForm();
	}

	/**
	 * 一件の商品情報を読み込み商品詳細ページへフォワード.
	 * 
	 * @param model モデル
	 * @param id    ID
	 * @return 一件の商品詳細
	 */
	@SuppressWarnings("static-access")
	@RequestMapping("/detail")
	public String itemDetail(Model model, Integer id, HttpServletRequest request, HttpServletResponse response) {
		Item itemDetail = itemRepository.load(id);

		BrowsingHistoryWithCookie browseCookie = new BrowsingHistoryWithCookie();
		String itemId = "" + itemDetail.getId();
		browseCookie.setCookie(request, response, "/", "cookie_" + itemId, itemId, 5 * 60);

		// cookieが５つ以上の場合は0番目を削除
//		Cookie[] cookies = request.getCookies();
//		cookies.toString();
//		if (cookies.length >= 5) {
//			// 配列からリストへの変換
//			List<Cookie> cookieList = new LinkedList<Cookie>(Arrays.asList(cookies));
//			System.out.println("cookieリストが以下の通り");
//			System.out.println(cookieList);
//			cookieList.remove(0);
//		}

		List<Topping> toppings = toppingRepository.findAllTopping();

		Map<Integer, Integer> quantityMap = new LinkedHashMap<>();
		// FIXME:こういう場合はfor文を使うと3行で書けます
		final int MAX_QUANTITY = 12;
		for (int i = 1; i <= MAX_QUANTITY; i++) {
			quantityMap.put(i, i);
		}

		model.addAttribute("quantityMap", quantityMap);

		Map<Integer, String> toppingMap = new LinkedHashMap<>();
		int i = 1;
		for (Topping toppingName : toppings) {
			Topping topping = new Topping();
			topping = toppingName;
			toppingMap.put(i, topping.getName());
			i++;
		}

		model.addAttribute("toppingMap", toppingMap);

		model.addAttribute("toppings", toppings);
		model.addAttribute("itemDetail", itemDetail);
		return "itemDetail";
	}
}
