package util;

import java.util.ArrayList;
import java.util.List;

import model.Application;
import model.InstallTimePolicy;
import model.Policy;
import model.PolicyMatched;
import dao.ApplicationDao;
import dao.PolicyDao;
import dao.PolicyMatchedDao;

/**
 * 政策比對機制
 * */
public class PolicyMatchedUtil {

	// Dao
	private PolicyDao plDao;
	private ApplicationDao aDao;
	private PolicyMatchedDao pmDao;
	//Util
	private ParseXML parseXML;
	private ConfigFile config;
	private APKDownload apkDownload;
	
	/**
	 * 功能: 初始化設定
	 * */
	public PolicyMatchedUtil(){
		
		plDao = new PolicyDao(); 
		aDao = new ApplicationDao();
		pmDao = new PolicyMatchedDao();
		
		parseXML = new ParseXML();
		config = new ConfigFile();
		apkDownload = new APKDownload();
	}
	
	/**
	 * 功能: 政策比對分析
	 * pkName：Android package name
	 * */
	public String analysisPolicyMatched(String pkName){
		
		String status = null;
		Application app = aDao.findByPkName(pkName);
		
		if(app == null){
			apkDownload.SearchAPKFromGooglePlay(pkName);
			app = apkDownload.getApplication();
		}
		//搜尋Policies
		List<Policy> pList = plDao.getListByAppName(app.getAppLabel());
		List<InstallTimePolicy> itpList = new ArrayList<InstallTimePolicy>();
		if(pList.size()>0){
			//有Policies
			for(Policy p : pList){
				InstallTimePolicy itp = (InstallTimePolicy) parseXML
						.XMLParseToInstallObj(p.getPolicy());
//				itpList.add(itp);
				
			}
			
			
		}else{
			//無Policies
			status =config.getPropValue("noData");
		}
		return status;
		
	}
	
	/**
	 * 功能: 儲存PolicyMatched Table
	 * policy_id: Policy id
	 * app_id: Application id
	 * result: 分析結果
	 * */
	public void savePolicyMatched(int pId,int aId,String result){
		PolicyMatched pm = new PolicyMatched();
		pm.setPolicyId(pId);
		pm.setAppId(aId);
		pm.setResult(result);
		pmDao.insert(pm);
	}
}
