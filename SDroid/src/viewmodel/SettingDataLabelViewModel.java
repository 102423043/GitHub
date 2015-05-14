package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.DataLabel;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import util.LogInfo;
import dao.DataLabelDao;

public class SettingDataLabelViewModel {
	
	// Model
	DataLabel dataLabel;
	// Dao
	private DataLabelDao dlDao;
	// Util
	private LogInfo logInfo;
	// ListView
	private List<DataLabel> dataLabels;
	
	@Init
	public void init(){
		dataLabel = new DataLabel();
		dlDao = new DataLabelDao();
		logInfo = new LogInfo();
		
		dataLabels = new ListModelList<DataLabel>();
		
		getDataLabelList();
	}
	
	/**
	 * 功能: 當頁面初始化後，載入清單資料
	 * */
	public void getDataLabelList() {
		dataLabels.clear();
		List<DataLabel> dList = dlDao.getAllList();
		if(dList != null){
		for(DataLabel label: dList){
			dataLabels.add(label);
		}
		}
	}
	
	/**
	 * 功能: 新增資料標籤
	 * */
	@Command
	public void insertLabel(@BindingParam("mStr") String label) {
		if(label != ""){
			dlDao.insert(label);
			Messagebox.show("新增成功");
			getDataLabelList();
		}else{
			Messagebox.show("Run-Time 資料標籤，不可為空。");
		}
	}
	
	/**
	 * 功能: 刪除資料標籤
	 * */
	@Command
	public void removeLabel(@BindingParam("mStr") String id){
		if(dlDao.removeById(id)){
			Messagebox.show("刪除成功");
			getDataLabelList();
		}else{
			Messagebox.show("刪除失敗");
		}
	}

	public DataLabel getDataLabel() {
		return dataLabel;
	}

	public List<DataLabel> getDataLabels() {
		return dataLabels;
	}
	
	
}
