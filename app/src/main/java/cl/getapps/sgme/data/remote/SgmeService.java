package cl.getapps.sgme.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import cl.getapps.sgme.BuildConfig;
import cl.getapps.sgme.data.model.api.DetalleEvento;
import cl.getapps.sgme.data.model.api.Evento;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import cl.getapps.sgme.data.model.Menu;
import cl.getapps.sgme.util.MyGsonTypeAdapterFactory;

public interface SgmeService {

    String ENDPOINT = "https://api.ribot.io/";
    String ENDPOINT_DESARROLLO = "http://192.168.1.84/";
    String ENDPOINT_CELULAR = "http://192.168.43.159:50300/";

    @GET("ribots")
    Observable<List<Menu>> getRibots();

    @GET("api/EVENTOS")
    Observable<List<Evento>> getEventos();

    @GET("api/EVENTOS/{ID}")
    Observable<List<DetalleEvento>> getDetalleEvento(@Path("ID") int id);

    /******** Helper class that sets up a new services *******/
    class Creator {

        public static SgmeService newRibotsService() {

            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);

            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build();

            Gson gson = new GsonBuilder()
                    .registerTypeAdapterFactory(MyGsonTypeAdapterFactory.create())
                    .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
                    .create();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(SgmeService.ENDPOINT_DESARROLLO)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
            return retrofit.create(SgmeService.class);
        }
    }
}
