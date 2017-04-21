//package com.monitoring.dao;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.ArrayList;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//import javax.sql.DataSource;
//
//import org.springframework.jdbc.datasource.DriverManagerDataSource;
//
//import com.monitoring.dto.MemoryDto;
//import com.mysql.jdbc.Driver;
//
//public class MemoryDao {
//	ArrayList<MemoryDto> memoryDtos = new ArrayList<MemoryDto>();
//	private Connection connection;
//	private PreparedStatement preparedStatement;
//	private ResultSet resultSet;
//	
//	private String driver = "com.mysql.jdbc.Driver";
//	private String url = "jdbc:mysql://localhost/3306/server_monitoring?useSSL=false";
//	private String id ="root";
//	private String password = "mju12345";
//	
//	public ArrayList<MemoryDto> memoryList() throws SQLException {
//		try {
//			String query = "SELECT * FROM memory";
//			preparedStatement = connection.prepareStatement(query);
//			resultSet = preparedStatement.executeQuery();
//			
//			while (resultSet.next()) {
//				String totalMemory = resultSet.getString("total_memroy");
//				String usedMemory = resultSet.getString("used_memroy");
//				String freeMemory = resultSet.getString("free_memroy");
//				
//				System.out.println(totalMemory);
//				System.out.println(usedMemory);
//				System.out.println(freeMemory);
//				
//				MemoryDto memoryDto = new MemoryDto();
//				memoryDto.setTotalMemory(totalMemory);
//				memoryDto.setUsedMemory(usedMemory);
//				memoryDto.setFreeMemory(freeMemory);
//				
//				memoryDtos.add(memoryDto);
//			}
//		} catch (Exception e) {
//				e.printStackTrace();
//		} finally {
//			if (preparedStatement == null) {
//				preparedStatement.close();
//			} else if (connection == null) {
//				connection.close();
//			} else if (resultSet == null) {
//				resultSet.close();
//			}
//		}
//		
//		return memoryDtos;
//	}
//}
