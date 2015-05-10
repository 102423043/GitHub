package viewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.Application;
import model.Condition;
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
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.LabelElement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

import util.LogInfo;
import util.ParseXML;
import dao.ApplicationDao;
import dao.PermissionDao;
import dao.PolicyDao;

public class SettingPolicyViewModel {

	// Model
	private Policy policy;
	private InstallTimePolicy installPolicy;
	private Condition minVersion;
	private Condition permissionLevel;
	// Dao
	private PermissionDao pDao;
	private ApplicationDao aDao;
	private PolicyDao plDao;
	// Util
	private ParseXML parseXML;
	private LogInfo logInfo;
	// ListView
	private List<String> applications;
	private List<String> permissions;
	private List<String> conditions;
	private List<String> types;
	private List<String> actions;
	private List<String> components;
	private List<InstallTimePolicy> installTimePolicies;

	/**
	 * 功能: 初始化設定
	 * */
	@Init
	public void init() {
		policy = new Policy();
		installPolicy = new InstallTimePolicy();
		minVersion = new Condition();
		permissionLevel = new Condition();

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
		installTimePolicies = new ListModelList<InstallTimePolicy>();

		loadData();
	}
	
	/**
	 * 功能: 當頁面初始化後，載入清單資料
	 * */
	public void loadData() {

		List<Permission> perList = pDao.getAllList();
		for (Permission per : perList) {
			permissions.add(per.getPermission());
		}
		List<Application> appList = aDao.getAllList();
		for (Application app : appList) {
			applications.add(app.getAppLabel());
		}

		getInstallPolicyList();
	}

	/**
	 * 功能: 取得目前最新的 Install-time  Policies 清單
	 * */
	public void getInstallPolicyList() {
		List<Policy> pList = plDao.getAllList("installTime");
		if (pList != null) {
			installTimePolicies.clear();
			for (Policy p : pList) {
				logInfo.info("%s", p.getPolicy());
				InstallTimePolicy itp = (InstallTimePolicy) parseXML
						.XMLParseToInstallObj(p.getPolicy());
				itp.setId(p.getId());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
				itp.setCreateTime(sdf.format(p.getCreateTime()));
				installTimePolicies.add(itp);
			}
		}
	}

	/**
	 * 功能: 新增安全政策
	 * */
	@Command
	public void insertPolicy() {
		Policy policy;

		minVersion.setName("minVersion");
		permissionLevel.setName("permissionLevel");
		installPolicy.getConditions().add(minVersion);
		installPolicy.getConditions().add(permissionLevel);

		String policyXML = parseXML.InstallObjParseToXML(installPolicy);
		policy = new Policy();
		policy.setType("installTime");
		policy.setPolicy(policyXML);

		plDao.insert(policy);
		getInstallPolicyList();

	}

	/**
	 * 功能: 刪除安全政策
	 * */
	@Command
	public void removePolicy(@BindingParam("mStr")String id) {
		
		plDao.removeById(id);
		getInstallPolicyList();
	}
	
	
	/**
	 * Setter & Getter 物件
	 * */
	public Policy getPolicy() {
		return policy;
	}

	public InstallTimePolicy getInstallPolicy() {
		return installPolicy;
	}

	public Condition getMinVersion() {
		return minVersion;
	}

	public Condition getPermissionLevel() {
		return permissionLevel;
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
