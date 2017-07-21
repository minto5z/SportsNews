package com.mikhail.sportsnewshistoryrecords.api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import com.google.gson.Gson;
import com.mikhail.sportsnewshistoryrecords.api.keys.NytKeys;
import com.mikhail.sportsnewshistoryrecords.model.newswire.NytSportsResults;

/**
 * API call NYT News Wire
 */
public class NytAPI {

    public static final String NYT_API_URL = "http://api.nytimes.com/svc/news/v3/content/";
    public static Gson gson = new Gson();

    /**
     * RX JAva interface to make NYT News Wire api call
     */
    public interface NytRx {
        @GET("all/sports/{subsection}/1.json?&api-key=" + NytKeys.newsWireKey)
        Observable<NytSportsResults> nytSportsResults(
                @Query("subsection") String subsection);
    }

    /**
     * Retrofit interface to make NYT News Wire api call
     */
    public interface NytAPIRetrofit {
        @GET("{source}/{section}/{subsection}/1.json?&api-key=" + NytKeys.newsWireKey)
        Call<NytSportsResults> response(
                @Path("source") String source,
                @Path("section") String section,
                @Query("subsection") String subsection);
    }

    /**
     * Simple REST adapter which points to NYT News Wire api
     */
    public static NytAPIRetrofit create() {
        return new Retrofit.Builder()
                .baseUrl(NYT_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NytAPIRetrofit.class);
    }

    /**
     * RX JAva adapter which points to NYT News Wire api
     */
    public static NytRx createRx() {
        return new Retrofit.Builder()
                .baseUrl(NYT_API_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NytRx.class);
    }

}
