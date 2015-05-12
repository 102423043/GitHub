package util;

import model.Application;
import dao.ApplicationDao;
import dao.PolicyDao;

/**
 * 政策比對機制
 * */
public class PolicyMatched {

	// Dao
	private PolicyDao plDao;
	private ApplicationDao aDao;
	//Util
	private APKDownload apkDownload;
	
	/**
	 * 功能: 初始化設定
	 * */
	public PolicyMatched(){
		
		plDao = new PolicyDao(); 
		aDao = new ApplicationDao();
		
		apkDownload = new APKDownload();
	}
	
	/**
	 * 功能: 政策比對分析
	 * pkName：Android package name
	 * */
	public void analysisPolicyMatched(String pkName){
		
		
		Application app = aDao.findByPkName(pkName);
		
		if(app == null){
			//APK Search
			apkDownload.SearchAPKFromGooglePlay(pkName);
			//APK Download
			app = apkDownload.getApplication();
			apkDownload.APKDownloadFromGooglePlay(app.getAppLabel(),app.getAssetId());
		}
		
	}
}
