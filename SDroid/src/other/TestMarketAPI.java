package other;


import com.gc.android.market.api.MarketSession;
import com.gc.android.market.api.MarketSession.Callback;
import com.gc.android.market.api.model.Market.App;
import com.gc.android.market.api.model.Market.AppsRequest;
import com.gc.android.market.api.model.Market.AppsResponse;
import com.gc.android.market.api.model.Market.ResponseContext;

public class TestMarketAPI {

	public static void main(String[] args) {
  
        final MarketSession session = new MarketSession(false);
        session.login("ncu.csep@gmail.com", "sec217netlab", "312EAB5F2F3DE962");       
        
        AppsRequest appsRequest = AppsRequest.newBuilder().setStartIndex(0)
                        .setEntriesCount(10).setQuery("pname:net.slideshare.mobile")
                        .setWithExtendedInfo(true).build();

        session.append(appsRequest, new Callback<AppsResponse>() {
                @Override
                public void onResult(ResponseContext context, AppsResponse response) {
                        int count = response.getAppCount();
                        System.out.println("count=" + count);
                        for (int i = 0; i < count; i++) {
                                App app = response.getApp(i);
                                System.out.print(app.toString());
                                System.out.println("--------------------------------------");
                                System.out.println(app.getExtendedInfoOrBuilder().getPermissionIdCount());
                                for (int j = 0; j < app.getExtendedInfoOrBuilder().getPermissionIdCount(); j++){
                                	System.out.println(app.getExtendedInfoOrBuilder().getPermissionId(j));
                                }
                        }
                }
        });
        session.flush();                
}

}
