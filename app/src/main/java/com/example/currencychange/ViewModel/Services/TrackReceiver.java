package com.example.currencychange.ViewModel.Services;

import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.Context;

import com.example.currencychange.Model.NetworkUtils;
import com.example.currencychange.Model.NotificationUtils;
import com.example.currencychange.Model.SharedPrefrencesHelper;
import com.example.currencychange.ViewModel.CurrencyViewModel;


public  class TrackReceiver extends BroadcastReceiver {
    CurrencyViewModel currencyViewModel ;

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationUtils.createMainChannel(context);
        currencyViewModel= CurrencyViewModel.getModel(null);
        String from = SharedPrefrencesHelper.getFrom(context);
        String to = SharedPrefrencesHelper.getTo(context);
        NetworkUtils.isNetworkConnected(context);
        currencyViewModel.getRates(from).observeForever(rateResponse -> {
            if(rateResponse!=null){
                try {
                    if (rateResponse.conversionRates != null) {
                        boolean status = SharedPrefrencesHelper.getStatus(context);
                        double rate = rateResponse.conversionRates.getClass().getField(to).getDouble( rateResponse.conversionRates);
                        SharedPrefrencesHelper.setRate(context,rate);
                        String desc = "Rate from " + from +" to "+to +" is " ;
                        if(status)desc+= "raising up ";
                        else desc+="falling down ";
                        desc+= "to "+rate ;

                        NotificationUtils.notifyRate(context,desc,status);

                    }
                }catch (IllegalAccessException | NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }
        });

    }
}