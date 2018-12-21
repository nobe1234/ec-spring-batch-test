package jp.co.sample.ecommerce_a;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Cookie関連の処理を行うクラス.
 * 
 * 主に閲覧履歴からおすすめ商品を表示する。
 * 
 * @author soheinobe
 *
 */
public class BrowsingHistoryWithCookie {

//	/**
//	 * ブラウザからCookie情報をえるクラス.
//	 * 
//	 * @param リクエスト
//	 * @param name
//	 * @return
//	 */
//	public String getCookie(HttpServletRequest request, String name) {
//		String result = null;
//
//		Cookie[] cookies = request.getCookies();
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (name.equals(cookie.getName())) {
//					result = cookie.getValue();
//					break;
//				}
//			}
//		}
//
//		return result;
//	}

	public static void setCookie(HttpServletRequest request, HttpServletResponse response, String path, String name,
			String value, int maxAge) {
		Cookie[] cookies = request.getCookies();
		List<Cookie> matchCookieList = new LinkedList<Cookie>();
//		String[] matchCookieList = new String[cookies.length];

		for (Cookie cookie : cookies) {
			if (cookie.getName().matches("cookie_.*")) {
				matchCookieList.add(cookie);
			}
		}

		System.out.println(matchCookieList.size());

//		if (matchCookieList.size() >= 5) {
//			System.out.println("------");
//			System.out.println(matchCookieList.get(0).getName());
//			matchCookieList.remove(0);
//			System.out.println(matchCookieList.size());
//			System.out.println(Arrays.asList(cookies));
//			System.out.println("消す前");
//			System.out.println(cookies[0].getName());
//			cookies[0].setMaxAge(0);
//			System.out.println("消したあと");
//			System.out.println(cookies[0].getName());
//			response.addCookie(cookies[0]);
//
//			System.out.println("反映した");
//			System.out.println(cookies[0].getName());
			// 配列からリストへの変換
//			List<Cookie> cookieList = new LinkedList<Cookie>(Arrays.asList(cookies));
//			System.out.println("cookieリストが以下の通り");
////			System.out.println(cookieList);
//			for (Cookie cookie : cookieList) {
//				System.out.println(cookie.getValue());
//			}
//			cookieList.remove(0);
//			System.out.println(cookies);
//		}
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		cookie.setPath(path);
		// httpsで稼働している環境であればCookieが暗号化されるようSecure属性をつける
//		if ("https".equals(request.getScheme())) {
//			cookie.setSecure(true);
//		}
		response.addCookie(cookie);

	}

}
