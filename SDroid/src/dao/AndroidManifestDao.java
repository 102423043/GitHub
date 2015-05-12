package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import util.JDBCmysql;
import util.LogInfo;

public class AndroidManifestDao {

	private Connection con = null;
	private Statement stat = null;
	private ResultSet rs = null;
	private PreparedStatement pst = null;
	private String sqlStr = "";
	private LogInfo logInfo = null;

	private String tableName = "androidManifest";

	public AndroidManifestDao() {
		con = new JDBCmysql().getConnection();
		logInfo = new LogInfo();
	}

	/**
	 * 功能: 新增一筆 AndroidManifest 資料 amf: AndroidManifest 的物件
	 * */
	public void insert(int aId , List<Integer> pIds) {
		sqlStr = "INSERT INTO " + tableName 
				+ " (app_id,permission_id)"
				+ " VALUES (?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sqlStr);
			for(Integer pId: pIds){
				ps.setInt(1, aId);
				ps.setInt(2, pId);
				ps.addBatch();
			}
			ps.executeBatch();
			con.commit();
			
			System.out.println("insertSQL:" + sqlStr);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void Close() {
		try {
			if (rs != null) {
				rs.close();
				rs = null;
			}
			if (stat != null) {
				stat.close();
				stat = null;
			}
			if (pst != null) {
				pst.close();
				pst = null;
			}
		} catch (SQLException e) {
			logInfo.error("Close Exception : %s", e.toString());
		}
	}
}
