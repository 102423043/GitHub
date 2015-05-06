package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Application;
import util.JDBCmysql;
import util.LogInfo;
import util.ResultSetMapper;

public class ApplicationDao {

	private JDBCmysql sql;
	private Connection con = null; 
	private Statement stat = null; 
	private ResultSet rs = null; 
	private PreparedStatement pst = null; 
	private String sqlStr = "";
	
	private String tableName = "application";
	
	public ApplicationDao(){
		con = new JDBCmysql().getConnection();
	}
	
	public Application getListById(int aid){
		
		List<Application> pojoList = new ArrayList<Application>();
		ResultSetMapper<Application> resultSetMapper = new ResultSetMapper<Application>();
		sqlStr = "select * from "+tableName+" as a where a.id = '"+aid+"' ";
		
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(sqlStr); 
	      LogInfo.info("getListById: %s", sqlStr);	
	      
	      pojoList= resultSetMapper.mapRersultSetToObject(rs, Application.class);
	      
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
	
	public List<Application> getAllList(){
		
		List<Application> pojoList = new ArrayList<Application>();
		ResultSetMapper<Application> resultSetMapper = new ResultSetMapper<Application>();
		sqlStr = "select * from "+tableName;
		
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(sqlStr); 
	      LogInfo.info("getAllList: %s", sqlStr);	
	      
	      pojoList= resultSetMapper.mapRersultSetToObject(rs, Application.class);
	      
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
		 LogInfo.error("Close Exception : %s", e.toString());
	  } 
	}
	
}
