package jp.co.sample.ecommerce_a.form;

import java.util.List;

/**
 * itemDetail.jspからリクエストパラメータを受け取るフォーム.
 * 
 * @author junpei.oyama
 *
 */
public class InsertItemForm {

	private Integer itemId;
	private Character size;
	private List<Integer> toppingList;
	private Integer quantity;
	
	public Integer getItemId() {
		return itemId;
	}
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}
	public Character getSize() {
		return size;
	}
	public void setSize(Character size) {
		this.size = size;
	}
	public List<Integer> getToppingList() {
		return toppingList;
	}
	public void setToppingList(List<Integer> toppingList) {
		this.toppingList = toppingList;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
