package jp.co.sample.ecommerce_a.service;

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
	 * テーブル全件検索 売上.
	 * 
	 * @return
	 */
	public List<Sale> findAll() {
		return salesRepository.findAll();
	}
}
