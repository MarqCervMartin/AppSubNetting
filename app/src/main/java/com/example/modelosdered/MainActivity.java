package com.example.modelosdered;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button uno,dos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        uno =(Button)findViewById(R.id.Btn1);
        uno.setOnClickListener(new View.OnClickListener() { //Evento del botón
            @Override
            public void onClick(View v) {//Método para cuando de click
                Intent inteuno = new Intent(MainActivity.this, Main2ActivityIP.class);//Manda a la actividad Serie
                startActivity(inteuno);//Inicia la actividad
            }

        });
        dos = (Button) findViewById(R.id.Btn2);
        dos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intedos = new Intent(MainActivity.this, ActivityAux.class);//Manda a la actividad Serie
                startActivity(intedos);//Inicia la actividad
            }
        });
    }
}
