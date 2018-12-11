package jp.co.sample.ecommerce_a.service;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

@Service
//FIXME:javadoc漏れ
public class OrderDateService {

	public boolean checkDate(String orderDate) {
		LocalDateTime currentLocalDateTime = LocalDateTime.now().plusHours(1);
		try {
			Timestamp time = new Timestamp(
					new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(orderDate).getTime());
			LocalDateTime deliveryTime = time.toLocalDateTime();
			if(!(deliveryTime.isAfter(currentLocalDateTime))) {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return true;
	}

}
