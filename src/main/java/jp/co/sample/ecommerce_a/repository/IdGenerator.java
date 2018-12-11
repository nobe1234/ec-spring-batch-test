package jp.co.sample.ecommerce_a.repository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class IdGenerator {

	@Autowired
	private JdbcTemplate template;

	LocalDate localDate = LocalDate.now();
	DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	String today = localDate.format(dateTimeFormatter);

//	@Autowired
//	private OrderRepository orderRepository;
//	 

	public String genetateId() {
//		Order order =  orderRepository.fi
//		boolean isCheckOrderNumber = 
////		if () {
////			
////		}
		String seq = today + this.template.queryForObject("select to_char(nextval('orders_order_number_seq'),'FM0000')",
				String.class);
		return seq;

	}

}
