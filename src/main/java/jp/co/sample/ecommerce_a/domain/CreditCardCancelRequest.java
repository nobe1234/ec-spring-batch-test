package jp.co.sample.ecommerce_a.domain;

/**
 * 決済キャンセルに必要な情報を表すエンティティ.
 * 
 * Web APIに送るキャンセルリクエスト。
 * 
 * @author soheinobe
 *
 */
public class CreditCardCancelRequest {

	private Integer order_number;

	public Integer getOrder_number() {
		return order_number;
	}

	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}

}
