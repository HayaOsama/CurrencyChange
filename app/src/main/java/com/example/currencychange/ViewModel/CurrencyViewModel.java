package com.example.currencychange.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.currencychange.Model.ApiInterface;
import com.example.currencychange.Model.NetworkUtils;

public class CurrencyViewModel extends AndroidViewModel {
  ApiInterface apiInterface ;
  NetworkUtils networkUtils ;
    public CurrencyViewModel(@NonNull Application application) {
        super(application);
        networkUtils = NetworkUtils.getInstance(application);
        apiInterface = networkUtils.getApiInterface();
    }


    public double convert(String from, String to, double amount) {
        return 0.0 ;
    }
}
