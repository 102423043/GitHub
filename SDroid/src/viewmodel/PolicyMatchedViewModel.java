package viewmodel;

import java.io.File;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.io.Files;
import org.zkoss.util.media.Media;
import org.zkoss.zul.Messagebox;

import util.AXMLPrinter;

public class PolicyMatchedViewModel {

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
}
