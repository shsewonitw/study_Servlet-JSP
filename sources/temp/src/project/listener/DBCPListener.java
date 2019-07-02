package project.listener;

import java.io.*;
import java.sql.*;
import java.util.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDriver;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class DBCPListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent sce)  { 
		String poolConfig = sce.getServletContext().getInitParameter("POOL_CONFIG");
		Properties prop = new Properties();
		try {
			prop.load(new StringReader(poolConfig));
		} catch (IOException e) {
			throw new RuntimeException("config load fail", e);
		}
		loadJDBCDriver(prop);
		initConnectionPool(prop);
   }
	
	private void loadJDBCDriver(Properties prop) {
		String driverClass = prop.getProperty("JDBC_DRIVER");
		try {
			Class.forName(driverClass);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException("fail to load JDBC Driver", ex);
		}
	}
	
	private void initConnectionPool(Properties prop) {
	try {
		String url = prop.getProperty("JDBC_URL");
		String user = prop.getProperty("JDBC_USER");
		String password = prop.getProperty("JDBC_PASSWORD");
		
		ConnectionFactory connFactory = 
				new DriverManagerConnectionFactory(url, user, password);
		
		PoolableConnectionFactory poolableConnFactory = 
				new PoolableConnectionFactory(connFactory, null);
		
		String validationQuery = prop.getProperty("VALIDATION_QUERY");
		if(validationQuery != null && !validationQuery.isEmpty()) {
			poolableConnFactory.setValidationQuery(validationQuery);
		}
		
		GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
		
		poolConfig.setTimeBetweenEvictionRunsMillis(1000L * 60L * 5l);
		poolConfig.setTestWhileIdle(true);
		int minIdle = getIntProperty(prop, "MIN_IDLE", 5);
		poolConfig.setMinIdle(minIdle);
		int maxTotal = getIntProperty(prop, "MAX_TOTAL", 50);
		poolConfig.setMaxTotal(maxTotal);
		
		GenericObjectPool<PoolableConnection> connectioncPool = 
				new GenericObjectPool<>(poolableConnFactory,poolConfig);
		
		poolableConnFactory.setPool(connectioncPool);
		
		Class.forName("org.apache.commons.dbcp2.PoolingDriver");
		PoolingDriver driver = (PoolingDriver)
				DriverManager.getDriver("jdbc:apache:commons:dbcp:");
		String poolName = prop.getProperty("POOL_NAME");
		driver.registerPool(poolName, connectioncPool);
		
	} catch(Exception e) {
		throw new RuntimeException(e);
	}
		
	}
	
	private int getIntProperty(Properties prop, String propName, int defaultValue) {
		String value = prop.getProperty(propName);
		if(value == null)
			return defaultValue;
		return Integer.parseInt(value);
	}
	
     public void contextDestroyed(ServletContextEvent sce)  { 
         // TODO Auto-generated method stub
    }

     
    
}
