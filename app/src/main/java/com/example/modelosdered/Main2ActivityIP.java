package com.example.modelosdered;

import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Main2ActivityIP extends AppCompatActivity implements View.OnClickListener {

    private EditText etip;
    //private Button btnVerSRedes;
    private TextInputLayout inip;
    private boolean validaIP = false;



    EditText div;
    TextView ver,mr1,di1,df1;
    TextView cuatro,cinco,seis,siete,ocho;
    Button b1;
    //variables del Mares
    // caja subredes y boton para mostarlas
    EditText e;
    Button b2;
    //Variables
    private String idNetwork = "",direccionInicial = "", direccionFinal="";
    private int mascara = 0;
    //set y gets para poder mandarlos al intent
    public void setIdNetwork(String a){
        idNetwork = a;
    }
    public String getIdNetwork(){
        return idNetwork;
    }
    public void setDireccionInicial(String a){
        direccionInicial = a;
    }
    public String getDireccionInicial(){
        return direccionInicial;
    }
    public void setDireccionFinal(String a){
        direccionFinal = a;
    }
    public String getDireccionFinal(){
        return direccionFinal;
    }
    public void setMascara(int a){
        mascara = a;
    }
    public int getMascara(){
        return mascara;
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_ip);

        //nueva caja texto ip
        etip = (EditText) findViewById(R.id.ETIP);
        inip = (TextInputLayout) findViewById(R.id.ip);

/*      MOdificando por una sola editText 28/May/19
        a = (EditText) findViewById(R.id.o1);
        b = (EditText) findViewById(R.id.o2);
        c = (EditText)findViewById(R.id.o3);
        d = (EditText) findViewById(R.id.o4);*/
        div = (EditText) findViewById(R.id.sub);

        //nueva caja de cantidad de subredes limitada a 2 digitos maximo 99
        e = (EditText) findViewById(R.id.ETSR);

        b1 = (Button) findViewById(R.id.b1);
        b2 = (Button) findViewById(R.id.b2);
        ver = (TextView) findViewById(R.id.val);
        mr1 = (TextView) findViewById(R.id.mr);
        di1 = (TextView) findViewById(R.id.di);
        df1 = (TextView) findViewById(R.id.df);
        b1.setOnClickListener(this);
/*Depuración xd 06/MAyo/2019 Mares: encontre mi error no olvidar enviar bien los datos a un texview
    no mandar ints solo strings

        cuatro = (TextView) findViewById(R.id.v4);
        cinco = (TextView) findViewById(R.id.v5);
        seis = (TextView) findViewById(R.id.v6);
        siete = (TextView) findViewById(R.id.v7);
        ocho = (TextView) findViewById(R.id.v8);*/
    }
    String aa,bb,cc,dd,ee;
    @Override
    public void onClick(View view) {

        //Verificar que no esten los campos vacios
        /*
        if (a.getText().toString().isEmpty() || b.getText().toString().isEmpty() || c.getText().toString().isEmpty() || d.getText().toString().isEmpty() || div.getText().toString().isEmpty()
                ){
            ver.setText("Llene los campos");
        }*/
        if(Patterns.IP_ADDRESS.matcher(etip.getText().toString()).matches() == false){
            inip.setError("Ip no valida");
            validaIP = false;
        }else {// si no estan vacios

            validaIP = true;
            inip.setError(null);

            String string = etip.getText().toString();
            String aux = "";

            aux = string.replace(".","-");
            String[] parts = aux.split("-",4);
            String part1 = parts[0]; // 123
            String part2 = parts[1]; // 654321
            String part3 = parts[2];
            String part4 = parts[3];

/*
            int a1 = Integer.parseInt(a.getText().toString());
            int b1 = Integer.parseInt(b.getText().toString());
            int c1 = Integer.parseInt(c.getText().toString());
            int d1 = Integer.parseInt(d.getText().toString());
            int e1 = Integer.parseInt(div.getText().toString());
            */
            int a1 = Integer.parseInt(part1);
            int b1 = Integer.parseInt(part2);
            int c1 = Integer.parseInt(part3);
            int d1 = Integer.parseInt(part4);
            int e1 = Integer.parseInt(div.getText().toString());
            if (a1>255 || b1>255 || c1> 255 || d1 > 254 ){// que no pasen de 255
                ver.setText("Verifique direccion ip");
            }
            else {//si la direccion es correcta verificar la division
                if (e1<=30 ) {

                    ver.setText("Direccion y division correcta");
                    setMascara(e1);
                    aa = ""+a1;
                    bb = ""+b1;
                    cc = ""+c1;
                    dd = ""+d1;
                    //probando
                    if (e1 < 8) {
                        int resta = 8 - e1;//saber la posicion de la ultima x en el octeto
                        //if si e1 = 8 entonces resta-1
                        int salto = (int) Math.pow(2, resta);//proximo salto para referencia
                        int fin = a1 + salto - 1;
                        mr1.setText(a1 + ".0.0.0/"+e1);//ivan es identificador de red NETWORK ID no mascara de red
                        setIdNetwork(a1 + ".0.0.0/");
                        di1.setText(a1 + ".0.0.1/"+e1);
                        setDireccionInicial(a1 + ".0.0.1/");
                        if (fin > 255) {//si pasa de 255
                            int sob = fin - 255 - 1;
                            df1.setText((a1 + 1) + "." + sob + ".255.254/" + e1);
                            setDireccionFinal((a1 + 1) + "." + sob + ".255.254/");
                        } else{
                            df1.setText(fin + ".255.255.254/" + e1);
                            setDireccionFinal(fin + ".255.255.254/");
                        }
                    }

                    else if (e1 > 8 && e1 <= 16) {//segundo octeto direccion ip
                        int resta = 16 - e1;
                        int salto = (int) Math.pow(2, resta);
                        int fin = b1 + salto - 1;
                        mr1.setText(a1 + "." + b1 + ".0.0/" + e1);
                        setIdNetwork(a1 + "." + b1 + ".0.0/");
                        di1.setText(a1 + "." + b1 + ".0.1/" + e1);
                        setDireccionInicial(a1 + "." + b1 + ".0.1/");
                        if (fin > 255) {//si pasa de 255
                            int sob = fin -255- 1;
                            df1.setText((a1 + 1) + "." + sob + ".255.254/");
                            setDireccionFinal((a1 + 1) + "." + sob + ".255.254/" + e1);
                        }
                        else {
                            df1.setText(a1 + "." + fin + ".255.254/" + e1);
                            setDireccionFinal(a1 + "." + fin + ".255.254/");
                        }

                        }

                    else if (e1>16 && e1<=24 ){//tercer octeto direccion ip
                        int resta = 24 - e1;
                        int salto = (int) Math.pow(2, resta);
                        int fin = c1+salto-1;
                        mr1.setText(a1+"."+b1+"."+c1+".0/"+e1);
                        setIdNetwork(a1+"."+b1+"."+c1+".0/");
                        di1.setText(a1+"."+b1+"."+c1+".1/"+e1);
                        setDireccionInicial(a1+"."+b1+"."+c1+".1/");
                        if (fin>255){//si pasa de 255
                            int sob = fin-255-1;
                            df1.setText(a1+"."+(b1+1)+"."+sob+".254/"+e1);
                            setDireccionFinal(a1+"."+(b1+1)+"."+sob+".254/");
                        }
                        else {
                            df1.setText(a1 + "." + b1 + "." + fin + ".254/" + e1);
                            setDireccionFinal(a1 + "." + b1 + "." + fin + ".254/");
                        }
                        }



                    else {//cuarto octeto direccion ip
                        int resta = 32 - e1;
                        int salto = (int) Math.pow(2, resta);
                        int fin = d1+salto-1;
                        mr1.setText(a1+"."+b1+"."+c1+".0/"+e1);
                        setIdNetwork(a1+"."+b1+"."+c1+".0/");
                        di1.setText(a1+"."+b1+"."+c1+".1/"+e1);
                        setDireccionInicial(a1+"."+b1+"."+c1+".1/");
                        if (fin>255){//si pasa de 255
                            int sob = fin-255-1;
                            df1.setText(a1+"."+b1+"."+(c1+1)+"."+sob+"/"+e1);
                            setDireccionFinal(a1+"."+b1+"."+(c1+1)+"."+sob+"/");
                        }
                        else {
                            df1.setText(a1 + "." + b1 + "." + c1 + "." + d1 + "/" + e1);
                            setDireccionFinal(a1 + "." + b1 + "." + c1 + "." + d1 + "/");
                        }
                        }

                    }

                else
                    ver.setText("Division / incorrecta");
            }//2else
        }//1 else


    }//metodo onClick

    //metodo añadido ivan, segundo boton para cambiar a otra intent e imprimir subredes de tus i'ps
    public void botonSubRedes(View v){

        if (e.getText().toString().isEmpty()){
            ver.setText("Llene los campos porfavor");
        }else{
            ver.setText("Generando");
            Proceso();

        }

    }
    public void Proceso(){

        String Nosubredes = e.getText().toString();
        Intent x = new Intent("com.example.modelosdered.Main3Activity");

        x.putExtra("IDNETWORK",getIdNetwork());
        x.putExtra("IPINICIAL",getDireccionInicial());
        x.putExtra("IPFINAL",getDireccionFinal());
        x.putExtra("MASCARA",String.valueOf(getMascara()));
        x.putExtra("SUBREDES",Nosubredes);

        x.putExtra("PrimerOcteto",aa);
        x.putExtra("SegundoOcteto",bb);
        x.putExtra("TercerOcteto",cc);
        x.putExtra("CuartoOcteto",dd);


        startActivity(x);

        /*
        cuatro.setText(getIdNetwork());
        cinco.setText(getDireccionInicial());
        seis.setText(getDireccionFinal());
        siete.setText(String.valueOf(getMascara()));
        ocho.setText((e.getText().toString()));
        */

    }

}
