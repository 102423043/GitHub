package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.Policy;
import util.JDBCmysql;
import util.LogInfo;
import util.ResultSetMapper;

public class PolicyDao {

	private JDBCmysql sql;
	private Connection con = null; 
	private Statement stat = null; 
	private ResultSet rs = null; 
	private PreparedStatement pst = null; 
	private String sqlStr = "";
	private LogInfo logInfo = null;
	
	private String tableName = "policy";
	
	public PolicyDao(){
		con = new JDBCmysql().getConnection();
		logInfo = new LogInfo();
	}
	
	public boolean insert(Policy policy){
		boolean res = false;
		SimpleDateFormat bartDateFormat = new SimpleDateFormat("yyyy-MM-dd");       
		
		String sqlStr = String.format("INSERT INTO "+tableName+
				" (type,policy,create_time) VALUES ('%s','%s','%s')",
				policy.getType(),policy.getPolicy(),bartDateFormat.format(new Date()));
		
	    try 
	    { 
	      stat = con.createStatement(); 
	      int check = stat.executeUpdate(sqlStr); 
	      System.out.println("insertSQL:"+sqlStr);	
	      
	      if(check !=0)
	      {
	    	  res = true;
	      }
	      
	    } 
	    catch(SQLException e) 
	    { 
	      System.out.println("insert DropDB Exception :" + e.toString()); 
	    } 
	    finally 
	    { 
	      Close(); 
	    }		
		
		return res;		
		
	}
	
	public List<Policy> getAllList(String type){
		List<Policy> pojoList = new ArrayList<Policy>();
		ResultSetMapper<Policy> resultSetMapper = new ResultSetMapper<Policy>();
		sqlStr = "select * from "+tableName+" ";
		sqlStr +="where type='"+type+"'";
	    try 
	    { 
	      stat = con.createStatement(); 
	      rs = stat.executeQuery(sqlStr); 
	      logInfo.info("getAllList: %s", sqlStr);	
	      
	      pojoList= resultSetMapper.mapRersultSetToObject(rs, Policy.class);
	      
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
