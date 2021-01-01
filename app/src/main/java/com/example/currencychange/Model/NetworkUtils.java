package com.example.currencychange.Model;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getName();
    private static final String BASE_URL ="https://fixer-fixer-currency-v1.p.rapidapi.com/";
    public static final String API_KEY = "3d428328a3mshbe680ff208e3b56p1b6f20jsn9d0f38c4c07b";
    public static boolean myConnection;
    private static NetworkUtils sInstance;
    private ApiInterface apiInterface;


    private NetworkUtils(Context context) {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.level(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(6000, TimeUnit.SECONDS)
                .connectTimeout(6000, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        apiInterface = retrofit.create(ApiInterface.class);

    }

    public static NetworkUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new NetworkUtils(context);
        }
        return sInstance;
    }

    public static void isNetworkConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        connectivityManager.registerNetworkCallback(
                builder.build(),
                new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(Network network) {
                        myConnection = true;
                    }

                    @Override
                    public void onLost(Network network) {
                        myConnection = false;
                    }
                }

        );

    }

    public ApiInterface getApiInterface() {
        return apiInterface;
    }


}
