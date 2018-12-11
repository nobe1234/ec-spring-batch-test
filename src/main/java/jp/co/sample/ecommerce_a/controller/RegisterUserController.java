package jp.co.sample.ecommerce_a.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import jp.co.sample.ecommerce_a.domain.User;
import jp.co.sample.ecommerce_a.form.RegisterUserForm;
import jp.co.sample.ecommerce_a.repository.UserRepository;

/**
 * ユーザ情報を登録するコントローラー.
 * 
 * ユーザー登録フォームより情報を受け取る。
 * 
 * @author soheinobe
 *
 */
@Controller
@RequestMapping("/registerController")
public class RegisterUserController {

	/** ユーザリポジトリを注入. */
	@Autowired
	private UserRepository userRepository;

	/** パスワードエンコーダ */
	@Autowired
	private PasswordEncoder passwordEncoder;

	/**
	 * 登録フォーム関連づけるための空のフォームを作成するメソッド.
	 * 
	 * @return 空のフォーム
	 */
	@ModelAttribute
	public RegisterUserForm setUpRegisterUserForm() {
		return new RegisterUserForm();
	}

	/**
	 * ユーザ登録画面を表示するメソッド.
	 * 
	 * @return ユーザ登録画面
	 */
	@RequestMapping("/toRegisterUser")
	public String toUserRegister(Model model) {
		return "registerUser";
	}

	/**
	 * ユーザー登録を行い、入力内容に問題なければログイン画面を表示する.
	 * 
	 * @param リクエストパラメータ
	 * @param エラーチェック
	 * @return ログイン画面
	 */
	@RequestMapping("/registerUser")
	public String registerUser(@Validated RegisterUserForm registerUserForm, BindingResult result, Model model) {

		if (!(registerUserForm.getPassword().equals(registerUserForm.getConfirmPassword()))) {
			result.rejectValue("password", null, "入力されたパスワードが異なります。");
		}

		User verificationUser = new User();
		verificationUser = userRepository.findByEmail(registerUserForm.getEmail());
		if (verificationUser != null) {
			result.rejectValue("email", null, "メールアドレスが重複しています。");
		}

		if (result.hasErrors()) {
			return toUserRegister(model);
		}

		User insertUser = new User();
		// 入力された生パスワード
		String rowPassword = registerUserForm.getPassword();
		// パスワードを暗号化
		BeanUtils.copyProperties(registerUserForm, insertUser);
		String encodePassword = passwordEncoder.encode(rowPassword);
		insertUser.setPassword(encodePassword);
		userRepository.insert(insertUser);
		return "redirect:/";
	}

}
