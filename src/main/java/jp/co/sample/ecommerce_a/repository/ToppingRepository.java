package jp.co.sample.ecommerce_a.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import jp.co.sample.ecommerce_a.domain.Topping;

/**
 * トッピング情報を読み込むリポジトリ.
 * 
 * @author yu.terauchi
 *
 */
@Repository
public class ToppingRepository {

	/**
	 * ResultSetオブジェクトからToppingオブジェクトに変換するためのクラス実装&インスタンス化
	 */
	private static final RowMapper<Topping> TOPPING_ROW_MAPPER = (rs, i) -> {
		Topping topping = new Topping();
		topping.setId(rs.getInt("id"));
		topping.setName(rs.getString("name"));
		topping.setPriceM(rs.getInt("price_m"));
		topping.setPriceL(rs.getInt("price_l"));
		return topping;
	};

	@Autowired
	private NamedParameterJdbcTemplate jdbcTemplate;

	/**
	 * すべてのトッピングを読み込むメソッド.
	 * 
	 * @return トッピング一覧
	 */
	public List<Topping> findAllTopping() {
		String sql = "SELECT id,name,price_m,price_l FROM toppings ORDER BY id;";
		List<Topping> toppings = jdbcTemplate.query(sql, TOPPING_ROW_MAPPER);
		return toppings;
	}
}
