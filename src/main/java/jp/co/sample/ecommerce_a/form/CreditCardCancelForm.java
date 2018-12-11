package jp.co.sample.ecommerce_a.form;

/**
 * 決済キャンセル処理のリクエストを受け取るフォーム.
 * 
 * @author soheinobe
 *
 */
public class CreditCardCancelForm {

	/** 注文NO */
	private Integer order_number;

	public Integer getOrder_number() {
		return order_number;
	}

	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}

}
