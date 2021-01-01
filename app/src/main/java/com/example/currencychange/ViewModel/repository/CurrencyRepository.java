package com.example.currencychange.ViewModel.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.currencychange.Model.ApiInterface;
import com.example.currencychange.Model.NetworkUtils;
import com.example.currencychange.ViewModel.entity.SafetyResult;

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

    public LiveData<SafetyResult> convert(String from, String to, double amount){
         MutableLiveData<SafetyResult> res = new MutableLiveData();
        apiInterface.convert(from ,to , amount ).enqueue(new Callback<SafetyResult>() {
             @Override
             public void onResponse(Call<SafetyResult> call, Response<SafetyResult> response) {

                 if (response.isSuccessful()) {
                     Log.e(TAG, "onResponse:"+response.body().getQuery());
                     res.setValue(response.body());
                 } else {
                     Log.e(TAG, "onResponse:  null data ");
                     res.setValue(null);
                 }
             }

             @Override
             public void onFailure(Call<SafetyResult> call, Throwable t) {
                 Log.e(TAG, "onResponse:  "+t.getMessage());

             }
         });
        return res;
    }
}
