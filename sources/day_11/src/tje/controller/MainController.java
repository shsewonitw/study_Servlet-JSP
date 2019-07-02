package tje.controller;

import java.io.*;
import java.util.*;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import tje.service.Service;

public class MainController extends HttpServlet {
	private HashMap<String,Service> uriMap = new HashMap<>();
		
	@Override
	public void init(ServletConfig config) throws ServletException {
		
		// URI와 각 URI가 요청될 때 실행할 서비스 클래스의 이름이 등록된
		// 파일의 이름 및 실제 경로의 값을 추출
		String strConfigFile = config.getInitParameter("ConfigFile");
		String strConfigFilePath = config.getServletContext().getRealPath(strConfigFile);
		
		// 설정정보를 저장하기 위한 Properties 객체 생성
		// Properties 클래스는 '키=값' 의 형태로 데이터를 저장할 수 있는 클래스
		// (읽어오는 것도 가능)
		Properties prop = new Properties();
		try (FileReader fr = new FileReader(strConfigFilePath)){
			prop.load(fr);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Iterator<Object> keyIter = prop.keySet().iterator();
		while(keyIter.hasNext()) {
			String strServiceName = (String)keyIter.next();
			String strServiceClassName = prop.getProperty(strServiceName);
			
			try {
				// 클래스의 이름을 사용하여 해당 클래스 타입을 제어할 수 있는 객체를 생성하는 방법
				// Class 타입의 객체를 사용하여 임의의 타입의 객체를 생성할 수 있음
				Class<?> serviceClass = Class.forName(strServiceClassName);
				Service service = (Service)serviceClass.newInstance();
				// URI 정보와 해당 URI 요청 시 실행할 서비스 객체를 MAP에 등록
				uriMap.put(strServiceName, service);
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		super.init(config);
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String requestURI = request.getRequestURI().substring(request.getContextPath().length());
		
		String viewPage = null;
		
		Service service = null;
		
		
		if( (service = uriMap.get(requestURI)) != null )
			viewPage = service.service(request, response);
		else
			response.sendError(404);
		
		
		if( viewPage != null)
			request.getRequestDispatcher(viewPage).forward(request, response);
	}

}
