package com.example.currencychange.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.currencychange.R;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
  private   EditText fromCurrency , toCurrency ;
  private CountryCodePicker fromSpinner , toSpinner ;
  private   ImageView convert;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //connecting
        fromCurrency = findViewById(R.id.from_am);
        toCurrency = findViewById(R.id.to_am);
        fromSpinner = findViewById(R.id.from_spinner);
        toSpinner = findViewById(R.id.to_spinner);
        convert = findViewById(R.id.convert);
        convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String from = fromSpinner.getSelectedCountryNameCode();
                String to = toSpinner.getSelectedCountryNameCode();
                double amount = Double.parseDouble(fromCurrency.getText().toString());
                double result = convert(from, to , amount);
                toCurrency.setText(result+"");
            }
        });

    }// end onCreate

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = new MenuInflater(this);
        menuInflater.inflate(R.menu.main_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }// end onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }


    public  double  convert(String from , String to , double amount){
        return  1.0 ;
    }
}// end class