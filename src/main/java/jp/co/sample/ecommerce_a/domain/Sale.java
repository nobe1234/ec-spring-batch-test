package jp.co.sample.ecommerce_a.domain;

/**
 * 売上の情報を保持するドメイン.
 * 
 * @author soheinobe
 *
 */
public class Sale {

	/** id */
	private Integer id;
	/** 売上月 */
	private Integer monthOfYear;
	/** 売上年 */
	private String year;
	/** 月間売上 */
	private Integer MonthlySales;

	@Override
	public String toString() {
		return "Sales [id=" + id + ", monthOfYear=" + monthOfYear + ", year=" + year + ", MonthlySales=" + MonthlySales
				+ "]";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMonthOfYear() {
		return monthOfYear;
	}

	public void setMonthOfYear(Integer monthOfYear) {
		this.monthOfYear = monthOfYear;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public Integer getMonthlySales() {
		return MonthlySales;
	}

	public void setMonthlySales(Integer monthlySales) {
		MonthlySales = monthlySales;
	}

}
