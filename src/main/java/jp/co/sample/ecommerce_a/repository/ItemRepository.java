package jp.co.sample.ecommerce_a.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.ecommerce_a.domain.Item;

/**
 * Itemテーブルを操作するリポジトリ.
 * 
 * @author yu.terauchi
 *
 */
@Repository
public class ItemRepository {

	/**
	 * ResultSetオブジェクトからItemオブジェクトに変換するためのクラス実装&インスタンス化.
	 */
	private static final RowMapper<Item> ITEM_ROW_MAPPER = (rs, i) -> {
		Item item = new Item();
		item.setId(rs.getInt("id"));
		item.setName(rs.getString("name"));
		item.setDescription(rs.getString("description"));
		item.setPriceM(rs.getInt("price_m"));
		item.setPriceL(rs.getInt("price_l"));
		item.setImagePath(rs.getString("image_path"));
		item.setDeleted(rs.getBoolean("deleted"));
		return item;
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * すべてのの商品を読み込むメソッド.
	 * 
	 * @return商品一覧
	 */
	public List<Item> findAll() {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY id;";
		List<Item> items = jdbcTemplate.query(sql, ITEM_ROW_MAPPER);
		return items;
	}

	/**
	 * 9件ごとに商品を分けて読み込むメソッド.
	 * 
	 * ページング処理に使用。
	 * 
	 * @return
	 */
	public List<Item> findEveryNineItems(Integer pageNumber) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items LIMIT 9 OFFSET :pageNumber ORDER BY id;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("pageNumber", pageNumber);
		List<Item> itemList = jdbcTemplate.query(sql, param, ITEM_ROW_MAPPER);
		return itemList;
	}

	/**
	 * 安い順で並び替え.
	 * 
	 * @return商品一覧
	 */
	public List<Item> findAllSortPrice() {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY price_m;";
		List<Item> items = jdbcTemplate.query(sql, ITEM_ROW_MAPPER);
		return items;
	}

	/**
	 * 高い順で並び替え.
	 * 
	 * @return商品一覧
	 */
	public List<Item> findAllSortPriceDesc() {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items ORDER BY price_m Desc;";
		List<Item> items = jdbcTemplate.query(sql, ITEM_ROW_MAPPER);
		return items;
	}

	/**
	 * 商品を名前検索するメソッド.
	 * 
	 * @param naem 商品名
	 * @return 商品一覧
	 */
	public List<Item> findByItemName(String name) {
		// XXX:大文字小文字を区別なく検索する場合はこのようにilike演算子を使います
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE name ILIKE :name ORDER BY id ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("name", "%" + name + "%");
		List<Item> items = jdbcTemplate.query(sql, param, ITEM_ROW_MAPPER);
		return items;
	}

	/**
	 * 一つの商品の詳細を読み込むメソッド.
	 * 
	 * @param id 商品ID
	 * @return 商品の詳細
	 */
	public Item load(Integer id) {
		String sql = "SELECT id,name,description,price_m,price_l,image_path,deleted FROM items WHERE id = :id ;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		Item itemDetail = jdbcTemplate.queryForObject(sql, param, ITEM_ROW_MAPPER);
		return itemDetail;
	}
}
