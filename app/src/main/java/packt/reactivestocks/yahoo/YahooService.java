package packt.reactivestocks.yahoo;

import io.reactivex.Single;
import packt.reactivestocks.yahoo.json.YahooStockResult;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface YahooService {

    @GET("auto-complete")
    Single<YahooStockResult> yqlQuery(
            @Query("q") String query,
            @Query("region") String env
    );
}
