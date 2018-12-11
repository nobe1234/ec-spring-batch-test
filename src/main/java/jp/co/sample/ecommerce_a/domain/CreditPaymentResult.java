package jp.co.sample.ecommerce_a.domain;

/**
 * 認証結果を表すドメイン.
 * 
 * APIから受け取る結果を指定するリクエストボディ。
 * 
 * @author soheinobe
 *
 */
public class CreditPaymentResult {

	/** ステータス */
	private String status;
	/** メッセージ */
	private String message;
	/** エラーコード */
	private String error_code;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getError_code() {
		return error_code;
	}

	public void setError_code(String error_code) {
		this.error_code = error_code;
	}

}
