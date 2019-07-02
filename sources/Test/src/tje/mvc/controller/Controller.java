package tje.mvc.controller;

import tje.mvc.service.*;
import java.io.*;
import java.lang.reflect.Constructor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class Controller extends HttpServlet {
	
	private HashMap<String,Service> map = new HashMap<>();
	
	@Override
	public void init(ServletConfig config) throws ServletException {

		String strConfigFile = config.getInitParameter("ConfigFile");
		String strConfigFilePath = config.getServletContext().getRealPath(strConfigFile);
		System.out.println(strConfigFile+", "+strConfigFilePath);
		Properties prop = new Properties();
		
		try(FileReader fr = new FileReader(strConfigFilePath)) {
			prop.load(fr);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Iterator<Object> keyIter = prop.keySet().iterator();
		
		while(keyIter.hasNext()) {
			String strServiceName = (String)keyIter.next();
			String strServiceClassName = prop.getProperty(strServiceName);
			
			try {				
				Class<?> serviceClass = 
						Class.forName(strServiceClassName);
				
				// 생성자 목록을 반환				
				Constructor [] constructors = 
						serviceClass.getConstructors();
				// 특정 생성자를 저장하기 위한 변수 
				Constructor cs = null;
				try {
					cs = serviceClass.getConstructor(ServletConfig.class);
				} catch(Exception e) {
					cs = null;
				}
				
				Service service = null;
				//if( constructors[0].getParameterCount() == 0 )
				if( cs == null )
					service = (Service)serviceClass.newInstance();
				else {
					System.out.println(serviceClass.getName());
					System.out.println(cs.getParameterCount());
					System.out.println(cs.getParameters()[0].getType());
					
					// 동적으로 생성자의 매개변수를 전달하는 방법
					service = (Service)cs.newInstance(config);
					service = (Service)constructors[0].newInstance(config);
				}				
				// service.init(config);
			
				map.put(strServiceName,service);
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
		
		
		if( (service = map.get(requestURI)) != null )
			viewPage = service.service(request, response);
		else
			response.sendError(404);
		
		
		if( viewPage != null)
			request.getRequestDispatcher(viewPage).forward(request, response);
	}
}
