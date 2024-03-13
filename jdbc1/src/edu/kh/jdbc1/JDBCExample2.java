package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class JDBCExample2 {
  public static void main(String[] args) {
	
	  // 1단계 : JDBC 객체 참조변수 생성
	  
	  Connection conn = null;
	  
	  Statement stmt = null;
	  
	  ResultSet rs = null;
	  
	  Scanner sc = new Scanner(System.in);
	  
	  try {
		  
		  // 2단계 :알맞은 객체 대입
		  
		  
		  //오라클 드라이버를 메모리에 로드
		  Class.forName("oracle.jdbc.driver.OracleDriver");
		  
		   String type = "jdbc:oracle:thin:@";
	       String ip = "localhost";  
	       String port = ":1521";
	       String sid = ":XE";  
	       String user = "kh_sgh";
	       String pw = "kh1234"; 
	       
	       //길 뚫기
	       conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
	       
	       System.out.println("<입력 받는 급여보다 많이 받는(초과) 직원만 조회>");
	       System.out.print("급여 입력 : ");
	       int input = sc.nextInt(); //2000000
	       
	       String sql = "SELECT EMP_ID, EMP_NAME , SALARY FROM EMPLOYEE WHERE SALARY > " + input;
	       
	       
	       stmt = conn.createStatement();
	       
	       rs = stmt.executeQuery(sql);
	       
	       while(rs.next()) {
	    	   
	    	   String empId = rs.getString("EMP_ID");
	    	   
	    	   String empName = rs.getString("EMP_NAME");
	    	   
	    	   int Salary = rs.getInt("SALARY");
	    	   
	    	   
	    	   System.out.printf("사원번호 :  %s / 이름 : %s / 급여 : %d\n", empId, empName, Salary);
	    	   
	       }
	       
	       
	       
	       
	       
	       
		  
		  
	  }catch(Exception e) {
		  e.printStackTrace();
	  }finally {
		  
		  try {
			  
			  if(rs != null) rs.close();
			  if(stmt != null) stmt.close();
			  if(conn != null) conn.close();
			  
			  
		  }catch(Exception e) {
			  e.printStackTrace();
		  }
		  
		  
	  }
	  
	  
}
}
