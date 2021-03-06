package common;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCTemplate {
	
	private String driver;
	private String url;
	private String user;
	private String password;
	
	private JDBCTemplate() {
		
		driver = "oracle.jdbc.driver.OracleDriver";
		url = "jdbc:oracle:thin:@localhost:1521:kyk";
		user = "kyk";
		password = "kyk";
	}
	
	private static class JdbcTemplateHolder{
		private static final JDBCTemplate instance = new JDBCTemplate();
	}
	
	public static JDBCTemplate getInstance() {
		return JdbcTemplateHolder.instance;
	}
	
	public Connection getConnection() {
		
		Connection con = null;
		String url = this.url;
		String user = this.user;
		String password = this.password;
		
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
			con.setAutoCommit(false);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
	
	public void close(Connection con) {
		
		try {
			if(con != null)
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//preparedStatement�� statement�� �ļ� Ŭ����
	//���� ó���� �����ϴ�.
	public void close(Statement stmt) {
		
		try {
			if(stmt != null)
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rs) {
		
		try {
			if(rs != null)
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rs, Statement stmt) {
		
		try {
			if(rs != null)
			rs.close();
			if(stmt != null)
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(ResultSet rs, Statement stmt, Connection con) {
		
		try {
			if(con != null)
			con.close();
			if(rs != null)
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void commit(Connection con) {
		
		try {
			con.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void rollback(Connection con) {
		
		try {
			con.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(PreparedStatement pstm, ResultSet rs) {
		
		try {
			rs.close();
			pstm.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void close(PreparedStatement pstm) {
		
		try {
			pstm.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
