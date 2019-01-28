package net.castman.currencyexchanger;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText edAmount;
    private Button btnHelp;
    private TextView res_usd;
    private TextView res_euro;
    private double ntdToUsd = 0.032;
    private double ntdToEuro = 0.028;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {
        edAmount = findViewById(R.id.ed_amount);
        res_usd = findViewById(R.id.result_usd);
        res_euro = findViewById(R.id.result_euro);
        btnHelp = findViewById(R.id.btn_help);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.txt_help)
                        .setMessage(getString(R.string.txt_currency_rate) + "\nNTD to USD:" + ntdToUsd + "\nNTD to Euro: " + ntdToEuro)
                        .setPositiveButton(R.string.txt_ok,null)
                        .show();
            }
        });
    }

    public void calc(View view){
        String amount_s = edAmount.getText().toString();
        if ("".equals(amount_s)) {
            new AlertDialog.Builder(this)
                    .setTitle(R.string.txt_empty_ntd)
                    .setMessage(R.string.txt_input_amount)
                    .setPositiveButton(R.string.txt_ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            edAmount.setText("");
                        }
                    })
                    .show();
        }
        else {
            double amount = Double.parseDouble(amount_s);
            res_usd.setText("" + amount*ntdToUsd);
            res_euro.setText("" + amount*ntdToEuro);
        }
    }
}
