package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.Application;
import model.InstallTimePolicy;
import model.Permission;
import model.Policy;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Box;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.LabelElement;











import util.LogInfo;
import util.ParseXML;
import dao.ApplicationDao;
import dao.PermissionDao;
import dao.PolicyDao;

public class SettingPolicyViewModel {

	//Model
	private Policy policy;
	private InstallTimePolicy installPolicy;
	//Dao
	private PermissionDao pDao;
	private ApplicationDao aDao;
	private PolicyDao plDao;
	//Util
	private ParseXML parseXML;
	private LogInfo logInfo;
	//ListView
	private List<String> applications;
	private List<String> permissions;
	private List<String> conditions;
	private List<String> types;
	private List<String> actions;
	private List<String> components;
	private List<InstallTimePolicy> installTimePolicies;
	
	@Init
	public void init(){
		policy = new Policy();
		installPolicy = new InstallTimePolicy();
		
		pDao = new PermissionDao();
		aDao = new ApplicationDao();
		plDao = new PolicyDao();
		
		parseXML = new ParseXML();
		logInfo = new LogInfo();
		
		applications = new ArrayList<String>();
		permissions = new ArrayList<String>();
		conditions = new ArrayList<String>();
		types = new ArrayList<String>();
		actions = new ArrayList<String>();
		components = new ArrayList<String>();
		installTimePolicies = new ArrayList<InstallTimePolicy>();
		
		loadData();
	}

	public void loadData(){

		List<Permission> perList = pDao.getAllList();
		for(Permission per: perList){
			permissions.add(per.getPermission());
		}
		List<Application> appList = aDao.getAllList();
		for(Application app: appList){
			applications.add(app.getAppLabel());
		}

		getInstallPolicyList();
	}
	
	public void getInstallPolicyList(){
		List<Policy> pList = plDao.getAllList();
		for(Policy p: pList){
			InstallTimePolicy itp = (InstallTimePolicy)parseXML.XMLParseToObj(p.getPolicy());
			itp.setId(p.getId());
			itp.setCreateTime(p.getCreateTime());
			installTimePolicies.add(itp);
		}
		
	}
	
	@Command
	public void insertPolicy(@BindingParam("mStr")String type){
		Policy policy;
		
		switch(type){
		case "installTime":
			String policyXML = parseXML.ObjParseToXML("installTime", installPolicy);
//			logInfo.info("%s",policyXML);
			policy = new Policy();
			policy.setType(type);
			policy.setPolicy(policyXML);
			
			plDao.insert(policy);
			
			break;
		case "runTime":
			break;
		}
		
	}
	

	
	/**
	 * Setter & Getter Object
	 * */
	public Policy getPolicy() {
		return policy;
	}

	public InstallTimePolicy getInstallPolicy() {
		return installPolicy;
	}

	public List<InstallTimePolicy> getInstallTimePolicies() {
		return installTimePolicies;
	}

	public List<String> getApplications() {
		return applications;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public List<String> getConditions() {
		return conditions;
	}

	public List<String> getTypes() {
		return types;
	}

	public List<String> getActions() {
		return actions;
	}

	public List<String> getComponents() {
		return components;
	}
	
	
}
