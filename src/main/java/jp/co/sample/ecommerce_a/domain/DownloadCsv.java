//package jp.co.sample.ecommerce_a.domain;
//
//import java.sql.Timestamp;
//import java.util.Date;
//import java.util.List;
//
//import com.fasterxml.jackson.annotation.JsonProperty;
//
////@JsonPropertyOrder() ソートしたい場合は必要
//public class DownloadCsv {
//
////	/** ID */
////	private Integer id;
////	/** ユーザーID */
////	private Integer userId;
//	/** 注文番号（日付管理） */
//	@JsonProperty("注文番号")
//	private String order_number;
//	/** 状態 */
//	@JsonProperty("ステータス")
//	private Integer status;
//	/** 合計金額 */
//	@JsonProperty("合計金額")
//	private Integer totalPrice;
//	/** 注文日 */
//	@JsonProperty("注文日")
//	private Date orderDate;
//	/** 宛先氏名 */
//	@JsonProperty("利用者名")
//	private String destinationName;
//	/** 宛先Eメール */
//	@JsonProperty("メール")
//	private String destinationEmail;
//	/** 宛先郵便番号 */
//	@JsonProperty("郵便番号")
//	private String destinationZipcode;
//	/** 宛先住所 */
//	@JsonProperty("住所")
//	private String destinationAddress;
//	/** 宛先TEL */
//	@JsonProperty("電話番号")
//	private String destinationTel;
//	/** 配達時間 */
//	@JsonProperty("お届け時間")
//	private Timestamp deliveryTime;
//
//	/** 支払方法 */
////	@JsonProperty("注文番号")
////	private Integer paymentMethod;
////	/** ユーザー */
////	private User user;
////	/** 注文商品リスト */
////	private List<OrderItem> orderList;
//
//	@Override
//	public String toString() {
//		return "DownloadCsv [id=" + id + ", userId=" + userId + ", status=" + status + ", totalPrice=" + totalPrice
//				+ ", orderDate=" + orderDate + ", destinationName=" + destinationName + ", destinationEmail="
//				+ destinationEmail + ", destinationZipcode=" + destinationZipcode + ", destinationAddress="
//				+ destinationAddress + ", destinationTel=" + destinationTel + ", deliveryTime=" + deliveryTime
//				+ ", paymentMethod=" + paymentMethod + ", user=" + user + ", orderList=" + orderList + ", order_number="
//				+ order_number + "]";
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public Integer getUserId() {
//		return userId;
//	}
//
//	public void setUserId(Integer userId) {
//		this.userId = userId;
//	}
//
//	public Integer getStatus() {
//		return status;
//	}
//
//	public void setStatus(Integer status) {
//		this.status = status;
//	}
//
//	public Integer getTotalPrice() {
//		return totalPrice;
//	}
//
//	public void setTotalPrice(Integer totalPrice) {
//		this.totalPrice = totalPrice;
//	}
//
//	public Date getOrderDate() {
//		return orderDate;
//	}
//
//	public void setOrderDate(Date orderDate) {
//		this.orderDate = orderDate;
//	}
//
//	public String getDestinationName() {
//		return destinationName;
//	}
//
//	public void setDestinationName(String destinationName) {
//		this.destinationName = destinationName;
//	}
//
//	public String getDestinationEmail() {
//		return destinationEmail;
//	}
//
//	public void setDestinationEmail(String destinationEmail) {
//		this.destinationEmail = destinationEmail;
//	}
//
//	public String getDestinationZipcode() {
//		return destinationZipcode;
//	}
//
//	public void setDestinationZipcode(String destinationZipcode) {
//		this.destinationZipcode = destinationZipcode;
//	}
//
//	public String getDestinationAddress() {
//		return destinationAddress;
//	}
//
//	public void setDestinationAddress(String destinationAddress) {
//		this.destinationAddress = destinationAddress;
//	}
//
//	public String getDestinationTel() {
//		return destinationTel;
//	}
//
//	public void setDestinationTel(String destinationTel) {
//		this.destinationTel = destinationTel;
//	}
//
//	public Timestamp getDeliveryTime() {
//		return deliveryTime;
//	}
//
//	public void setDeliveryTime(Timestamp deliveryTime) {
//		this.deliveryTime = deliveryTime;
//	}
//
//	public Integer getPaymentMethod() {
//		return paymentMethod;
//	}
//
//	public void setPaymentMethod(Integer paymentMethod) {
//		this.paymentMethod = paymentMethod;
//	}
//
//	public User getUser() {
//		return user;
//	}
//
//	public void setUser(User user) {
//		this.user = user;
//	}
//
//	public List<OrderItem> getOrderList() {
//		return orderList;
//	}
//
//	public void setOrderList(List<OrderItem> orderList) {
//		this.orderList = orderList;
//	}
//
//	public String getOrder_number() {
//		return order_number;
//	}
//
//	public void setOrder_number(String order_number) {
//		this.order_number = order_number;
//	}
//
//}
