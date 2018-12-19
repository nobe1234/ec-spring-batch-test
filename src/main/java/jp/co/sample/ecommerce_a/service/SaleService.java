package jp.co.sample.ecommerce_a.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jp.co.sample.ecommerce_a.domain.Sale;
import jp.co.sample.ecommerce_a.repository.SalesRepository;

/**
 * 売上関連の処理をするサービスクラス.
 * 
 * @author soheinobe
 *
 */
@Service
public class SaleService {

	/**
	 * saleリポジトリを注入.
	 */
	@Autowired
	private SalesRepository salesRepository;

	/**
	 * 年数を受け取って、該当する年の月ごとの売上合計金額を返す.
	 * 
	 * @param year
	 * @return
	 */
	public List<Integer> findByYear(String year) {
		List<Sale> saleList = salesRepository.findByYear(year);
		List<Integer> monthlySaleList = new ArrayList<>();
		for (Sale sale : saleList) {
			Integer monthlySale = sale.getMonthlySales();
			monthlySaleList.add(monthlySale);
		}

		return monthlySaleList;
	}

	/**
	 * テーブル全件検索 売上.
	 * 
	 * @return
	 */
	public List<Sale> findAll() {
		return salesRepository.findAll();
	}
}
