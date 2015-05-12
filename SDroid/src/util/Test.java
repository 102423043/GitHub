package util;

import java.io.File;

public class Test {
	public static void main(String args[]){
		APKDownload ad = new APKDownload();
//		ad.SearchAPKFromGooglePlay("net.slideshare.mobile");
		ad.APKDownloadFromGooglePlay("net.slideshare.mobile","155");
		
//		File apkFile = new File("C:\\Users\\lemon\\Desktop\\net.slideshare.mobile.apk");
//		AXMLPrinter printer = new AXMLPrinter();
//		printer.getManifestXMLFromAPK(apkFile);
		
	}
	
	// @Command
	// public void uploadAPKFile(@BindingParam("mStr")Media media){
	// try{
	// File apkFile = new
	// File("C:\\Users\\lemon\\Desktop\\jp.naver.line.android.apk");
	// Files.copy(apkFile, media.getStreamData());
	// AXMLPrinter printer = new AXMLPrinter();
	// List<String> list = printer.getManifestXMLFromAPK(apkFile);
	//
	// Messagebox.show("Upload");
	// }catch(Exception ex){
	// ex.printStackTrace();
	// }
	// }
}
