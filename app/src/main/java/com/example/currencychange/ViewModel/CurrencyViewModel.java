package com.example.currencychange.ViewModel;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.currencychange.ViewModel.entity.ConversionRate;
import com.example.currencychange.ViewModel.entity.RateResponse;
import com.example.currencychange.ViewModel.repository.CurrencyRepository;

public class CurrencyViewModel extends AndroidViewModel {
    private static final String TAG = CurrencyViewModel.class.getName();
    CurrencyRepository repository ;
    private static CurrencyViewModel model ;
    private CurrencyViewModel(@NonNull Application application) {
        super(application);
        repository = CurrencyRepository.getInstance(application);
    }

    public static CurrencyViewModel getModel(Application application) {
        if(model==null) model=new CurrencyViewModel(application);
        return model;
    }

    public LiveData<ConversionRate> convert(String from, String to, double amount) {
        Log.e(TAG, "convert From: "+from );
        Log.e(TAG, "convert To: "+to );
        Log.e(TAG, "convert amount: "+amount );
        return repository.convert(from , to , amount);
    }

    public LiveData<RateResponse> getRates(String isoFrom) {
        Log.e(TAG, "convert From: "+isoFrom );
        return repository.getRates(isoFrom);

    }
}
