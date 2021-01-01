package com.example.currencychange.Model.networking;

import com.example.currencychange.ViewModel.entity.ConversionRate;
import com.example.currencychange.ViewModel.entity.RateResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiInterface {


    @GET("pair/{iso1}/{iso2}/{amount}")
    Call<ConversionRate> convert(@Path("iso1") String from ,
                                 @Path("iso2") String to ,
                                 @Path("amount") double amount
                             );

    //convert?from=USD&to=ILS&amount=12"

    @GET("latest/{isoBase}")
    Call<RateResponse> getRates(@Path("isoBase") String base);
}
