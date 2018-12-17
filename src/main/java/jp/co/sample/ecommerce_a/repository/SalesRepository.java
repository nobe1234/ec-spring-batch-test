package jp.co.sample.ecommerce_a.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.ecommerce_a.domain.Sale;

/**
 * 売上テーブルのDB処理を行うリポジトリ.
 * 
 * @author soheinobe
 *
 */
@Repository
public class SalesRepository {

	/**
	 * JDBC関連のオブジェクトを注入.
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 結果の操作を定義.
	 */
	private static final RowMapper<Sale> SALE_ROW_MAPPER = (rs, i) -> {
		Sale sale = new Sale();
		sale.setId(rs.getInt("id"));
		sale.setMonthlySales(rs.getInt("monthly_price"));
		return sale;
	};

	/**
	 * 年度ごとの売上データを抽出するメソッド.
	 * 
	 * @param year
	 * @return 当年の売上リスト.
	 */
	public List<Sale> findByYear(Integer year) {
		String sql = "SELECT id,month_of_year ,year,monthly_price from sales where year = :year order by month_of_year;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("year", year);

		List<Sale> saleList = template.query(sql, param, SALE_ROW_MAPPER);

		return saleList;
	}

	/**
	 * 全ての売上を検索するメソッド.
	 * 
	 * @return 売上リスト
	 */
	public List<Sale> findAll() {
		String sql = "SELECT id,month_of_year ,year,monthly_price from sales where year = :year order by month_of_year;";

		List<Sale> saleList = template.query(sql, SALE_ROW_MAPPER);

		return saleList;
	}

	// TODO:年度絞り込みなどもあったらいいかも 例2000~2004
}
