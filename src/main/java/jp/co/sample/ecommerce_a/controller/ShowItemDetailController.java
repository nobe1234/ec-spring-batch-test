package jp.co.sample.ecommerce_a.controller;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

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
	
	@ModelAttribute
	public InsertItemForm setUpInsertItemForm() {
		return new InsertItemForm();
	}

	/**
	 * 一件の商品情報を読み込み商品詳細ページへフォワード.
	 * 
	 * @param model モデル
	 * @param id ID
	 * @return 一件の商品詳細
	 */
	@RequestMapping("/detail")
	public String itemDetail(Model model, Integer id) {
		Item itemDetail = itemRepository.load(id);
		
		List<Topping> toppings = toppingRepository.findAllTopping();
		
		Map<Integer,Integer> quantityMap = new LinkedHashMap<>();
		// FIXME:こういう場合はfor文を使うと3行で書けます
//		final int MAX_QUANTITY = 12;
//		for(int i = 1; i <= MAX_QUANTITY; i++) {
//			quantityMap.put(i, i);
//		}
		quantityMap.put(1, 1);
		quantityMap.put(2, 2);
		quantityMap.put(3, 3);
		quantityMap.put(4, 4);
		quantityMap.put(5, 5);
		quantityMap.put(6, 6);
		quantityMap.put(7, 7);
		quantityMap.put(8, 8);
		quantityMap.put(9, 9);
		quantityMap.put(10, 10);
		quantityMap.put(11, 11);
		quantityMap.put(12, 12);
		
		model.addAttribute("quantityMap", quantityMap);
		
		Map<Integer,String> toppingMap = new LinkedHashMap<>();
		int i = 1;
		for(Topping toppingName : toppings) {
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
