package viewmodel;


import model.Application;
import model.Policy;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import dao.ApplicationDao;
import dao.PolicyDao;
import util.APKDownload;
import util.LogInfo;
import util.PolicyMatchedUtil;

public class PolicyMatchedViewModel {

	//UI
	@Wire("#txtPkName")
	private Textbox txtPkName;
	// Model
	private Policy policy;
	// Dao
	private PolicyDao plDao;
	private ApplicationDao aDao;
	// Util
	private LogInfo logInfo;
	private APKDownload apkDownload;
	private PolicyMatchedUtil policyMatched;
	// ListView

	/**
	 * 功能: 初始化設定
	 * */
	@Init
	public void init() {
		policy = new Policy();

		plDao = new PolicyDao();
		aDao = new ApplicationDao();

		logInfo = new LogInfo();
		apkDownload = new APKDownload();
		policyMatched = new PolicyMatchedUtil();
	}

	/**
	 * 功能: 當頁面初始化後，載入清單資料
	 * */
	public void loadData() {

	}

	/**
	 * 功能: 搜尋App資訊，並將儲存至資料庫
	 * pkName: Package name
	 * */
	@Command
	public void searchAPKInfo(@BindingParam("mStr") String pkName) {
		if (pkName.trim().isEmpty()) {
			Messagebox.show("此欄位不可為空");
			return;
		}
		boolean result = searchApkInfo(pkName);
		if(result){
			Messagebox.show("成功新增 "+pkName+" 檔案資訊");
		}else{
			Messagebox.show("已存在 "+pkName+" 檔案資訊");
		}
	}

	public boolean searchApkInfo(String pkName){
		boolean result = false;
		Application app = aDao.findByPkName(pkName);
		if(app == null){
			apkDownload.SearchAPKFromGooglePlay(pkName);
			result = true;
		}
		return result;
	}

	private void showNotify(String msg, Component ref) {
		Clients.showNotification(msg,"error", ref,"middle_center",10);
	}

	/**
	 * Setter & Getter 物件
	 * */

}
