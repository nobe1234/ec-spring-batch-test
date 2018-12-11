package jp.co.sample.ecommerce_a.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.Order;
import jp.co.sample.ecommerce_a.repository.OrderRepository;
import jp.co.sample.ecommerce_a.service.OrderService;

@RequestMapping("/downloadCsv")
@Controller
public class DownloadCsvController {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private OrderService orderSevice;

	/**
	 * 注文一覧を表示.
	 * 
	 * @return
	 */
	@RequestMapping("/")
	public String index(Model model) {
		// 注文情報を全件取得
		List<Order> orderList = new ArrayList<>();
		orderList = orderRepository.outputAllOrder();

		model.addAttribute("orderList", orderList);

		return "admin/allOrderList";
	}

	/**
	 * csvを出力する.
	 * 
	 * 管理者にのみ権限を付与。
	 * 
	 * @return
	 */
	@RequestMapping("/outputCsv")
	public void outputCsv(HttpServletResponse response) {

		// 文字コードと出力するCSVファイル名を設定
		response.setContentType(MimeTypeUtils.APPLICATION_OCTET_STREAM_VALUE + ";charset=utf-8");
		response.setHeader("Content-Disposition", "attachment; filename=\"test.csv\"");

		// try-with-resources文を使うことでclose処理を自動化
		try (PrintWriter pw = response.getWriter()) {

			// 書き出し用の文字列はサービスクラスで生成。全てのオーダーを取り出して書き出している。
			String outputString = orderSevice.ExportAllOrder();

			// CSVファイルに書き込み
			pw.print(outputString);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
