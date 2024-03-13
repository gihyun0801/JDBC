package edu.kh.jdbc1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import edu.kh.jdbc1.model.vo.Employee;

public class JDBCExample4 {
     public static void main(String[] args) {
		
    	 // 직급명, 급여를 입력받아
    	 // 해당 직급에서 입력 받은 급여보다 많이 받는 사원의
    	 // 이름, 직급명, 급여, 연봉을 조회하여 출력
    	 
    	 
    	 // 단 , 조회 결과가 없으면 "조회 결과 없음" 출력
    	 
    	 // 조회 결과가 있으면 아래와 같이 출력
    	 // 직급명 입력 : 부사장
    	 // 급여 입력 : 5000000 
    	 // 송종기 /부사장 / 6000000 / 72000000 
    	 // 노옹철 /부사장 / 3700000 / 44000000
    	 
    	 Connection conn = null;
    	 
    	 Statement stmt = null;
    	 
    	 ResultSet rs = null;
    	 
    	 Scanner sc = new Scanner(System.in);
    	 
    	 try {
    		 
    		 Class.forName("oracle.jdbc.driver.OracleDriver");
    		 
    		 List<Employee> list = new ArrayList<Employee>();
    		 
    		 System.out.print("직급명 입력 : ");
    		 String job = sc.nextLine();
    		 
    		 System.out.print("급여 입력 : ");
    		 String salary = sc.nextLine();
    		 
    		 String url = "jdbc:oracle:thin:@";
    		 String Ip = "127.0.0.1";
    		 String port = ":1521";
    		 String database = ":xe";
    		 String Id = "kh_sgh";
    		 String pw = "kh1234";
    		 
    		 String sql = "SELECT EMP_NAME, JOB_NAME, SALARY, SALARY * 12 AS 연봉 FROM EMPLOYEE JOIN JOB USING(JOB_CODE) WHERE JOB_NAME = " + "'"+ job + "'" + "AND SALARY > " + salary;
     		 
    		 conn = DriverManager.getConnection(url+Ip+port+database, Id, pw);
    		 
    		 stmt = conn.createStatement();
    		 
    		 rs = stmt.executeQuery(sql);
    		 
    		 while(rs.next()) {
    			 
    			 String empName = rs.getString("EMP_NAME");
    			 String job2 = rs.getString("JOB_NAME");
    			 int salary2 = rs.getInt("SALARY");
    			 int announce = rs.getInt("연봉");
    			 
    			 list.add(new Employee(empName,job2,salary2,announce));
    			 
    			 
    			 
    		 }
    		 
    		 if(list.isEmpty()) {
    			 
    			 System.out.println("조회 결과 없음");
    		 }else {
    			 
    			 for(Employee em : list) {
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
