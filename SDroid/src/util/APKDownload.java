package util;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.gc.android.market.api.LoginException;
import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.MarketSession.Callback;
import com.gc.android.market.api.model.Market.App;
import com.gc.android.market.api.model.Market.AppsRequest;
import com.gc.android.market.api.model.Market.AppsResponse;
import com.gc.android.market.api.model.Market.ResponseContext;
import com.gc.android.market.api.model.Market.GetAssetResponse.InstallAsset;


public class APKDownload {
	private ConfigFile config = null; 
	
	public APKDownload(){
		config = new ConfigFile();
	}
	
	public void SearchAPKFromGooglePlay(String pname){      
		MarketSession session = new MarketSession(false);
		session.login(config.getPropValue("email"), config.getPropValue("password"), config.getPropValue("deviceId"));
		
        AppsRequest appsRequest = AppsRequest.newBuilder().setStartIndex(0)
                        .setEntriesCount(10).setQuery("pname:"+pname)
                        .setWithExtendedInfo(true).build();

        session.append(appsRequest, new Callback<AppsResponse>() {
                @Override
                public void onResult(ResponseContext context, AppsResponse response) {
                        int count = response.getAppCount();
                        System.out.println("APP's count: " + count);
                        for (int i = 0; i < count; i++) {
                                App app = response.getApp(i); 
                                int pCount = app.getExtendedInfoOrBuilder().getPermissionIdCount();
                                System.out.println("permissions' count: " + pCount);
                                for (int j = 0; j < pCount; j++){
                                	System.out.println(app.getExtendedInfoOrBuilder().getPermissionId(j));
                                }

                        }
                }
        });
        session.flush(); 
	}
	
	public void APKDownloadFromGooglePlay(String assetId){  
		//ex: assetId = 8069246084405590500
		try{
			MarketSession session = new MarketSession(true);
			session.login(config.getPropValue("email"), config.getPropValue("password"), config.getPropValue("deviceId"));
			
			String fileToSave = assetId + ".apk";
			
			System.out.println("Downloading.. " + fileToSave);
            InstallAsset ia = session.queryGetAssetRequest(assetId).getInstallAsset(0);
            String cookieName = ia.getDownloadAuthCookieName();
            String cookieValue = ia.getDownloadAuthCookieValue();	
            
            URL url = new URL(ia.getBlobUrl());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
            conn.setRequestProperty("Cookie", cookieName + "=" + cookieValue);
            if (conn.getResponseCode() == 302) {
                String location = conn.getHeaderField("Location");
                url = new URL(location);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.setRequestProperty("User-Agent", "Android-Market/2 (sapphire PLAT-RC33); gzip");
                conn.setRequestProperty("Cookie", cookieName + "=" + cookieValue);
            }
            
            InputStream inputstream = (InputStream) conn.getInputStream();
            
            BufferedOutputStream stream = new BufferedOutputStream(
            		new FileOutputStream(new File(config.getPropValue("fileToSavePath")+fileToSave)));
            byte[] buf = new byte[16536/*1024*/];
            int k = 0;
            int readed = 0;
            while ((k = inputstream.read(buf)) > 0) {
                stream.write(buf, 0, k);
                readed += k;
            }
            inputstream.close();
            stream.flush();
            stream.close();
            System.gc();
			
            System.out.println("Download finished!");
		}catch(LoginException le){
			le.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
