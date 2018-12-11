package jp.co.sample.ecommerce_a.form;

/**
 * 入力されたクレジットカード情報のパラメータを受け取るフォーム.
 * 
 * 
 * APIへ送るリクエストとして利用する。
 * 
 * @author soheinobe
 *
 */
public class CreditCardForm {

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
	/** 利用者ID */
	private Integer user_id;
	private Integer paymentMethod;

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	/** 注文ナンバー */
	private Integer order_number; // order_id
	/** 決済金額 */
	private Integer amount;

//	private Integer paymentMethod;
//	/** 宛先氏名 */
//	@Size(max = 100, message = "100文字以内で入力してください")
//	@NotBlank(message = "お名前を入力してください")
//	private String destinationName;
//	/** 宛先Eメール */
//	@Size(max = 100, message = "100文字以内で入力してください")
//	@NotBlank(message = "メールアドレスを入力してください")
//	private String destinationEmail;
//	/** 宛先郵便番号 */
//	private String destinationZipcode;
//	/** 宛先住所 */
//	@Size(max = 200, message = "200文字以内で入力してください")
//	@NotBlank(message = "ご配達先の住所を入力してください")
//	private String destinationAddress;
//	/** 宛先TEL */
//	@Pattern(regexp = "^(070|080|090)-\\d{4}-\\d{4}$", message = "「-」（ハイフン）必須、070、080、090から始まる番号のみが使えます")
//	@Size(max = 15, message = "15文字以内で入力してください")
//	@NotBlank(message = "お電話番号を入力してください")
//	private String destinationTel;
//	/** 宛先配達日 */
//	@NotBlank(message = "ご配達日時を選択してください")
//	private String deliveryDate;
//	/** 宛先配達時間 */
//	private String deliveryTime;

	public String getCard_exp_month() {
		return card_exp_month;
	}

	public Integer getCard_number() {
		return card_number;
	}

	public void setCard_number(Integer card_number) {
		this.card_number = card_number;
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

	public Integer getOrder_number() {
		return order_number;
	}

	public void setOrder_number(Integer order_number) {
		this.order_number = order_number;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}