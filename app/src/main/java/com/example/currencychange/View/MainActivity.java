package com.example.currencychange.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import com.example.currencychange.Model.SharedPrefrencesHelper;
import com.example.currencychange.R;
import com.example.currencychange.ViewModel.CurrencyViewModel;
import com.example.currencychange.ViewModel.Services.TrackReceiver;
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
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel =  CurrencyViewModel.getModel(getApplication());
        //connecting
        fromCurrency = findViewById(R.id.from_am);
        toCurrency = findViewById(R.id.to_am);
        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        convert = findViewById(R.id.convert);
        setSpinners();

       rateResponseObserver= rateResponse -> {
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
                      toCurrency.setText(String.valueOf(amount * rate));
                  }
              }catch (IllegalAccessException | NoSuchFieldException e) {
                  e.printStackTrace();
              }
          }
       };
        convert.setClickable(true);
        convert.setOnClickListener((View view) -> {
            String toCode =  toSpinner.getSelectedCountryNameCode();
            String fCode =  fromSpinner.getSelectedCountryNameCode();
            fromSpinner.setCountryForNameCode(toCode);
            toSpinner.setCountryForNameCode(fCode);
            setConvert();

        });

        CountryCodePicker.OnCountryChangeListener  countryChangeListener = this::setConvert;

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
        boolean isTrack = SharedPrefrencesHelper.getTrack(getBaseContext());
       if(item.getItemId()==R.id.track_currency){
           if(!isTrack){
               SharedPrefrencesHelper.setTrack(getBaseContext(),true);
               Intent intent = new Intent(this, TrackReceiver.class);
               pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
               alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
               alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME,
                       SystemClock.elapsedRealtime()+(60*1000) ,  60*1000, pendingIntent);
           } else{
               SharedPrefrencesHelper.setTrack(getBaseContext(),false);
           }

       }

        return super.onOptionsItemSelected(item);
    }// end onOptionsItemSelected



}// end class