package com.example.currencychange.ViewModel.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.currencychange.Model.networking.ApiInterface;
import com.example.currencychange.Model.networking.NetworkUtils;
import com.example.currencychange.ViewModel.entity.ConversionRate;
import com.example.currencychange.ViewModel.entity.RateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CurrencyRepository {
    private static final String TAG = CurrencyRepository.class.getName();
   private ApiInterface apiInterface ;
   private NetworkUtils networkUtils ;
    private  static CurrencyRepository instance ;

    private CurrencyRepository(Application application) {
        networkUtils = NetworkUtils.getInstance(application);
        apiInterface = networkUtils.getApiInterface();
    }

    public static CurrencyRepository getInstance(Application application){
        if(instance==null) instance = new CurrencyRepository(application);
        return instance ;
    }

    public LiveData<ConversionRate> convert(String from, String to, double amount){
         MutableLiveData<ConversionRate> res = new MutableLiveData();
        apiInterface.convert(from ,to , amount ).enqueue(new Callback<ConversionRate>() {
             @Override
             public void onResponse(Call<ConversionRate> call, Response<ConversionRate> response) {

                 if (response.isSuccessful()) {
                     Log.e(TAG, "onResponse:"+response.body());
                     res.setValue(response.body());
                 } else {
                     Log.e(TAG, "onResponse:  null data ");
                     res.setValue(null);
                 }
             }

             @Override
             public void onFailure(Call<ConversionRate> call, Throwable t) {
                 Log.e(TAG, "onResponse:  "+t.getMessage());

             }
         });
        return res;
    }

    public LiveData<RateResponse> getRates(String isoFrom) {
        MutableLiveData<RateResponse> res = new MutableLiveData();
        apiInterface.getRates(isoFrom).enqueue(new Callback<RateResponse>() {
            @Override
            public void onResponse(Call<RateResponse> call, Response<RateResponse> response) {

                if (response.isSuccessful()) {
                    Log.e(TAG, "onResponse:"+response.body());
                    res.setValue(response.body());
                } else {
                    Log.e(TAG, "onResponse:  null data ");
                    res.setValue(null);
                }
            }

            @Override
            public void onFailure(Call<RateResponse> call, Throwable t) {
                Log.e(TAG, "onResponse:  "+t.getMessage());

            }
        });
        return res;
    }
}
