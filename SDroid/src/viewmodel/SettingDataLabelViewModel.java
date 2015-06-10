package viewmodel;

import java.util.ArrayList;
import java.util.List;

import model.DataLabel;
import model.Label;
import model.Policy;

import org.apache.commons.lang3.StringUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import util.LogInfo;
import dao.DataLabelDao;
import dao.LabelDao;
import dao.PolicyDao;

public class SettingDataLabelViewModel {

	// Model
	Label label;
	DataLabel dataLabel;
	// Dao
	private LabelDao dlDao;
	private PolicyDao pDao;
	private DataLabelDao datalblDao;
	// Util
	private LogInfo logInfo;
	// ListView
	private List<Label> labels;
	private List<DataLabel> datalabels;
	private List<String> labelStrList;
	//UI
	@Wire("#fileName")
	private org.zkoss.zul.Label fileName;

	@Init
	public void init() {
		
		label = new Label();
		dataLabel= new DataLabel();
		dlDao = new LabelDao();
		pDao = new PolicyDao();
		datalblDao = new DataLabelDao();
		
		logInfo = new LogInfo();
		labelStrList = new ArrayList<String>();
		labels = new ListModelList<Label>();
		datalabels = new ListModelList<DataLabel>();

		loadData();
	}

	public void loadData() {
	
		List<Label> dataList = dlDao.getAllList();
		for (Label data : dataList) {
			labelStrList.add(data.getLabel());
		}
		
		getDataLabelList();
	}
	
	/**
	 * 功能: 當頁面初始化後，載入清單資料
	 * */
	public void getDataLabelList() {
		//資料標籤
		datalabels.clear();
		List<DataLabel> dataList = datalblDao.getAllList();
		if (dataList != null) {
			for (DataLabel label : dataList) {
				datalabels.add(label);
			}
		}
		//標籤清單
		labels.clear();
		List<Label> dList = dlDao.getAllList();
		if (dList != null) {
			for (Label label : dList) {
				labels.add(label);
			}
		}
	}

	/**
	 * 功能: 新增資料標籤
	 * */
	@Command
	public void insertDataLabel() {
		if(!StringUtils.isBlank(fileName.getValue()) && !StringUtils.isBlank(dataLabel.getLabel())){
			dataLabel.setFileName(fileName.getValue());
			datalblDao.insert(dataLabel);
			Messagebox.show("新增成功");
			getDataLabelList();
		}else {
			Messagebox.show("資料標籤，不可為空。");
		}
		
	}
	/**
	 * 功能: 刪除資料標籤
	 * */
	@Command
	public void removeDataLabel(@BindingParam("mStr") final String id) {
		datalblDao.removeById(id);
		Messagebox.show("DataLabel刪除成功");
		getDataLabelList();
	}
	
	
	/**
	 * 功能: 新增標籤
	 * */
	@Command
	public void insertLabel(@BindingParam("mStr") String label) {
		if (!StringUtils.isBlank(label)) {
			dlDao.insert(label);
			Messagebox.show("新增成功");
			getDataLabelList();
		} else {
			Messagebox.show("標籤制定，不可為空。");
		}
	}

	/**
	 * 功能: 刪除標籤
	 * */
	@SuppressWarnings("unchecked")
	@Command
	public void removeLabel(@BindingParam("mStr") final String id) {
		Label dl = dlDao.getById(id);
		final List<Policy> list = pDao.getListByDataLabel(dl.getLabelId());
		if (list != null) {
			Messagebox.show("此筆Label已有Matched AppPolicy，是否確定刪除?", "Question",
					Messagebox.OK | Messagebox.CANCEL, Messagebox.QUESTION,
					new EventListener() {
						@Override
						public void onEvent(Event e) throws Exception {
							if (Messagebox.ON_OK.equals(e.getName())) {
								for (Policy p : list) {
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

	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
	    Selectors.wireComponents(view, this, false);
	}
	
	public Label getLabel() {
		return label;
	}

	public List<Label> getLabels() {
		return labels;
	}

	public DataLabel getDataLabel() {
		return dataLabel;
	}

	public List<DataLabel> getDatalabels() {
		return datalabels;
	}

	public List<String> getLabelStrList() {
		return labelStrList;
	}

}
