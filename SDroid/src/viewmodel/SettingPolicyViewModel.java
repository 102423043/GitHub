package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.Application;
import model.Permission;
import model.Policy;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.LabelElement;




import dao.ApplicationDao;
import dao.PermissionDao;

public class SettingPolicyViewModel {

	private Policy policy;
	private PermissionDao pDao;
	private ApplicationDao aDao;
	//Install-Time
	private List<Application> applications;
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
		aDao = new ApplicationDao();
		
		applications = new ArrayList<Application>();
		permissions = new ArrayList<Permission>();
		conditions = new ArrayList<String>();
		types = new ArrayList<String>();
		actions = new ArrayList<String>();
		components = new ArrayList<String>();
		
		loadData();
	}

	public void loadData(){

		permissions = pDao.getAllList();
		applications = aDao.getAllList();

	}
	
	@Command
	public void insertPolicy(String type){
		switch(type){
		case "installTime":
			
			break;
		case "runTime":
			break;
		}
		
	}
	

	
	/**
	 * Getter Object
	 * */
	public Policy getPolicy() {
		return policy;
	}

	public List<Application> getApplications() {
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
