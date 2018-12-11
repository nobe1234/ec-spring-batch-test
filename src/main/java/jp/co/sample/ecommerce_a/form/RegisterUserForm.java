package jp.co.sample.ecommerce_a.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

/**
 * ユーザー登録情報を受け取るフォーム.
 * 
 * @author soheinobe
 *
 */
public class RegisterUserForm {

	/** 名前 */
	@NotBlank(message = "名前を入力してください")
	private String name;
	/** Eメール */
	@NotBlank(message = "メールアドレスを入力してください")
	@Email(message = "アドレスが不正です")
	private String email;
	/** パスワード */
//	@Size(min = 8, max = 16, message = "パスワードは8文字以上16文字以下で登録してください")
//	@Pattern(regexp = "^(?=.*?[AZ])(?=(.*[az]){1,})(?=(.*[\\d]){1,})(?=(.*[\\W]){1,})(?!.*\\s).{8,16}$",message="パスワードは半角数字8~16文字で、数字、大文字小文字それぞれ１文字以上含めて入力してください")
//	@Pattern(regexp = "^(?=.*?[a-z])(?=.*?[A-Z])(?=.*[0-9])[a-z\\d]{8,16}$", message = "パスワードは半角数字8~16文字で、数字、大文字小文字それぞれ１文字以上含めて入力してください")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z\\-]{8,16}$", message = "パスワードは半角数字8~16文字で、数字、大文字小文字それぞれ１文字以上含めて入力してください")
	@NotBlank(message = "パスワードを入力してください")
	private String password;
	/** 確認用パスワード */
	@NotBlank(message = "確認用パスワードを入力してください")
	private String confirmPassword;
	/** 郵便番号 */
	@NotBlank(message = "郵便番号を入力してください")
	@Pattern(regexp = "^[0-9]{7}$", message = "郵便番号は半角数字7桁、ハイフンなしで入力してください")
	private String zipcode;
	/** 住所 */
	@NotBlank(message = "住所を入力してください")
	private String address;
	/** 電話番号 */
	@NotBlank(message = "電話番号を入力してください")
	@Pattern(regexp = "^(070|080|090)-\\d{4}-\\d{4}$", message = "「-」（ハイフン）必須、070、080、090から始まる番号のみが使えます") // TODO:電話番号の正規表現
	private String telephone;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
