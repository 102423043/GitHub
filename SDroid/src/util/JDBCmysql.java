package util;

import java.sql.*;

public class JDBCmysql {

	private Connection con = null; 
	private ConfigFile config = null;

	public Connection getConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			config = new ConfigFile();
			con = DriverManager.getConnection( 
							config.getPropValue("mysqlInfo")+"?"+
							config.getPropValue("unicode"),
							config.getPropValue("loginAccount"),
							config.getPropValue("loginPwd"));

		} catch (ClassNotFoundException e) {
			LogInfo.error("DriverClassNotFound: %s", e.toString());
		} catch (SQLException x) {
			LogInfo.error("Exception: %s", x.toString());
		}
		return con;
	}

}
