package com.example.modelosdered;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Activity3 extends AppCompatActivity {
    TextView v1,v2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_3);

        v1 = (TextView) findViewById(R.id.View1);
        v2 = (TextView) findViewById(R.id.View2);


        Bundle extras = getIntent().getExtras();
        String IdNetwork = extras.getString("IDNETWORK");
        String IpInicial = extras.getString("IPINICIAL");
        String IPFinal = extras.getString("IPFINAL");
        int mascara = extras.getInt("MASCARA");
        int Nosubredes = extras.getInt("SUBREDES");

        v1.setText(IdNetwork);
        v2.setText(Nosubredes);

    }
}
