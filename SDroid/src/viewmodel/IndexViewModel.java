package viewmodel;

import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Path;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class IndexViewModel{

	//UI component
	@Wire("#conHeader")
	Label title;
	@Wire("#crumbHeader")
	Label subTitle;
	@Wire("#pageContent")
	Include pageContent;
	
	@Init
	public void init(){
	}

	@Command
	public void goToIndexPage(@BindingParam("hStr") String header){
		title.setValue(header);
		subTitle.setValue(header);
		pageContent.setSrc("/template/IndexPage.zul");
		
		
	}
	
	@Command
	public void goToSettingPolicy(@BindingParam("hStr") String header){
		title.setValue(header);
		subTitle.setValue(header);
		pageContent.setSrc("/template/SettingPolicy.zul");
		
	
		//showNotify("test", pageContent);
	
	}
	
	@Command
	public void goToPolicyMatched(@BindingParam("hStr") String header){
		title.setValue(header);
		subTitle.setValue(header);
		pageContent.setSrc("/template/PolicyMatched.zul");
	}
	
	@Command
	public void goToManagingMsg(@BindingParam("hStr") String header){
		title.setValue(header);
		subTitle.setValue(header);
		pageContent.setSrc("/template/MessageMgt.zul");
	}

	/* Getter & Setter */
	
	
	
	private void showNotify(String msg, Component ref) {
	   Clients.showNotification(msg, "info", ref, "end_center", 2000);
	}
	
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view){
	    Selectors.wireComponents(view, this, false);
	}
}
