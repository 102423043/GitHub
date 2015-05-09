package viewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.AppPolicy;
import model.Application;
import model.Policy;
import model.RunTimePolicy;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

import dao.ApplicationDao;
import dao.PolicyDao;
import util.LogInfo;
import util.ParseXML;

public class SettingRunTimePolicyViewModel {
	//Model
	private RunTimePolicy runTimePolicy;
	private AppPolicy appPolicy;
	//Dao
	private ApplicationDao aDao;
	private PolicyDao plDao;
	//Util
	private ParseXML parseXML;
	private LogInfo logInfo;
	//ListView
	private List<String> applications;
	private List<RunTimePolicy> runTimePolicies;
	private List<AppPolicy> appPolicies;
	
	/**
	 * 功能: 初始化設定
	 * */
	@Init
	public void init(){
		runTimePolicy = new RunTimePolicy();
		appPolicy = new AppPolicy();
		
		aDao = new ApplicationDao();
		plDao = new PolicyDao();
		
		parseXML = new ParseXML();
		logInfo = new LogInfo();
		
		applications = new ArrayList<String>();
		runTimePolicies = new ArrayList<RunTimePolicy>();
		appPolicies = new ArrayList<AppPolicy>();
		
		loadData();
	}
	
	public void loadData(){
		
		List<Application> appList = aDao.getAllList();
		for(Application app: appList){
			applications.add(app.getAppLabel());
		}
		
		getPolicyList("RunTimePolicy");
		getPolicyList("AppPolicy");
		
	}
	
	/**
	 * 功能: 取得目前最新的 Policies 清單
	 * type: RunTimePolicy or AppPolicy
	 * */
	public void getPolicyList(String type){
		List<Policy> pList = plDao.getAllList(type);
		if(pList != null){
			for(Policy p: pList){
				logInfo.info("%s", p.getPolicy());
				if(type == "RunTimePolicy"){
					RunTimePolicy rtp = (RunTimePolicy)parseXML.XMLParseToRunTimeOrAppObj(p.getPolicy(),type);
					rtp.setId(p.getId());
					rtp.setCreateTime(new SimpleDateFormat("yyyy/MM/dd").format(p.getCreateTime()));
					runTimePolicies.add(rtp);
				}else if(type == "AppPolicy"){
					AppPolicy ap = (AppPolicy)parseXML.XMLParseToRunTimeOrAppObj(p.getPolicy(),type);
					ap.setId(p.getId());
					ap.setCreateTime(new SimpleDateFormat("yyyy/MM/dd").format(p.getCreateTime()));
					appPolicies.add(ap);
				}
			}
		}
	}
	
	/**
	 * 功能: 新增安全政策
	 * type: RunTimePolicy or AppPolicy
	 * */
	@Command
	public void insertPolicy(@BindingParam("mStr")String type){
		Policy policy= new Policy();
		
		switch(type){
		
		case "RunTimePolicy":
			String runTimeXML = parseXML.RunTimeOrAppObjParseToXML(runTimePolicy,type);
			policy.setPolicy(runTimeXML);
			break;
		case "AppPolicy":
			String appXML = parseXML.RunTimeOrAppObjParseToXML(appPolicy,type);
			policy.setPolicy(appXML);
			break;
		}
		
		policy.setType(type);
		plDao.insert(policy);
		getPolicyList(type);
	}
	
	
	/**
	 * Setter & Getter 物件
	 * */
	public RunTimePolicy getRunTimePolicy() {
		return runTimePolicy;
	}

	public AppPolicy getAppPolicy() {
		return appPolicy;
	}

	public List<String> getApplications() {
		return applications;
	}

	public List<RunTimePolicy> getRunTimePolicies() {
		return runTimePolicies;
	}

	public List<AppPolicy> getAppPolicies() {
		return appPolicies;
	}
	
	
	
}
