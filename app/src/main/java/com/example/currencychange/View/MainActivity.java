package com.example.currencychange.View;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.currencychange.Model.NotificationUtils;
import com.example.currencychange.Model.SharedPrefrencesHelper;
import com.example.currencychange.R;
import com.example.currencychange.ViewModel.CurrencyViewModel;
import com.example.currencychange.ViewModel.entity.RateResponse;
import com.hbb20.CountryCodePicker;
import java.util.Currency;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
  private   EditText fromCurrency , toCurrency ;
  private CountryCodePicker fromSpinner , toSpinner ;
  private   ImageView convert;
  private CurrencyViewModel viewModel ;
  private Observer<RateResponse> rateResponseObserver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new CurrencyViewModel(getApplication());
        //connecting
        fromCurrency = findViewById(R.id.from_am);
        toCurrency = findViewById(R.id.to_am);
        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        convert = findViewById(R.id.convert);
        setSpinners();

       rateResponseObserver=new Observer<RateResponse>() {
           @Override
           public void onChanged(RateResponse rateResponse) {
              if(rateResponse!=null){
                  try {
                      if (rateResponse.conversionRates != null) {
                          String to = toSpinner.getSelectedCountryNameCode();
                          double amount = Double.parseDouble(fromCurrency.getText().toString());
                          String isoTo = Currency.getInstance(new Locale("" , to)).getCurrencyCode();
                          SharedPrefrencesHelper.setTo(getBaseContext(),isoTo);
                          SharedPrefrencesHelper.setToNmCode(getBaseContext(),to);
                          double rate = rateResponse.conversionRates.getClass().getField(isoTo).getDouble( rateResponse.conversionRates);
                          SharedPrefrencesHelper.setRate(getBaseContext(),rate);
                          Log.e(getPackageName(), "onChanged: "+rate);
                          toCurrency.setText((amount * rate) + "");
                      }
                  }catch (IllegalAccessException | NoSuchFieldException e) {
                      e.printStackTrace();
                  }
              }
           }
       };
        convert.setClickable(true);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toCode =  toSpinner.getSelectedCountryNameCode();
                String fCode =  fromSpinner.getSelectedCountryNameCode();
                fromSpinner.setCountryForNameCode(toCode);
                toSpinner.setCountryForNameCode(fCode);
                setConvert();

            }
        });

        CountryCodePicker.OnCountryChangeListener  countryChangeListener = new CountryCodePicker.OnCountryChangeListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onCountrySelected() {
                setConvert();

            }
        } ;

        fromSpinner.setOnCountryChangeListener(countryChangeListener);
        toSpinner.setOnCountryChangeListener(countryChangeListener);

    }// end onCreate

    private void setConvert(){
        String from = fromSpinner.getSelectedCountryNameCode();
        String isoFrom =  Currency.getInstance(new Locale("" , from)).getCurrencyCode();
        SharedPrefrencesHelper.setFrom(getBaseContext(),isoFrom);
        SharedPrefrencesHelper.setFromNmCode(getBaseContext(),from);
        viewModel.getRates(isoFrom).observeForever(rateResponseObserver);
    }

    private void setSpinners(){
        String toCode = SharedPrefrencesHelper.getToNmCode(getBaseContext());
        String fCode =  SharedPrefrencesHelper.getFromNmCode(getBaseContext());
        fromSpinner.setCountryForNameCode(fCode);
        toSpinner.setCountryForNameCode(toCode);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }// end onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==R.id.track_currency){
           NotificationUtils.createMainChannel(getBaseContext());
           NotificationUtils.notifyRate(getBaseContext());
       }
        return super.onOptionsItemSelected(item);
    }// end onOptionsItemSelected



}// end class