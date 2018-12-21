package jp.co.sample.ecommerce_a.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.ecommerce_a.domain.Item;
import jp.co.sample.ecommerce_a.repository.ItemRepository;

/**
 * 商品テーブル関連の処理を行うサービスクラス.
 * 
 * @author soheinobe
 *
 */
@Service
public class ItemService {

	@Autowired
	private ItemRepository itemRepository;

	/**
	 * cookieからの取得した情報から商品を検索する.
	 * 
	 * cookieから取得したString型のIDを数値に変換する。
	 * 
	 * @param cookieに保存された商品ID
	 * @return 商品情報
	 */
	public Item loadWithCookieId(String id) {
		Integer itemId = Integer.parseInt(id);
		Item item = itemRepository.load(itemId);
		return item;
	}

}
