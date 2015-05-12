package util;

import model.Application;

public class Test {
	public static void main(String args[]){
		APKDownload ad = new APKDownload();
//		ad.SearchAPKFromGooglePlay("net.slideshare.mobile");
//		ad.APKDownloadFromGooglePlay("8069246084405590500");
		
		//APK Search
		ad.SearchAPKFromGooglePlay("net.slideshare.mobile");
		//APK Download
		Application app = ad.getApplication();
		if(app != null){
		ad.APKDownloadFromGooglePlay(app.getAppLabel(),app.getAssetId());
		}
		
	}
}
