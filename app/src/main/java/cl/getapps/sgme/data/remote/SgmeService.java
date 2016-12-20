package cl.getapps.sgme.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.util.MyGsonTypeAdapterFactory;

public interface SgmeService {

    String ENDPOINT = "https://api.ribot.io/";

    @GET("ribots")
    Observable<List<Menu>> getRibots();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static SgmeService newRibotsService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SgmeService.ENDPOINT)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(SgmeService.class);
        }
    }
}
