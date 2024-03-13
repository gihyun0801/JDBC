package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Emp;

public class JDBCExample3 {
   public static void main(String[] args) {
	
	   // 부서명을 입력받아 같은 부서에 있는 사원의 
	   // 사원명, 부서명, 급여 조회
	   
	   
	   Connection conn = null;
	   
	   Statement stmt = null;
	   
	   ResultSet rs = null;
	   
	   Scanner sc = new Scanner(System.in);
	   
	   try {
		   
		   System.out.print("부서명 입력 : "); // 총무부
		   String str = sc.nextLine();
		   
		   Class.forName("oracle.jdbc.driver.OracleDriver");
		   
		   //url + host + port + datebase
		   String type = "jdbc:oracle:thin:@";
	       String ip = "localhost";  
	       String port = ":1521";
	       String sid = ":XE";  
	       String user = "kh_sgh";
	       String pw = "kh1234"; 
	       
	       //길 뚫기
	       conn = DriverManager.getConnection(type+ip+port+sid, user, pw);
	       
	       
	       //버스 만들기
	       stmt = conn.createStatement();
	       
	       String sql = "SELECT EMP_ID, SALARY, NVL(DEPT_TITLE, '부서없음') AS DEPT_TITLE FROM EMPLOYEE LEFT JOIN DEPARTMENT ON(DEPT_CODE = DEPT_ID) WHERE DEPT_TITLE = " + "'" + str + "'";
		   
	       //java에서 작성되는 sql에
	       //문자열 
	       
	       rs = stmt.executeQuery(sql);
	       
	       //조회 결과 (rs)를 List 옮겨담기
	       
	       List<Emp> list = new ArrayList<Emp>();
	       
	     
	       
	       while(rs.next()) {
	    	   
	    	   String empId = rs.getString("EMP_ID");
	    	   String empTitle = rs.getString("DEPT_TITLE");
	    	   int salary = rs.getInt("SALARY");
	    	   
	    	   
	    	   // 현재 행에 존재하는 컬럼값 얻어오기
	    	   Emp emp = new Emp(empId,empTitle, salary);
	    	   
	    	   list.add(emp);
	    	   
	    	   
	    	   
	       }
	       
	       //만약에 List에 추가되면 Emp 객체가 없다면 "조회 결과 없음"
	       //있다면 순차적으로 출력
	       
	       if(list.isEmpty()) {
	    	   //만약에 리스트가 비어있는경우 
	    	   System.out.println("조회 결과 없음");
	       }else {
	    	   
	    	   for(Emp em : list) {
	    		   System.out.println(em);
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
