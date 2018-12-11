package jp.co.sample.ecommerce_a.repository;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import jp.co.sample.ecommerce_a.domain.Item;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.OrderItem;
import jp.co.sample.ecommerce_a.domain.OrderTopping;
import jp.co.sample.ecommerce_a.domain.Topping;

/**
 * Ordersテーブルを操作するリポジトリ.
 * 
 * @author yume.hirata
 *
 */
@Repository
public class OrderRepository {

	@Autowired
	private NamedParameterJdbcTemplate template;

	private SimpleJdbcInsert insert;

//	/** 今日の日付 */
//	private LocalDate localDate = LocalDate.now();
//	DateFormat format = new SimpleDateFormat("yyyyMMdd");

	/**
	 * OrderテーブルのROW_MAPPERの定義.
	 * 
	 */
	private final static RowMapper<Order> FIND_ORDER_ROW_MAPPER = (rs, i) -> {
		Order order = new Order();
		order.setId(rs.getInt("id"));
		order.setUserId(rs.getInt("user_id"));
//		order.setOrder_number(rs.getString("order_number"));
		order.setStatus(rs.getInt("status"));
		order.setTotalPrice(rs.getInt("total_price"));
		order.setOrderDate(rs.getDate("order_date"));
		order.setDestinationName(rs.getString("destination_name"));
		order.setDestinationEmail(rs.getString("destination_email"));
		order.setDestinationZipcode(rs.getString("destination_zipcode"));
		order.setDestinationAddress(rs.getString("destination_address"));
		order.setDestinationTel(rs.getString("destination_tel")); // 追加
		order.setDeliveryTime(rs.getTimestamp("delivery_time"));
		order.setPaymentMethod(rs.getInt("payment_method"));
		return order;
	};

	/**
	 * テーブルを結合したものからorderListを作成する.
	 * 
	 */
	private static final ResultSetExtractor<List<Order>> ORDER_RESULT_SET_EXTRACTOR = (rs) -> {
		List<Order> orderList = new LinkedList<>();
		Order order = null;
		OrderItem orderItem = null;
		Item item = null;
		List<OrderItem> orderItemList = null;
		List<OrderTopping> orderToppingList = null;
		long beforeOrderId = 0;
		long beforeOrderItemId = 0;
		while (rs.next()) {
			if (beforeOrderId != rs.getInt("order_id")) {
				order = new Order();
				orderList.add(order);
				order.setId(rs.getInt("order_id"));
				order.setUserId(rs.getInt("user_id"));
				order.setOrder_number(rs.getString("order_number"));
				order.setStatus(rs.getInt("status"));
				order.setTotalPrice(rs.getInt("total_price"));
				order.setOrderDate(rs.getDate("order_date"));
				order.setDestinationName(rs.getString("destination_name"));
				order.setDestinationEmail(rs.getString("destination_email"));
				order.setDestinationZipcode(rs.getString("destination_zipcode"));
				order.setDestinationAddress(rs.getString("destination_address"));
				order.setDestinationTel(rs.getString("destination_tel"));
				order.setDeliveryTime(rs.getTimestamp("delivery_time"));
				order.setPaymentMethod(rs.getInt("payment_method"));
				orderItemList = new ArrayList<OrderItem>();
				order.setOrderList(orderItemList);
			}

			if (rs.getInt("orderItem_id") != beforeOrderItemId) {
				orderItem = new OrderItem();
				item = new Item();
				orderToppingList = new LinkedList<>();
				orderItemList.add(orderItem);
				orderItem.setId(rs.getInt("orderItem_id"));
				orderItem.setItemId(rs.getInt("item_id"));
				orderItem.setOrderId(rs.getInt("order_id"));
				orderItem.setQuantity(rs.getInt("quantity"));
				orderItem.setSize((rs.getString("size").toCharArray()[0]));
				orderItem.setItem(item);
				orderItem.setOrderToppingList(orderToppingList);

				item.setId(rs.getInt("item_id"));
				item.setName(rs.getString("item_name"));
				item.setDescription(rs.getString("description"));
				item.setPriceM(rs.getInt("item_priceM"));
				item.setPriceL(rs.getInt("item_priceL"));
				item.setImagePath(rs.getString("image_path"));
				item.setDeleted(rs.getBoolean("deleted"));
			}

			if (rs.getInt("orderTopping_id") != 0) {
				OrderTopping orderTopping = new OrderTopping();
				Topping topping = new Topping();
				orderToppingList.add(orderTopping);
				orderTopping.setId(rs.getInt("orderTopping_id"));
				orderTopping.setToppingId(rs.getInt("topping_id"));
//				orderTopping.setOrderItemId(rs.getInt("orderItem_id"));
				orderTopping.setTopping(topping);

				topping.setId(rs.getInt("topping_id"));
				topping.setName(rs.getString("topping_name"));
				topping.setPriceM(rs.getInt("topping_priceM"));
				topping.setPriceL(rs.getInt("topping_priceL"));
			}
			beforeOrderId = rs.getInt("order_id");
			beforeOrderItemId = rs.getInt("order_item_id");
		}

		return orderList;
	};

	// FIXME:idジェネレータで使わなかったら削除して。
//	/**
//	 * 
//	 * 
//	 * @param userId 検索キーとなるログインしているユーザーのユーザーID
//	 * @param status 検索キーとなる注文の状態
//	 * @return 探してきた注文
//	 */
//	public Order findAll() {
//		String sql = "SELECT id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,"
//				+ "destination_tel,delivery_time,payment_method FROM orders ORDER BY ID;";
//
//		List<Order> orderList = template.query(sql, FIND_ORDER_ROW_MAPPER);
//
//		return orderList.get(0);
//	}

	/**
	 * ユーザーIDと状態から注文を探す.
	 * 
	 * @param userId 検索キーとなるログインしているユーザーのユーザーID
	 * @param status 検索キーとなる注文の状態
	 * @return 探してきた注文
	 */
	public Order findOrderByUserIdAndStatus(Integer userId, Integer status) {
		String sql = "SELECT id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address,"
				+ "destination_tel,delivery_time,payment_method FROM orders WHERE user_id = :userId AND status = :status;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);

		List<Order> orderList = template.query(sql, param, FIND_ORDER_ROW_MAPPER);

		return orderList.get(0);
	}

	/**
	 * OrderRepository初期化時に実行されるメソッド.
	 */
	@PostConstruct
	public void init() {
		SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert((JdbcTemplate) template.getJdbcOperations());
		SimpleJdbcInsert withTableName = simpleJdbcInsert.withTableName("orders");
		insert = withTableName.usingGeneratedKeyColumns("id");
	}

	/**
	 * idを渡さないinsertを実行するメソッド.
	 * 
	 * @param order 注文情報
	 * @return 注文情報
	 */
	public Order insert(Order order) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);

		Number key = insert.executeAndReturnKey(param);
		order.setId(key.intValue());

		return order;
	}

	/**
	 * 注文情報を更新する.
	 * 
	 * @param order 更新に反映される注文内容
	 */
	public void update(Order order) {
		String sql = "UPDATE orders SET user_id=:userId,status=:status,total_price=:totalPrice,order_date=:orderDate,destination_name=:destinationName,"
				+ "destination_email=:destinationEmail,destination_zipcode=:destinationZipcode,destination_address=:destinationAddress,destination_tel=:destinationTel,"
				+ "delivery_time=:deliveryTime,payment_method=:paymentMethod WHERE id = :id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
	}

	/**
	 * 受けとった引数をそのまま返す. あとでinsert文とsetでsaveメソッドにする.
	 * 
	 * @param order 受け取った引数
	 */
	public Order updateReturn(Order order) {
		String sql = "UPDATE orders SET user_id=:userId,status=:status,total_price=:totalPrice,order_date=:orderDate,destination_name=:destinationName,"
				+ "destination_email=:destinationEmail,destination_zipcode=:destinationZipcode,destination_address=:destinationAddress,destination_tel=:destinationTel,"
				+ "delivery_time=:deliveryTime,payment_method=:paymentMethod WHERE id = :id;";
		SqlParameterSource param = new BeanPropertySqlParameterSource(order);
		template.update(sql, param);
		return order;
	}

	/**
	 * user_idとstatusでordersテーブルの情報を検索するメソッド.
	 * 
	 * @param userId ユーザid
	 * @param status 状態
	 * @return order情報
	 */
	public Order findByUserIdAndStatus(Integer userId, Integer status) {
		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);

		String sql = "SELECT id,user_id,status,total_price,order_date,destination_name,destination_email,destination_zipcode,destination_address, destination_tel,delivery_time,payment_method "
				+ "FROM orders WHERE user_id=:userId AND status=:status";

		List<Order> orderList = template.query(sql, param, FIND_ORDER_ROW_MAPPER);
		if (orderList.size() == 0) {
			return null;
		}

		return orderList.get(0);
	}

	/**
	 * ショッピングカートの中身を表示するためのメソッド.
	 * 
	 * @param userId ユーザid
	 * @param status 状態
	 * @return 注文リスト,検索にかからなければnull
	 */
	public Order findByUserIdAndStatusForShowCart(Integer userId, Integer status) {

		String sql = "SELECT orders.id order_id, user_id,orders.order_number order_number, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method, "
				+ " orderItem.id orderItem_id, item_id, order_id, quantity, size,"
				+ " item.id item_id, item.name item_name, description, item.price_m item_priceM, item.price_l item_priceL, image_path, deleted,"
				+ " orderTopping.id orderTopping_id, topping_id, order_item_id,"
				+ " topping.id topping_id, topping.name topping_name, topping.price_m topping_priceM, topping.price_l topping_priceL"
				+ " FROM orders" + " LEFT OUTER JOIN order_items orderItem ON orders.id = orderItem.order_id"
				+ " LEFT OUTER JOIN items item ON orderItem.item_id = item.id"
				+ " LEFT OUTER JOIN order_toppings orderTopping ON orderItem.id = orderTopping.order_item_id"
				+ " LEFT OUTER JOIN toppings topping ON orderTopping.topping_id = topping.id"
				+ " WHERE user_id = :userId AND status = :status" + " ORDER BY orders.id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("userId", userId).addValue("status", status);

		List<Order> orderList = template.query(sql, param, ORDER_RESULT_SET_EXTRACTOR);
		if (orderList.size() == 0) {
			return null;
		}

		return orderList.get(0);
	}

	// FIXME:javadoc漏れ
	/**
	 * 退会処理時にユーザー削除を行う.
	 * 
	 * @param ユーザーID
	 */
	public void deleteByUserId(Integer userId) {
		String sql = "DELETE FROM orders WHERE user_id = :id";

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", userId);

		template.update(sql, param);
	}

	/**
	 * 全てのオーダーを取得する.
	 * 
	 * 管理者のみ利用可能。
	 * 
	 * csv出力用の検索を行うメソッド.
	 * 
	 * @return
	 */
	public List<Order> outputAllOrder() {
		String sql = "SELECT orders.id order_id,orders.order_number order_number,user_id, status, total_price, order_date, destination_name, destination_email, destination_zipcode, destination_address, destination_tel, delivery_time, payment_method, "
				+ " orderItem.id orderItem_id, item_id, order_id, quantity, size,"
				+ " item.id item_id, item.name item_name, description, item.price_m item_priceM, item.price_l item_priceL, image_path, deleted,"
				+ " orderTopping.id orderTopping_id, topping_id, order_item_id,"
				+ " topping.id topping_id, topping.name topping_name, topping.price_m topping_priceM, topping.price_l topping_priceL"
				+ " FROM orders" + " LEFT OUTER JOIN order_items orderItem ON orders.id = orderItem.order_id"
				+ " LEFT OUTER JOIN items item ON orderItem.item_id = item.id"
				+ " LEFT OUTER JOIN order_toppings orderTopping ON orderItem.id = orderTopping.order_item_id"
				+ " LEFT OUTER JOIN toppings topping ON orderTopping.topping_id = topping.id"
				+ " ORDER BY orders.order_number";

		List<Order> orderList = template.query(sql, ORDER_RESULT_SET_EXTRACTOR);
		if (orderList.size() == 0) {
			return null;
		}
		return orderList;
	}

}
