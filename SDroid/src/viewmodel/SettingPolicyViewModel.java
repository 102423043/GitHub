package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.Permission;
import model.Policy;

import org.zkoss.bind.annotation.Init;

import util.LogInfo;
import dao.PermissionDao;

public class SettingPolicyViewModel {

	private Policy policy;
	private PermissionDao pDao;
	//Install-Time
	private List<String> applications;
	private List<Permission> permissions;
	private List<String> conditions;
	//Run-Time
	private List<String> types;
	private List<String> actions;
	private List<String> components;
	
	
	@Init
	public void init(){
		policy = new Policy();
		pDao = new PermissionDao();
		applications = new ArrayList<String>();
		permissions = new ArrayList<Permission>();
		conditions = new ArrayList<String>();
		types = new ArrayList<String>();
		actions = new ArrayList<String>();
		components = new ArrayList<String>();
		
		loadData();
	}

	public void loadData(){
		//Permission
		permissions = pDao.getAllList();
//		LogInfo.info("permissions.size:", permissions.size());
	}
	
	/**
	 * Getter Object
	 * */
	public Policy getPolicy() {
		return policy;
	}

	public PermissionDao getpDao() {
		return pDao;
	}

	public List<String> getApplications() {
		return applications;
	}

	public List<Permission> getPermissions() {
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
