package jp.co.sample.ecommerce_a.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import jp.co.sample.ecommerce_a.domain.CreditCardCancelRequest;
import jp.co.sample.ecommerce_a.domain.CreditCardCancelResult;
import jp.co.sample.ecommerce_a.domain.CreditPaymentRequest;
import jp.co.sample.ecommerce_a.domain.CreditPaymentResult;
import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.User;
import jp.co.sample.ecommerce_a.form.CreditCardCancelForm;
import jp.co.sample.ecommerce_a.form.CreditCardForm;
import jp.co.sample.ecommerce_a.repository.OrderRepository;
import jp.co.sample.ecommerce_a.service.SendMailService;

/**
 * クレジットカードの認証をするコントローラ.
 * 
 * @author soheinobe
 *
 */
@Controller
@Transactional
@RequestMapping("/creditCardAuthorization")
public class CreditCardAuthorizationController {

	private static final String SEND_URI = "http://172.16.0.13:8080/web-api-sample/credit-card/";

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private SendMailService sendMailService;

	@RequestMapping("/toOrderForm")
	public String index(Model model) {
		return "orderConfirm";
	}

	@RequestMapping("")
	public String creditPayment(@Validated CreditCardForm creditCardForm, BindingResult result, Model model,
			@AuthenticationPrincipal LoginUser loginUser) {
		System.out.println("クレジットカード情報");
		creditCardForm.toString();
		User user = loginUser.getUser();
		Integer loginId = user.getId();
		Order order = orderRepository.findByUserIdAndStatus(loginId, 0);

//		order.setStatus(1);

		model.addAttribute("orderForm", order);

		CreditPaymentRequest requestBody = new CreditPaymentRequest();
		BeanUtils.copyProperties(creditCardForm, requestBody);
		URI uri = null;
		try {
			uri = new URI(SEND_URI + "payment");
		} catch (URISyntaxException e) {
			model.addAttribute("message", "通信エラー");
			e.printStackTrace();
			order.setStatus(9);
			return "authenticationComplete";
//			return "redirect:/order/orderconfirm"; // 完了画面へ移行
		}

		RequestEntity<CreditPaymentRequest> requestEntity = RequestEntity.post(uri)
				.contentType(MediaType.APPLICATION_JSON).body(requestBody);

		ResponseEntity<CreditPaymentResult> responseEntity = restTemplate.exchange(requestEntity,
				CreditPaymentResult.class);

		CreditPaymentResult cardPaymentResult = responseEntity.getBody();
		String resultErrorCode = cardPaymentResult.getError_code();

		// 認証結果のステータスを判定している
		switch (resultErrorCode) {
		case "E-01":
			result.rejectValue("card_exp_month", null, "カードの有効期限がきれています。");
			return index(model); // 確認画面へ移行
		case "E-02":
			result.rejectValue("card_number", null, "カード情報が正しくありません。");
			return index(model); // 確認画面へ移行
		case "E-03":
			result.rejectValue("card_number", null, "入力された値が不正です。");
			return index(model); // 確認画面へ移行

		default:
			break;
		}
		// 簡易チェック FIXME:あとで消す
//		if ("E-01".equals(resultErrorCode)) {
//			result.rejectValue("deliveryDate", null, "お届け日時は現在時刻から一時間後以降をご指定下さい");
//			model.addAttribute("message", "OK");
//		} else {
//			model.addAttribute("message", "NG");
//		}

		order.toString();

		order.setStatus(2);
		orderRepository.update(order);

		model.addAttribute("cardPaymentResult", cardPaymentResult);
		// 支払い方法の選択 TODO:注文確定画面でのメール送信時に使う。

		Date orderDate = new Date();
		order.setOrderDate(orderDate);

		String deliveryStr = " " + order.getDeliveryTime();
		try {
			Timestamp deliveryTime = new Timestamp(
					new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(deliveryStr).getTime());
			order.setDeliveryTime(deliveryTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		/** メール内容設定 */
		String mailTo = order.getDestinationEmail();
		String mailTitle = "らくらくピザ注文完了：" + order.getDestinationName() + "様";
		StringBuffer mailContent = new StringBuffer();
		mailContent.append(order.getDestinationName() + "様\n\nこの度はご注文ありがとうございます。");

		mailContent.append("\nご注文番号：" + order.getOrder_number());
		mailContent.append("\n\nお届け先氏名：" + order.getDestinationName() + "様");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd （E）");
		String mailOrderDate = format.format(orderDate);
		mailContent.append("\nご注文日時：" + mailOrderDate);
		mailContent.append("\nご注文の合計金額：" + order.getTotalPrice() + "円");
		mailContent.append("\nお届け日時：" + deliveryStr);
		mailContent.append("\n\n またのご利用をお待ちしております。");

		try {
			String mailContentStr = mailContent.toString();
			sendMailService.sendMail(mailTo, mailTitle, mailContentStr);
		} catch (Exception e) {
			order.setStatus(9);
			return "authenticationComplete";
		}

		return "orderComplete";
	}

	/**
	 * @param creditCardCancelForm
	 * @param model
	 * @return
	 */
	@RequestMapping("/cancel")
	public String cancel(CreditCardCancelForm creditCardCancelForm, Model model,
			@AuthenticationPrincipal LoginUser loginUser) {
		System.out.println("キャンセル処理開始");

		CreditCardCancelRequest requestBody = new CreditCardCancelRequest();
		BeanUtils.copyProperties(creditCardCancelForm, requestBody);

		URI uri = null;
		try {
			uri = new URI(SEND_URI + "cancel");
		} catch (URISyntaxException e) {
			model.addAttribute("message", "通信エラー");
			e.printStackTrace();
			return "authenticationComplete";
		}

		RequestEntity<CreditCardCancelRequest> requestEntity = RequestEntity.post(uri)
				.contentType(MediaType.APPLICATION_JSON).body(requestBody);

		ResponseEntity<CreditCardCancelResult> responseEntity = restTemplate.exchange(requestEntity,
				CreditCardCancelResult.class);

		// 今回はサクセスのみ

//		ステータスを９（キャンセル）に変更
//		order noアップデート

		User user = loginUser.getUser();
		Integer loginId = user.getId();
		Order order = orderRepository.findByUserIdAndStatus(loginId, 0);

		order.setStatus(9);
		orderRepository.update(order);

		model.addAttribute("order", order);
		model.addAttribute("responseEntity", responseEntity);

		// と
		return "cancelComplete";
	}

}
