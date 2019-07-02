package tje.servlet.cookie;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/cookie_03")
public class CookieServlet_03 extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 쿠키
		// 클라이언트(웹 브라우저)에 저장할 정보를 기록하는 방법을 제공
		// 키와 값의 형태로 데이터를 저장할 수 있음
		
		// 쿠키의 사용 과정 
		// # 서버에서 클라이언트로 쿠키를 저장하는 과정
		// 1. 서버에서 쿠키 객체를 생성
		// 2. 서버에서 생성된 쿠키 객체를 응답(response)에 추가하여 클라이언트에 전달
		// 3. 클라이언트는 서버로부터 전달받은 쿠키를 URL정보와 함께 저장
		// # 클라이언트에서 서버로 쿠키를 전송하는 과정
		// 4. 클라이언트(웹 브라우저)는 서버로 요청을 보낼때 현재 URL과 연관된 쿠키를 검색
		// 5. 쿠키가 존재한다면 쿠키를 요청(request)에 추가하여 전달
		// 6. 서버는 getCookies 메소드를 사용하여 클라이언트가 전달한 쿠키의 배열을 반환받음
		//    (null 값이 반환될 수 있음)
		// 7. 서버는 쿠키의 배열을 반복문을 활용하여 특정 name(key)의 쿠키 값을 전달받아 요청을 처리
		
		// 쿠키의 유효 시간 설정
		// setMaxAge 메소드
		// - 쿠키를 유지할 시간을 입력하는 메소드
		// - 단위는 초
		// - 하루동안 유지하는 쿠키인 경우 60 * 60 * 24
		
		// 1분동안 유지되는 쿠키 객체 생성
		Cookie cookie_60 = new Cookie("test_age_60","60");
		cookie_60.setMaxAge(60);
		response.addCookie(cookie_60);
		
		// 10분동안 유지되는 쿠키 객체 생성
		Cookie cookie_600 = new Cookie("test_age_600","600");
		cookie_600.setMaxAge(600);
		response.addCookie(cookie_600);
	}

}
