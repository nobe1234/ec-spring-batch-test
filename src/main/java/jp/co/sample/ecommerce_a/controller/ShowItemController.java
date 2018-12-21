package jp.co.sample.ecommerce_a.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.Item;
import jp.co.sample.ecommerce_a.form.PageForm;
import jp.co.sample.ecommerce_a.repository.ItemRepository;
import jp.co.sample.ecommerce_a.service.ItemService;

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

	@Autowired
	private ItemService itemService;

	/**
	 * @return ページング番号を受け取るフォーム.
	 */
	@ModelAttribute
	public PageForm setUpPageForm() {
		return new PageForm();
	}

	/**
	 * 全商品情報を読み込み商品一覧ページへフォワード.
	 * 
	 * @param model モデル
	 * @return 商品一覧ページ
	 */
	@RequestMapping("/index")
	public String index(Model model, HttpServletRequest request, Integer pageNumber) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			List<Item> cookieItemList = SearchCookiesItem(request);
			model.addAttribute("cookieItemList", cookieItemList);
		}

//		List<Item> items = itemRepository.findAll();
		// pageNumberはoffsetの値なので、ページが始まる値、0,9,10？b
		System.out.println(pageNumber);
		if (pageNumber == null) {
			// 初期アクセス時はページ番号がないはずなので、0を入れておく
			pageNumber = 0;
		}
		List<Item> items = itemService.findEveryNineItems(pageNumber);
		model.addAttribute("items", items);
		return "itemList";
	}

	/**
	 * cookieに保存されているIDから最近みた商品リストを作成するメソッド.
	 * 
	 * 最新５つを表示するため、cookieの数で分岐してある。
	 * 
	 * @param request
	 * @param length
	 * @return
	 */
	public List<Item> SearchCookiesItem(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		cookies.toString();
		List<Item> cookieItemList = new ArrayList<>();
		String id = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().startsWith("cookie_")) {
				id = cookie.getValue();
				Item item = itemService.loadWithCookieId(id);
				cookieItemList.add(item);
			}
		}

		// 毎回条件が変わってしまう。
		// for文の中で動く変数を条件にしない
		int x = cookieItemList.size();
//		System.out.println("引く前" + cookieItemList.size());
		if (cookieItemList.size() > 5) {
			for (int i = 1; i <= (x - 5); i++) {
				cookieItemList.remove(0);
//				System.out.println(i + ":" + cookieItemList.size());
			}
		}
//		System.out.println("" + cookieItemList.size());

//		if (cookies.length >= 5) {
//			for (int i = cookies.length; i <= (cookies.length - 4); i--) {
//				Cookie cookie = cookies[i];
//				if (cookie.getName().startsWith("cookie_")) {
//					id = cookie.getValue();
//					Item item = itemService.loadWithCookieId(id);
//					cookieItemList.add(item);
//				}
//			}
//		}
//
//		if (cookies.length <= 4) {
//			for (int i = 0; i <= cookies.length; i++) {
//				Cookie cookie = cookies[i];
//				if (cookie.getName().startsWith("cookie_")) {
//					id = cookie.getValue();
//					Item item = itemService.loadWithCookieId(id);
//					cookieItemList.add(item);
//				}
//			}
//		}

//		System.out.println("返す前");
//		System.out.println(cookieItemList.size());
		return cookieItemList;
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
