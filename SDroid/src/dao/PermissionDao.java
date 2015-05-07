package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Permission;
import util.JDBCmysql;
import util.LogInfo;
import util.ResultSetMapper;


public class PermissionDao {

	private JDBCmysql sql;
	private Connection con = null; 
	private Statement stat = null; 
	private ResultSet rs = null; 
	private PreparedStatement pst = null; 
	private String sqlStr = "";
	private LogInfo logInfo = null;
	
	private String tableName = "permission";
	
	public PermissionDao(){
		con = new JDBCmysql().getConnection();
		logInfo = new LogInfo();
	}
	
	public Permission getListById(int pid){
		
		List<Permission> pojoList = new ArrayList<Permission>();
		ResultSetMapper<Permission> resultSetMapper = new ResultSetMapper<Permission>();
		sqlStr = "select * from "+tableName+" as p where p.id = '"+pid+"' ";
		
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(sqlStr); 
	      logInfo.info("getListById: %s", sqlStr);	
	      
	      pojoList= resultSetMapper.mapRersultSetToObject(rs, Permission.class);
	      
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("getListById DropDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    }	
	    return pojoList.get(0);
	}
	
	public List<Permission> getAllList(){
		List<Permission> pojoList = new ArrayList<Permission>();
		ResultSetMapper<Permission> resultSetMapper = new ResultSetMapper<Permission>();
		sqlStr = "select * from "+tableName;
		
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(sqlStr); 
	      logInfo.info("getAllList: %s", sqlStr);	
	      
	      pojoList= resultSetMapper.mapRersultSetToObject(rs, Permission.class);
	      
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("getAllList DropDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    }	
	    return pojoList;
	}
	
	private void Close() 
	{ 
	  try 
	  { 
	    if(rs!=null) 
	    { 
	      rs.close(); 
	      rs = null; 
	    } 
	    if(stat!=null) 
	    { 
	      stat.close(); 
	      stat = null; 
	    } 
	    if(pst!=null) 
	    { 
	      pst.close(); 
	      pst = null; 
	    } 
	  } 
	 catch(SQLException e) 
	  { 
		 logInfo.error("Close Exception : %s", e.toString());
	  } 
	}
}
