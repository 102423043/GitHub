package viewmodel;

import java.io.File;

import model.Policy;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Messagebox;

import dao.PolicyDao;
import util.AXMLPrinter;
import util.LogInfo;
import util.ParseXML;

public class PolicyMatchedViewModel {

	// Model
	private Policy policy;	
	// Dao
	private PolicyDao plDao;
	// Util
	private ParseXML parseXML;
	private LogInfo logInfo;
	// ListView
	
	/**
	 * 功能: 初始化設定
	 * */
	@Init
	public void init() {
		policy = new Policy();
		
		plDao = new PolicyDao();
		
		parseXML = new ParseXML();
		logInfo = new LogInfo();
	}
	
	/**
	 * 功能: 當頁面初始化後，載入清單資料
	 * */
	public void loadData() {
		
	}
	
	/**
	 * 功能: 
	 * 上傳APK檔案，並取得AndroidManifest.xml
	 * */
	@Command
	public void uploadAPKFile(@BindingParam("mStr")Media media){
		try{
			File apkFile = new File("C:\\Users\\lemon\\Desktop\\jp.naver.line.android.apk");
			Files.copy(apkFile, media.getStreamData());
			AXMLPrinter printer = new AXMLPrinter();
			printer.getManifestXMLFromAPK();
			
			Messagebox.show("Upload");
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	/**
	 * Setter & Getter 物件
	 * */
	
}
