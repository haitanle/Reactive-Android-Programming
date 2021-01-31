package packt.reactivestocks.yahoo;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitYahooServiceFactory {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    //todo ch3 - clean up create Interceptor
    OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(chain -> {
        Request request = chain.request();
        Request newRequest;

        newRequest = request.newBuilder()
                .addHeader("x-rapidapi-key", "3bac2e984fmsh0518a968dfdce3dp18d5a0jsn5a49cac2849a")
                .addHeader("x-rapidapi-host", "apidojo-yahoo-finance-v1.p.rapidapi.com")
                .build();
        return chain.proceed(newRequest);
    })
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://apidojo-yahoo-finance-v1.p.rapidapi.com/")
            .build();

    public YahooService create() {
        return retrofit.create(YahooService.class);

    }
}
