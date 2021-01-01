package com.example.currencychange.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.Observer;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.currencychange.R;
import com.example.currencychange.ViewModel.CurrencyViewModel;
import com.example.currencychange.ViewModel.entity.SafetyResult;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
  private   EditText fromCurrency , toCurrency ;
  private CountryCodePicker fromSpinner , toSpinner ;
  private   ImageView convert;
  private CurrencyViewModel viewModel ;
  private double result = 0.0 ;
  private Observer<SafetyResult> convertResult ;

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
        convertResult = new Observer<SafetyResult>() {
            @Override
            public void onChanged(SafetyResult safetyResult) {
                if(safetyResult!=null){
                    result= safetyResult.getResult();
                    toCurrency.setText(result+"");
                }

            }
        };
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CountryCodePicker temp = fromSpinner ;
                fromSpinner = toSpinner ;
              toSpinner=temp;

            }
        });

        CountryCodePicker.OnCountryChangeListener  countryChangeListener = new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                String from = fromSpinner.getSelectedCountryNameCode();
                String to = toSpinner.getSelectedCountryNameCode();
                double amount = Double.parseDouble(fromCurrency.getText().toString());
                viewModel.convert(from, to , amount).observeForever(convertResult);//observe(convertResult);
            }
        } ;
        fromSpinner.setOnCountryChangeListener(countryChangeListener);
        toSpinner.setOnCountryChangeListener(countryChangeListener);

    }// end onCreate

    private void convert() {


    }//end convert

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }// end onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if(item.getItemId()==R.id.track_currency)
           //todo: track
           ;
        return super.onOptionsItemSelected(item);
    }// end onOptionsItemSelected



}// end class