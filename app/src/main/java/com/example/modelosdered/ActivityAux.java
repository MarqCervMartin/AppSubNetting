package com.example.modelosdered;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

public class ActivityAux extends AppCompatActivity {

    private EditText etip,etnumsredes;
    private Button btnVerSRedes;
    private TextInputLayout inip,insr;
    private boolean validaIP = false, validaNSR = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aux);

        etip = (EditText) findViewById(R.id.ETIP);
        etnumsredes =(EditText) findViewById(R.id.ETNSR);
        inip = (TextInputLayout) findViewById(R.id.ip);
        insr = (TextInputLayout) findViewById(R.id.NSub);
        btnVerSRedes = (Button) findViewById(R.id.btnVSR);
        btnVerSRedes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Patterns.IP_ADDRESS.matcher(etip.getText().toString()).matches() == false){
                    inip.setError("Ip no valida");
                    validaIP = false;
                }else{
                    validaIP = true;
                    inip.setError(null);
                }
            }
        });

    }


}
