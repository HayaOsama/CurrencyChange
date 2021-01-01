package com.example.currencychange.Model;

import androidx.lifecycle.LiveData;

import com.example.currencychange.ViewModel.entity.SafetyResult;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiInterface {

    @Headers({"X-Rapidapi-Key:3d428328a3mshbe680ff208e3b56p1b6f20jsn9d0f38c4c07b" ,
       "X-Rapidapi-Host:fixer-fixer-currency-v1.p.rapidapi.com"
        })
    @GET("convert")
    Call<SafetyResult> convert(@Query("from") String from ,
                               @Query("to") String to ,
                               @Query("amount") double amount
                             );

    //convert?from=USD&to=ILS&amount=12"
}
