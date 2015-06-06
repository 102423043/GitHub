package viewmodel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import model.AppPolicy;
import model.Application;
import model.Label;
import model.Policy;
import model.RunTimePolicy;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import dao.ApplicationDao;
import dao.LabelDao;
import dao.PolicyDao;
import util.LogInfo;
import util.ParseXML;

public class SettingRunTimePolicyViewModel {
	// Model
	private RunTimePolicy runTimePolicy;
	private AppPolicy appPolicy;
	// Dao
	private ApplicationDao aDao;
	private PolicyDao plDao;
	private LabelDao dlDao;
	// Util
	private ParseXML parseXML;
	private LogInfo logInfo;
	// ListView
	private List<String> applications;
	private List<RunTimePolicy> runTimePolicies;
	private List<AppPolicy> appPolicies;
	private List<String> dataLabels;

	/**
	 * 功能: 初始化設定
	 * */
	@Init
	public void init() {
		runTimePolicy = new RunTimePolicy();
		appPolicy = new AppPolicy();

		aDao = new ApplicationDao();
		plDao = new PolicyDao();
		dlDao = new LabelDao();

		parseXML = new ParseXML();
		logInfo = new LogInfo();

		applications = new ArrayList<String>();
		runTimePolicies = new ListModelList<RunTimePolicy>();
		appPolicies = new ListModelList<AppPolicy>();
		dataLabels = new ArrayList<String>();

		loadData();
	}

	public void loadData() {

		List<Application> appList = aDao.getAllList();
		for (Application app : appList) {
			applications.add(app.getAppLabel());
		}

		List<Label> dataList = dlDao.getAllList();
		for (Label data : dataList) {
			dataLabels.add(data.getLabelId());
		}

		getPolicyList("RunTimePolicy");
		getPolicyList("AppPolicy");

	}

	/**
	 * 功能: 取得目前最新的 Policies 清單 type: RunTimePolicy or AppPolicy
	 * */
	public void getPolicyList(String type) {
		List<Policy> pList = plDao.getAllList(type);
		switch (type) {
		case "RunTimePolicy":
			runTimePolicies.clear();
			if (pList != null) {
				for (Policy p : pList) {
					RunTimePolicy rtp = (RunTimePolicy) parseXML
							.XMLParseToRunTimeOrAppObj(p.getPolicy(), type);
					rtp.setId(p.getId());
					rtp.setCreateTime(new SimpleDateFormat("yyyy/MM/dd")
							.format(p.getCreateTime()));
					runTimePolicies.add(rtp);
				}
			}
			break;
		case "AppPolicy":
			appPolicies.clear();
			if (pList != null) {
				for (Policy p : pList) {
					AppPolicy ap = (AppPolicy) parseXML
							.XMLParseToRunTimeOrAppObj(p.getPolicy(), type);
					ap.setId(p.getId());
					ap.setCreateTime(new SimpleDateFormat("yyyy/MM/dd")
							.format(p.getCreateTime()));
					appPolicies.add(ap);
				}
			}
			break;
		}
	}

	/**
	 * 功能: 新增安全政策 type: RunTimePolicy or AppPolicy
	 * */
	@Command
	public void insertPolicy(@BindingParam("mStr") String type) {
		Policy policy = new Policy();

		switch (type) {

		case "RunTimePolicy":
			String runTimeXML = parseXML.RunTimeOrAppObjParseToXML(
					runTimePolicy, type);
			policy.setPolicy(runTimeXML);
			break;
		case "AppPolicy":
			if (validator(type)) {
				Messagebox.show("資料欄位不可為空");
				return;
			}
			String appXML = parseXML.RunTimeOrAppObjParseToXML(appPolicy, type);
			policy.setPolicy(appXML);
			break;
		}

		policy.setType(type);
		plDao.insert(policy);

		getPolicyList(type);
	}

	/**
	 * 功能: 刪除安全政策
	 * */
	@Command
	public void removePolicy(@BindingParam("mStr") String id) {

		Messagebox.show("Policy刪除成功");
		plDao.removeById(id);

		getPolicyList("RunTimePolicy");
		getPolicyList("AppPolicy");
	}

	/**
	 * 功能: 驗證資料欄位
	 * */
	private boolean validator(String type) {
		boolean check = false;

		switch (type) {
		case "RunTimePolicy":
			break;
		case "AppPolicy":
			if (StringUtils.isBlank(appPolicy.getDataLabel())
					|| StringUtils.isBlank(appPolicy.getApplication())) {
				check = true;
			}
			break;
		}

		return check;
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

	public List<String> getDataLabels() {
		return dataLabels;
	}

}
