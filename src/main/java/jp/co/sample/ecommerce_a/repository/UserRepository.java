package jp.co.sample.ecommerce_a.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import jp.co.sample.ecommerce_a.domain.User;

/**
 * ユーザ情報の操作を行うリポジトリ.
 * 
 * @author soheinobe
 *
 */
@Repository
public class UserRepository {

	/**
	 * JDBC関連のオブジェクトを注入.
	 */
	@Autowired
	private NamedParameterJdbcTemplate template;

	private static final RowMapper<User> USER_ROW_MAPPER = (rs, i) -> {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setName(rs.getString("name"));
		user.setEmail(rs.getString("email"));
		user.setPassword(rs.getString("password"));
		user.setZipcode(rs.getString("zipcode"));
		user.setAddress(rs.getString("address"));
		user.setTelephone(rs.getString("telephone"));
		return user;
	};

	/**
	 * ユーザ情報をDBに新規登録するメソッド.
	 * 
	 * @param ユーザ情報
	 */
	public void insert(User user) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(user);

		String insertSql = "INSERT INTO users (name,email,password,zipcode,address,telephone) "
				+ "VALUES(:name,:email,:password,:zipcode,:address,:telephone)";
		template.update(insertSql, param);

	}

	/**
	 * メールアドレスの１件検索を行うメソッド.
	 * 
	 * メールアドレス重複チェック、もしくはハッシュ化したパスワードチェックに使う。
	 * 
	 * @param email
	 * @return
	 */
	public User findByEmail(String email) {
		String sql = "select id,name,email,password,zipcode,address,telephone from users " + "where email = :email";
		SqlParameterSource param = new MapSqlParameterSource().addValue("email", email);

		List<User> userList = template.query(sql, param, USER_ROW_MAPPER);
		if (userList.size() == 0) {
			return null;
		}
		// FIXME:以下のfor文の代わりにreturn userList.get(0);で良いのでは？
		for (User user : userList) {
			return user;
		}
		return null;
	}
	
	// FIXME:javadoc漏れ
	public void deleteById(Integer id) {
		String sql = "DELETE FROM users WHERE id = :id";
		
		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		
		template.update(sql, param);
	}

}
