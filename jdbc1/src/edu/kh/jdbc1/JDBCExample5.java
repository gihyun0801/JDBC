package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample5 {
    public static void main(String[] args) {
		
    	//입사일을 입력 받아 ("2000-01-01") 받아
    	//입력 받은 값 보다 먼저 입사한 사람의
    	//이름, 입사일, 성별(M, F) 조회
    	
    	Connection conn = null;
    	
    	Statement stmt = null;
    	
    	ResultSet rs = null;
    	
    	Scanner sc = new Scanner(System.in);
    	
    	try {
    		
    		System.out.print("입사일 입력(YYYY-MM-DD) : ");
    		String date = sc.nextLine();
    		
    		Class.forName("oracle.jdbc.driver.OracleDriver");
    		
    		String type = "jdbc:oracle:thin:@";
 	       String ip = "localhost";  
 	       String port = ":1521";
 	       String sid = ":XE";  
 	       String user = "kh_sgh";
 	       String pw = "kh1234"; 
 	       
 	       conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
 	       
 	       stmt = conn.createStatement();
 	       
 	       
 	       String sql = "SELECT EMP_NAME AS 이름, TO_CHAR(HIRE_DATE, 'YYYY\"년\"MM\"월\"DD\"일\"') AS 입사일, DECODE(SUBSTR(EMP_NO, 8, 1), 1, 'M', 2, 'F') AS 성별"
 	       		+ " FROM EMPLOYEE"
 	       		+ " WHERE HIRE_DATE < " + "'"+ date + "'"; // '' 형식으로 보내야 
 	       
 	       rs = stmt.executeQuery(sql);
 	       
 	       List<Employee> list = new ArrayList<Employee>();
 	       
 	       while(rs.next()) {
 	    	   
 	    	   Employee emp = new Employee();
 	    	   
 	    	   emp.setEmpName(rs.getString("이름"));
 	    	   emp.setHireDate(rs.getString("입사일"));
 	    	   emp.setGender(rs.getString("성별").charAt(0));
 	    	   
 	    	   list.add(emp);
 	    	   
 	       }
 	       
 	       if(list.isEmpty()) {
 	    	   System.out.println("조회 결과 없음");
 	       }else {
 	    	   
 	    	   for(int i = 0 ; i <  list.size(); i++) {
 	    		   
 	    		   
 	    		   System.out.printf("%02d) %s / %s / %c\n", i+ 1, list.get(i).getEmpName() , list.get(i).getHireDate() , list.get(i).getGender()       );
 	    		   
 	    	   }
 	    	   
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
