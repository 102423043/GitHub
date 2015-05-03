package other;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.HttpURLConnection;

//use URL to download
public class TestURL {
	public static void main(String[] args) {
		// current assetId for the yahoo search apk
		String assetId = "8069246084405590500";
		// input your userId
		String userId = "110610369039659616932";
		// spoof your deviceId (ANDROID_ID) here
		String deviceId = "312EAB5F2F3DE962";
 
		// input your authToken here
		String authToken = "DQAAAP0AAADsUZVU8qHMAD3wJmXMFSVc6nxLxCwJbhetwTaz5ZR8nmeBHPzzZTQ7L472nfE596rUMRzGxPFCIc5CPpyMm_KcP0JBMRH9qyeVZRRPQwMxJoXBMfqb2y3J8xLieQNzWZ1ra-Jy0i5gj-trHGXC-87NzTbxbeb7lsvUSH08i5HizpcTHtgDISF_Y8dOXwRNoNbQmVwLoJjgHOlUrvdeuKqQIsfP18IrFAqgMDz6LX0MYsoe__81YUGK4jDpkZ9TcAVJDYmTqBU2uIvg-yo_a9vVhWTtyIVNkJLcEC7xAuQ6gkP6DSjxzLCu4Ar4hhuecYHRT9fI4eQI7lp-CiWvKTkT";
 
		String cookie = "ANDROID=" + authToken;
 
		try {
			// prepare data for being 'get'ed
			String rdata = "?" + URLEncoder.encode("assetId", "UTF-8") + "=" + URLEncoder.encode(assetId, "UTF-8");
			rdata += "&" + URLEncoder.encode("userId", "UTF-8") + "=" + URLEncoder.encode(userId, "UTF-8");
			rdata += "&" + URLEncoder.encode("deviceId", "UTF-8") + "=" + URLEncoder.encode(deviceId, "UTF-8");
 
			// Send data
			URL url = new URL("http://android.clients.google.com/market/download/Download" +rdata);
			HttpURLConnection conn = (HttpURLConnection)url.openConnection();
 
			// For GET only
			conn.setRequestMethod("GET");
 
			// Spoof values
			conn.setRequestProperty("User-agent", "AndroidDownloadManager");
			conn.setRequestProperty("Cookie", cookie);
 
			// Read response and save file...
			InputStream inputstream =  conn.getInputStream();
			BufferedOutputStream buffer = new BufferedOutputStream(new FileOutputStream(new File("C:\\Users\\lemon\\Desktop\\ttt.apk")));
			byte byt[] = new byte[1024];
			int i;
			for(long l = 0L; (i = inputstream.read(byt)) != -1; l += i )
				buffer.write(byt, 0, i);
 
			inputstream.close();
			buffer.close();
 
			System.out.println("File saved...");
		}
		catch (FileNotFoundException e) {
			System.err.println("Bad url address!");
			e.printStackTrace();
		}
		catch (UnsupportedEncodingException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		catch (MalformedURLException e) {
			System.out.println(e);
			e.printStackTrace();
		}
		catch (IOException e) {
			if(e.toString().contains("HTTP response code: 403"))
				System.err.println("Forbidden response received!");
			System.out.println(e);
			e.printStackTrace();
		}
	}
}
