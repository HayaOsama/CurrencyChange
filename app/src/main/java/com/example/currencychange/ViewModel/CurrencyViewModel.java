package com.example.currencychange.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.currencychange.Model.ApiInterface;
import com.example.currencychange.Model.NetworkUtils;
import com.example.currencychange.ViewModel.entity.SafetyResult;
import com.example.currencychange.ViewModel.repository.CurrencyRepository;

public class CurrencyViewModel extends AndroidViewModel {
 CurrencyRepository repository ;
    public CurrencyViewModel(@NonNull Application application) {
        super(application);
        repository = CurrencyRepository.getInstance(application);
    }


    public LiveData<SafetyResult> convert(String from, String to, double amount) {
        return repository.convert(from , to , amount);
    }
}
