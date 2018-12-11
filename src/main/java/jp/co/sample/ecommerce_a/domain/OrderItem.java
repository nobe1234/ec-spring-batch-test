package jp.co.sample.ecommerce_a.domain;

import java.util.List;


/**
 * 注文商品を表すエンティティ.
 * 
 * @author yu.terauchi
 *
 */
public class OrderItem {
	/** ID */
	private Integer id;
	/** 商品ID */
	private Integer itemId;
	/** オーダーID */
	private Integer orderId;
	/** 数量 */
	private Integer quantity;
	/** サイズ */
	private Character size;
	/** 商品 */
	private Item item;
	/** 注文商品リスト */
	private List<OrderTopping> orderToppingList;

	public OrderItem() {
	}

	public OrderItem(Integer id, Integer itemId, Integer orderId, Integer quantity, Character size, Item item,
			List<OrderTopping> orderToppingList) {
		super();
		this.id = id;
		this.itemId = itemId;
		this.orderId = orderId;
		this.quantity = quantity;
		this.size = size;
		this.item = item;
		this.orderToppingList = orderToppingList;
	}


	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", itemId=" + itemId + ", orderId=" + orderId + ", quantity=" + quantity
				+ ", size=" + size + ", item=" + item + ", orderToppingList=" + orderToppingList + "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getItemId() {
		return itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Character getSize() {
		return size;
	}
	
	//ピザのサイズをString型で取得するメソッド
	public String getStringSize() {
		return size.toString();
	}

	public void setSize(Character size) {
		this.size = size;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public List<OrderTopping> getOrderToppingList() {
		return orderToppingList;
	}

	public void setOrderToppingList(List<OrderTopping> orderToppingList) {
		this.orderToppingList = orderToppingList;
	}

	public Integer getOrderId() {
		return orderId;
	}
	
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	
	public int getSubTotal() {	
		int pizzaPrice = 0;
		
		int toppingPrice = 0;
		if(size == 'M') {
			toppingPrice = 200 * (orderToppingList.size());
			pizzaPrice = item.getPriceM();
		}
		if(size == 'L') {
			toppingPrice = 300 * (orderToppingList.size());
			pizzaPrice = item.getPriceL();
		}
		
		int subTotal = (pizzaPrice + toppingPrice) * quantity;
		
		return subTotal;
	}
}
