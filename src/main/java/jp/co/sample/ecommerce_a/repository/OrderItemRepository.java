package jp.co.sample.ecommerce_a.repository;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.sample.ecommerce_a.domain.OrderItem;

/**
 * OrderItemテーブルを操作するリポジトリ
 * 
 * @author junpei.oyama
 *
 */
@Repository
public class OrderItemRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;
	
	private SimpleJdbcInsert insert;
	
	/**
	 * OrderItemRepository初期化時に実行されるメソッド.
	 * 
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate)template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("order_items");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}
	
	/**
	 * idを渡さないinsertを実行するメソッド.
	 * 
	 * @param orderItem カートに入れた商品
	 * @return カートに入れた商品
	 */
	public OrderItem insert(OrderItem orderItem) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(orderItem);
		
		Number key = insert.executeAndReturnKey(param);
		orderItem.setId(key.intValue());
		
		return orderItem;
	}
	
	/**
	 * ショッピングカートからitemを削除するメソッド.
	 * 
	 * @param id order_itemsのid
	 */
	public void deleteById(Integer orderItemId) {
		String sql = "WITH deleted AS (DELETE FROM order_items WHERE id = :id RETURNING id) DELETE FROM order_toppings WHERE id IN (SELECT id FROM deleted)";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", orderItemId);
		
		template.update(sql, param);
	}
	
}
