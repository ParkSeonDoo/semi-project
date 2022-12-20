package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConn {	
	String driver = "com.mysql.cj.jdbc.Driver"; //드라이버명 상수. 
	
	String db = "myproject";
	String path = "jdbc:mysql://localhost:3306/"; //mysql 로컬호스트 3306(ip주소)/db명
	String unicode = "?characterEncoding=utf8&useUnicode=true"; // 한글호환되도록 utf8
	String user = "root"; //mysql 유저명
	String pwd = "11111111"; //패스워드
	Connection conn; //import해서 사용. db와 연결해주는 역할
	
	public DBConn() {
		try {
			Class.forName(driver);//데이터베이스 - 연결 클래스 찾아서 드라이버 로딩.
								  //class클래스는 자바가상머신에서 동작할 클래스들의 정보를 묘사.
			System.out.println("SUCCESS connection");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public Connection getConn() {//연결 클래스. db와 연결해줌
		try {
		conn = DriverManager.getConnection(path+db+unicode, user, pwd);
		} catch (Exception e) {
		e.printStackTrace();
	}
		return conn;
	}

	public DBConn(String db) {
		this();
		this.db = db;
	}
	
	public static void main(String[] args) {
		DBConn dbconn = new DBConn(); //mysql demon 실행중이어야함. 단독으로 쓸일없음 메인만든건 테스트용
	}
}
