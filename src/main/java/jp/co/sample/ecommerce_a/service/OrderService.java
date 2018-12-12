package jp.co.sample.ecommerce_a.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.ecommerce_a.domain.Item;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.OrderItem;
import jp.co.sample.ecommerce_a.repository.OrderRepository;

/**
 * 注文テーブル関連の処理を行うサービスクラス.s
 * 
 * @author soheinobe
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	/**
	 * CSV出力用の文字列を作成するメソッド.
	 * 
	 * @return CSV出力する文字列
	 */
	public List<String> ExportAllOrder() {
		List<Order> orderList = orderRepository.outputAllOrder();
		// 返す出力文字列
		List<String> outputList = new ArrayList<>();
		String outputString = null;

		// orderListの数だけ回す
		for (int i = 0; i < orderList.size(); i++) {

			// OrderItemList,Itemの中身からも必要なものを取り出していく。
			List<OrderItem> orderItemList = orderList.get(i).getOrderList();
			for (OrderItem orderItem : orderItemList) {
				Integer orderItemQuantity = orderItem.getQuantity();
				Integer orderItemSubTotal = orderItem.getSubTotal();
				Character orderItemSize = orderItem.getSize();
				Item item = orderItem.getItem();
				Integer orderItemPrice = null;
				if (orderItemSize.equals('M')) {
					orderItemPrice = item.getPriceM();
				}

				if (orderItemSize.equals('L')) {
					orderItemPrice = item.getPriceL();
				}

				String orderItemName = item.getName();

				String orderNumber = orderList.get(i).getOrder_number();
				String name = orderList.get(i).getDestinationName();
				String email = orderList.get(i).getDestinationEmail();
				String zipCode = orderList.get(i).getDestinationZipcode();
				String address = orderList.get(i).getDestinationAddress();
				String telephone = orderList.get(i).getDestinationTel();
				Integer totalPrice = orderList.get(i).getTotalPrice();
				Integer orderStatus = orderList.get(i).getStatus();

				// CSVファイル内部に記載する形式で文字列を設定
				outputString = orderNumber + "," + name + "," + email + "," + zipCode + "," + address + "," + telephone
						+ "," + orderItemName + "," + orderItemPrice + "," + orderItemQuantity + "," + orderItemSubTotal
						+ "," + totalPrice + "," + orderStatus + "\r\n";

				outputList.add(outputString);

			}
		}

		return outputList;
	}
}
