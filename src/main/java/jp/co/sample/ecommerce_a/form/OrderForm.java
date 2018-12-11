package jp.co.sample.ecommerce_a.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 入力された注文情報を受け取るフォーム.
 * 
 * @author yume.hirata
 *
 */
public class OrderForm {
	/** 宛先氏名 */
	@Size(max=100,message="100文字以内で入力してください")
	@NotBlank(message="お名前を入力してください")
	private String destinationName;
	/** 宛先Eメール */
	@Size(max=100,message="100文字以内で入力してください")
	@NotBlank(message="メールアドレスを入力してください")
	private String destinationEmail;
	/** 宛先郵便番号 */
	private String destinationZipcode;
	/** 宛先住所 */
	@Size(max=200,message="200文字以内で入力してください")
	@NotBlank(message="ご配達先の住所を入力してください")
	private String destinationAddress;
	/** 宛先TEL */
	@Pattern(regexp = "^(070|080|090)-\\d{4}-\\d{4}$", message = "「-」（ハイフン）必須、070、080、090から始まる番号のみが使えます")
	@Size(max=15,message="15文字以内で入力してください")
	@NotBlank(message="お電話番号を入力してください")
	private String destinationTel;
	/** 宛先配達日 */
	@NotBlank(message="ご配達日時を選択してください")
	private String deliveryDate;
	/** 宛先配達時間 */
	private String deliveryTime;
	/** 支払方法 */
	private Integer paymentMethod;

	public String getDestinationName() {
		return destinationName;
	}

	public void setDestinationName(String destinationName) {
		this.destinationName = destinationName;
	}

	public String getDestinationEmail() {
		return destinationEmail;
	}

	public void setDestinationEmail(String destinationEmail) {
		this.destinationEmail = destinationEmail;
	}

	public String getDestinationZipcode() {
		return destinationZipcode;
	}

	public void setDestinationZipcode(String destinationZipcode) {
		this.destinationZipcode = destinationZipcode;
	}

	public String getDestinationAddress() {
		return destinationAddress;
	}

	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}

	public String getDestinationTel() {
		return destinationTel;
	}

	public void setDestinationTel(String destinationTel) {
		this.destinationTel = destinationTel;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public Integer getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(Integer paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
