package jp.co.sample.ecommerce_a.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.Item;
import jp.co.sample.ecommerce_a.repository.ItemRepository;

/**
 * 商品情報を処理するコントローラー.
 * 
 * @author yu.terauchi
 *
 */
@Controller
@Transactional
@RequestMapping("/showItem")
public class ShowItemController {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * 全商品情報を読み込み商品一覧ページへフォワード.
	 * 
	 * @param model モデル
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request, String name) {
//		BrowsingHistoryWithCookie browseCookie = new BrowsingHistoryWithCookie();
//		String id = browseCookie.getCookie(request, name);
		String id = null;
		Cookie[] cookies = request.getCookies();
		List<Item> cookieItemList = new ArrayList<>();
		if (cookies != null) {
			System.out.println("cookie取り出します");
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("cookie_itemId")) {
//					id = cookie.getValue();
//					Integer itemId = Integer.parseInt(id);
//					Item item = itemRepository.load(itemId);
//					cookieItemList.add(item);
//				}
//			}
			// cookieの最新５つを取得したい
//			for (int i = cookies.length; i <= (cookies.length - 4); i--) {
				for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];
				if (cookie.getName().equals("cookie_itemId")) {
					id = cookie.getValue();
					System.out.println(id);
					Integer itemId = Integer.parseInt(id);
					Item item = itemRepository.load(itemId);
					cookieItemList.add(item);
				}
			}
			model.addAttribute("cookieItemList", cookieItemList);
		}

		List<Item> items = itemRepository.findAll();
		model.addAttribute("items", items);
		return "itemList";
	}

	/**
	 * 安い順で並び替えて商品一覧ページへフォワード.
	 * 
	 * @param model モデル
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/sortPrice")
	public String sortPrice(Model model) {
		List<Item> items = itemRepository.findAllSortPrice();
		model.addAttribute("items", items);
		return "itemList";
	}

	/**
	 * 高い順で並び替えて商品一覧ページへフォワード.
	 * 
	 * @param model モデル
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/sortPriceDesc")
	public String sortPriceDesc(Model model) {
		List<Item> items = itemRepository.findAllSortPriceDesc();
		model.addAttribute("items", items);
		return "itemList";
	}

	/**
	 * 検索されてた商品情報を読み込み商品一覧ページへフォワード.
	 * 
	 * @param model モデル
	 * @param name  名前
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/search")
	public String searchItem(Model model, String name) {
		List<Item> items = itemRepository.findByItemName(name);

		String message = "該当する商品がありません";
		// FIXME:文字列比較は.equals()メソッドで！
		if (name == "") {
			items = itemRepository.findAll();
			model.addAttribute("message", message);
		}
		if (items.size() == 0) {
			items = itemRepository.findAll();
			model.addAttribute("message", message);
		}
		model.addAttribute("items", items);
		return "itemList";
	}
}
