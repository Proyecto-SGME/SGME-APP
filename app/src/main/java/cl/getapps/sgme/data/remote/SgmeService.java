package cl.getapps.sgme.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import cl.getapps.sgme.data.model.api.Evento;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.util.MyGsonTypeAdapterFactory;

public interface SgmeService {

    String ENDPOINT = "https://api.ribot.io/";
    String ENDPOINT_DESARROLLO = "http://192.168.1.35:50300/";
    String ENDPOINT_CELULAR = "http://192.168.43.159:50300/";

    @GET("ribots")
    Observable<List<Menu>> getRibots();

    @GET("api/EVENTOS")
    Observable<List<Evento>> getEventos();

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static SgmeService newRibotsService() {
            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SgmeService.ENDPOINT_CELULAR)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(SgmeService.class);
        }
    }
}
