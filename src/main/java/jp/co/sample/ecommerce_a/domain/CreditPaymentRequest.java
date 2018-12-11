package jp.co.sample.ecommerce_a.domain;

/**
 * WEBAPIにリクエストを送るリクエストボディ.
 * 
 * @author soheinobe
 *
 */
public class CreditPaymentRequest {

	/** ユーザーID */
	private Integer user_id;
	/** オーダー番号 */
	private Integer order_number;
	/** クレジットカード番号 */
	private Integer card_number; // String??
	/** 有効期限(月) */
	private String card_exp_month;
	/** 有効期限(年) */
	private String card_exp_year;
	/** カード名義人 */
	private String card_name;
	/** セキュリティコード */
	private Integer card_cvv;

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public Integer getOrder_number() {
		return order_number;
	}

	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}

	public Integer getCard_number() {
		return card_number;
	}

	public void setCard_number(Integer card_number) {
		this.card_number = card_number;
	}

	public String getCard_exp_month() {
		return card_exp_month;
	}

	public void setCard_exp_month(String card_exp_month) {
		this.card_exp_month = card_exp_month;
	}

	public String getCard_exp_year() {
		return card_exp_year;
	}

	public void setCard_exp_year(String card_exp_year) {
		this.card_exp_year = card_exp_year;
	}

	public String getCard_name() {
		return card_name;
	}

	public void setCard_name(String card_name) {
		this.card_name = card_name;
	}

	public Integer getCard_cvv() {
		return card_cvv;
	}

	public void setCard_cvv(Integer card_cvv) {
		this.card_cvv = card_cvv;
	}

}
