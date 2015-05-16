package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.DataLabel;
import model.Policy;
import model.PolicyMatched;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import util.LogInfo;
import dao.DataLabelDao;
import dao.PolicyDao;

public class SettingDataLabelViewModel {
	
	// Model
	DataLabel dataLabel;
	// Dao
	private DataLabelDao dlDao;
	private PolicyDao pDao;
	// Util
	private LogInfo logInfo;
	// ListView
	private List<DataLabel> dataLabels;
	
	@Init
	public void init(){
		dataLabel = new DataLabel();
		dlDao = new DataLabelDao();
		pDao = new PolicyDao();
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
	@SuppressWarnings("unchecked")
	@Command
	public void removeLabel(@BindingParam("mStr") final String id){
		DataLabel dl = dlDao.getById(id);
		final List<Policy> list = pDao.getListByDataLabel(dl.getLabelId());
		if (list != null) {
			Messagebox.show("此筆Label已有Matched AppPolicy，是否確定刪除?", "Question",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if (Messagebox.ON_OK.equals(e.getName())) {
								for(Policy p :list){
									pDao.removeById(p.getId().toString());
								}
								dlDao.removeById(id);
								Messagebox.show("Label刪除成功");
								getDataLabelList();
							} else if (Messagebox.ON_CANCEL.equals(e.getName())) {
								return;
							}
						}
					});
		} else {
			dlDao.removeById(id);
			Messagebox.show("Label刪除成功");
			getDataLabelList();
		}
	}

	public DataLabel getDataLabel() {
		return dataLabel;
	}

	public List<DataLabel> getDataLabels() {
		return dataLabels;
	}
	
	
}
