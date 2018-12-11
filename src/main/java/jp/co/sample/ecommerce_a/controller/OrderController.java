package jp.co.sample.ecommerce_a.controller;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.LoginUser;
import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.domain.User;
import jp.co.sample.ecommerce_a.form.CreditCardCancelForm;
import jp.co.sample.ecommerce_a.form.CreditCardForm;
import jp.co.sample.ecommerce_a.form.OrderForm;
import jp.co.sample.ecommerce_a.repository.OrderRepository;
import jp.co.sample.ecommerce_a.service.OrderDateService;

/**
 * 注文情報を受け取るコントローラー.
 * 
 * @author yume.hirata
 *
 */
@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderRepository orderRepository;
//	@Autowired
//	private SendMailService sendMailService;
	@Autowired
	private OrderDateService orderDateService;

	@Autowired
	private HttpServletRequest application;

//	/** シーケンスを取得するIDジェネレーター. */
//	@Autowired
//	private IdGenerator idGenerator;

	@ModelAttribute
	public OrderForm setUpOrderForm() {
		return new OrderForm();
	}

	/**
	 * クレジットカード情報を受け取るフォームを作成.
	 * 
	 * @return クレジットカード入力フォーム
	 */
	@ModelAttribute
	public CreditCardForm setUpcreditForm() {
		return new CreditCardForm();
	}

	/**
	 * キャンセルリクエストを受け取るフォームを作成.
	 * 
	 * @return 決済キャンセルフォーム
	 */
	@ModelAttribute
	public CreditCardCancelForm setUpCancelForm() {
		return new CreditCardCancelForm();
	}

	/**
	 * 注文確認画面を表示する.
	 * 
	 * @param model 情報を引き渡すための引数
	 * @return 注文確認画面
	 */
	@RequestMapping("/toOrder")
	public String toOrder(Model model, @AuthenticationPrincipal LoginUser loginUser) {
		/** ここでOrderテーブルの内容を引き渡す */
		Integer userId;

		User user = loginUser.getUser();
		userId = user.getId();

		Integer status = 0;

		Order order = orderRepository.findByUserIdAndStatusForShowCart(userId, status);
		model.addAttribute("order", order);

		return "order";
	}

	/**
	 * 注文完了画面を表示する.
	 * 
	 * @return 注文完了画面
	 */
	@RequestMapping("/toOrderComplete")
	public String toOrderComplete() {
		return "orderComplete";
	}

	/**
	 * 入力された情報でデータベース上の情報を更新する.
	 * 
	 * @param form   フォームで受け取った情報
	 * @param result エラーを表示する引数
	 * @param model  情報を引き渡すための引数
	 * @return 決済完了画面
	 */
	@RequestMapping("/confirm")
	public String order(@AuthenticationPrincipal LoginUser loginUser, @Validated OrderForm form, BindingResult result,
			Model model) {

		System.out.println();
		if (!(form.getDeliveryDate().equals(""))) {
			if (!(orderDateService.checkDate(form.getDeliveryDate() + " " + form.getDeliveryTime()))) {
				result.rejectValue("deliveryDate", null, "お届け日時は現在時刻から一時間後以降をご指定下さい");
			}
		}

		if (result.hasErrors()) {
			return toOrder(model, loginUser);
		}

		User user = loginUser.getUser();
		Order order = orderRepository.findByUserIdAndStatusForShowCart(user.getId(), 0); // 0=未入金状態

		BeanUtils.copyProperties(form, order);

		/** 商品合計金額をアップデート */
		int totalPrice = order.getCalcTotalPrice() + order.getTax();
		order.setTotalPrice(totalPrice);

		Date orderDate = new Date();
		order.setOrderDate(orderDate);

		String deliveryStr = form.getDeliveryDate() + " " + form.getDeliveryTime();
		try {
			Timestamp deliveryTime = new Timestamp(
					new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(deliveryStr).getTime());
			order.setDeliveryTime(deliveryTime);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		// FIXME:注文確定処理が確定したら消す
//		if (form.getPaymentMethod() == 1) {
//			order.setStatus(1);
//		} else if (form.getPaymentMethod() == 2) {
//			order.setStatus(2);
//		}

		// 有効期限（月）のプルダウン要素
		Map<Integer, Integer> expirationMonthMap = new LinkedHashMap<>();
		final int MAX_MONTH = 12;
		for (int i = 1; i <= MAX_MONTH; i++) {
			// TODO:1けたの場合、二桁に変換 01
			expirationMonthMap.put(i, i);
		}
		application.setAttribute("expirationMonthMap", expirationMonthMap);

		// 有効期限（年）のプルダウン要素
		Map<Integer, Integer> expirationYearMap = new LinkedHashMap<>();
		final int MAX_YEAR = 2032;
		for (int i = 2018; i <= MAX_YEAR; i++) {
			expirationYearMap.put(i, i);
		}
		application.setAttribute("expirationYearMap", expirationYearMap);

//		/** メール内容設定 */
//		String mailTo =order.getDestinationEmail();
//		String mailTitle ="らくらくピザ注文完了：" + order.getDestinationName() + "様";
//		StringBuffer mailContent = new StringBuffer();
//		mailContent.append(order.getDestinationName() + "様\n\nこの度はご注文ありがとうございます。");
//		
//		mailContent.append("\n\nお届け先氏名：" + order.getDestinationName() +"様");
//		try {
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd （E）");
//			String mailOrderDate = format.format(orderDate);
//			mailContent.append("\nご注文日時：" + mailOrderDate);
//		}catch (Exception e) {
//			e.printStackTrace();
//		}
//		mailContent.append("\nご注文の合計金額："+ order.getTotalPrice() + "円");
//		mailContent.append("\nお届け日時：" + deliveryStr);
//		mailContent.append("\n\n またのご利用をお待ちしております。");
//		
//		String mailContentStr = mailContent.toString();
//		sendMailService.sendMail(mailTo, mailTitle, mailContentStr);

		// 決済完了時にエラーとする。
		// order.setStatus(1);
		System.out.println(order.getDestinationTel());
		orderRepository.update(order);

		return "orderConfirm"; // TODO:確認
//		return "redirect:/order/toOrderComplete";
	}

}
