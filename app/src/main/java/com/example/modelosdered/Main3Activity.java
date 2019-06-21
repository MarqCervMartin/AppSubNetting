package com.example.modelosdered;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;



public class Main3Activity extends AppCompatActivity {
    //CarouselPicker pick1;
    private TextView nSRedes,calculada,ipini,ipfin,idNet;
    private ListView listV;
    private String[] ips;

    private String IdNetwork ,IpInicial, IPFinal ,mascara ,Nosubredes;

    public String getIdNetwork() {
        return IdNetwork;
    }

    public void setIdNetwork(String idNetwork) {
        IdNetwork = idNetwork;
    }

    public String getIpInicial() {
        return IpInicial;
    }

    public void setIpInicial(String ipInicial) {
        IpInicial = ipInicial;
    }

    public String getIPFinal() {
        return IPFinal;
    }

    public void setIPFinal(String IPFinal) {
        this.IPFinal = IPFinal;
    }

    public String getMascara() {
        return mascara;
    }

    public void setMascara(String mascara) {
        this.mascara = mascara;
    }

    public String getNosubredes() {
        return Nosubredes;
    }

    public void setNosubredes(String nosubredes) {
        Nosubredes = nosubredes;
    }

    String a,b,c,d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        listV = (ListView) findViewById(R.id.LIDNETWORK);
        //pick1 = (CarouselPicker) findViewById(R.id.Carousel);
        //v1 = (TextView) findViewById(R.id.View1);
        //v2 = (TextView) findViewById(R.id.View2);
        nSRedes = (TextView) findViewById(R.id.TVNSR);
        calculada = (TextView) findViewById(R.id.TVNMC);
        idNet = (TextView) findViewById(R.id.TVIDN);
        ipini = (TextView) findViewById(R.id.TVIPI);
        ipfin = (TextView) findViewById(R.id.TVIPF);

        Bundle extras = getIntent().getExtras();
        setIdNetwork(extras.getString("IDNETWORK")) ;
        setIpInicial(extras.getString("IPINICIAL")) ;
        setIPFinal(extras.getString("IPFINAL"));
        setMascara(extras.getString("MASCARA"));
        setNosubredes(extras.getString("SUBREDES"));
        a = extras.getString("PrimerOcteto");
        b = extras.getString("SegundoOcteto");
        c = extras.getString("TercerOcteto");
        d = extras.getString("CuartoOcteto");

        //clcular nueva mascara
        int e = Integer.parseInt(getMascara())+Verificar(getNosubredes());
/* si sirvio ;( pero lo comentare
        String alv [] = {getIdNetwork(),getIpInicial(),getIPFinal(),getMascara(),""+e,getNosubredes(),a1,b1,c1,d1};
        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this,R.layout.list_item_ips, alv);
        listV.setAdapter(adapter); */
        //Dividir(e);
        Imprimir(e);
    }
    //aplicando formula 2^n >= numRedes
    public int Verificar(String num){
        int aux;
        double resultado;
        int numReds = Integer.parseInt(num);
        resultado = (Math.log(numReds))/(Math.log(2));
        //System.out.print(resultado);
        //v2.setText(String.valueOf(Math.ceil(resultado)));
        return aux = (int)Math.ceil(resultado);
    }
/*
    public void Dividir(int e){

        String[] partes = getIdNetwork().split(".");
        a1 = partes[0];
        b1 = partes[1];
        c1 = partes[2];
        d1 = partes[3];

        if(d1.contains("/")){
            String parteDos[] = d1.split("/");
            d1 = parteDos[0];
            String basura = parteDos[1];
        }
        String e1 = String.valueOf(e);
        String alv [] = {a1,b1,c1,d1,e1};
        ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.list_item_ips, alv);
        listV.setAdapter(adapter);
        //return a1,b1,c1,d1,e1;
    }*/

    public void Imprimir(int e1){

        nSRedes.setText(getNosubredes());
        calculada.setText(e1+"");
        idNet.setText(getIdNetwork()+e1);
        ipini.setText(getIpInicial()+e1);
        ipfin.setText(getIPFinal()+e1);

        int a1 = Integer.parseInt(a);
        int b1 = Integer.parseInt(b);
        int c1 = Integer.parseInt(c);
        int d1 = Integer.parseInt(d);
        ips = new String[Integer.parseInt(getNosubredes())];
        int count=1;
        if (e1 < 8) {
            int resta = 8 - e1;//saber la posicion de la ultima x en el octeto
            //if si e1 = 8 entonces resta-1
            int salto = (int) Math.pow(2, resta);//proximo salto para referencia
            //int fin = a1 + salto;


            //llenar
            for(int i=0;i<Integer.parseInt(getNosubredes());i++){
                if(i==0){
                    //ips[0] = count+"\t"+a1+b1+c1+d1;
                    ips[0]=count+"\t"+a1+".0.0.0/"+e1+"\t\t\t"+a1+"0.0.1/"+e1+"\t\t\t"+a1+"255.255.254/"+e1;
                }else {
                    a1+=salto;
                    ips[i] = count + "\t" + (a1) + ".0.0.0/" + (e1) + "\t\t\t" + (a1) + ".0.0.1/" + (e1) + "\t\t\t" + (a1) + ".255.255.254/" + (e1);

                }
                count++;
            }
            //imprime
            ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.list_item_ips, ips);
            listV.setAdapter(adapter);
            /*
            mr1.setText(a1 + ".0.0.0/"+e1);//ivan es identificador de red NETWORK ID no mascara de red
            di1.setText(a1 + ".0.0.1/"+e1);
            if (fin > 255) {//si pasa de 255
                int sob = fin - 255 - 1;
                df1.setText((a1 + 1) + "." + sob + ".255.254/" + e1);
            } else{
                df1.setText(fin + ".255.255.254/" + e1);
            }*/
        }

        else if (e1 > 8 && e1 <= 16) {//segundo octeto direccion ip
            int resta = 16 - e1;
            int salto = (int) Math.pow(2, resta);

            for(int i=0;i<Integer.parseInt(getNosubredes());i++){
                if(i==0){
                    //ips[0] = count+"\t"+a1+b1+c1+d1;
                    ips[0]=count+"\t"+a1+"."+b1+".0.0/"+e1+"\t\t\t"+a1+"."+b1+".0.1/"+e1+"\t\t\t"+a1+"."+(b1+salto-1)+".255.254/"+e1;
                }else {
                    b1+=salto;
                    if(b1>255){
                        b1 = b1-256;
                        a1 ++;
                    }
                    ips[i] = count + "\t" + (a1) +"."+ b1+".0.0/" + (e1) + "\t\t\t" + (a1) +"."+b1+ ".0.1/" + (e1) + "\t\t\t" + (a1) + "."+(b1+salto-1)+ ".255.254/" + (e1);

                }
                count++;
            }
            //imprime
            ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.list_item_ips, ips);
            listV.setAdapter(adapter);

            /*
            mr1.setText(a1 + "." + b1 + ".0.0/" + e1);
            setIdNetwork(a1 + "." + b1 + ".0.0/" + e1);
            di1.setText(a1 + "." + b1 + ".0.1/" + e1);
            setDireccionInicial(a1 + "." + b1 + ".0.1/" + e1);
            if (fin > 255) {//si pasa de 255
                int sob = fin -255- 1;
                df1.setText((a1 + 1) + "." + sob + ".255.254/" + e1);
                setDireccionFinal((a1 + 1) + "." + sob + ".255.254/" + e1);
            }
            else {
                df1.setText(a1 + "." + fin + ".255.254/" + e1);
                setDireccionFinal(a1 + "." + fin + ".255.254/" + e1);
            }*/

        }

        else if (e1>16 && e1<=24 ){//tercer octeto direccion ip
            int resta = 24 - e1;
            int salto = (int) Math.pow(2, resta);

            for(int i=0;i<Integer.parseInt(getNosubredes());i++){
                if(i==0){
                    //ips[0] = count+"\t"+a1+b1+c1+d1;
                    ips[0]=count+"\t"+a1+"."+b1+"."+c1+".0/"+e1+"\t\t\t"+a1+"."+b1+"."+c1+".1/"+e1+"\t\t\t"+a1+"."+b1+"."+(c1+salto-1)+".254/"+e1;
                }else {
                    c1+=salto;
                    if(c1>255){
                        c1 = c1-256;
                        b1 ++;
                    }
                    ips[i] = count + "\t" + (a1) + "."+b1+"."+c1+".0/" + (e1) + "\t\t\t" + (a1) +"."+b1+"."+c1+ ".1/" + (e1) + "\t\t\t" + (a1) +"."+ b1+"."+(c1+salto-1)+ ".254/" + (e1);

                }
                count++;
            }
            //imprime
            ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.list_item_ips, ips);
            listV.setAdapter(adapter);
            /*
            int fin = c1+salto-1;
            mr1.setText(a1+"."+b1+"."+c1+".0/"+e1);
            setIdNetwork(a1+"."+b1+"."+c1+".0/"+e1);
            di1.setText(a1+"."+b1+"."+c1+".1/"+e1);
            setDireccionInicial(a1+"."+b1+"."+c1+".1/"+e1);
            if (fin>255){//si pasa de 255
                int sob = fin-255-1;
                df1.setText(a1+"."+(b1+1)+"."+sob+".254/"+e1);
                setDireccionFinal(a1+"."+(b1+1)+"."+sob+".254/"+e1);
            }
            else {
                df1.setText(a1 + "." + b1 + "." + fin + ".254/" + e1);
                setDireccionFinal(a1 + "." + b1 + "." + fin + ".254/" + e1);
            }*/
        }



        else {//cuarto octeto direccion ip
            int resta = 32 - e1;
            int salto = (int) Math.pow(2, resta);
            for(int i=0;i<Integer.parseInt(getNosubredes());i++){
                if(i==0){
                    //ips[0] = count+"\t"+a1+b1+c1+d1;
                    ips[0]=count+"\t"+a1+"."+b1+"."+c1+"."+d1+"/"+e1+"\t\t\t"+a1+"."+b1+"."+c1+"."+d1+"/"+e1+"\t\t\t"+a1+"."+b1+"."+c1+"."+(d1+salto-1)+"/"+e1;
                }else {
                    d1+=salto;
                    if(d1>255){
                        d1 = d1-256;
                        c1 ++;
                    }
                    ips[i] = count + "\t" + a1+"."+b1+"."+c1+"."+d1+"/" + (e1) + "\t\t\t" + a1+"."+b1+"."+c1+"."+d1+ "/" + (e1) + "\t\t\t" + a1+"."+b1+"."+c1+"."+(d1+salto-1)+"/" + (e1);

                }
                count++;
            }
            //imprime
            ArrayAdapter <String> adapter = new ArrayAdapter<>(this, R.layout.list_item_ips, ips);
            listV.setAdapter(adapter);

            /*
            int fin = d1+salto-1;
            mr1.setText(a1+"."+b1+"."+c1+".0/"+e1);
            setIdNetwork(a1+"."+b1+"."+c1+".0/"+e1);
            di1.setText(a1+"."+b1+"."+c1+".1/"+e1);
            setDireccionInicial(a1+"."+b1+"."+c1+".1/"+e1);
            if (fin>255){//si pasa de 255
                int sob = fin-255-1;
                df1.setText(a1+"."+b1+"."+(c1+1)+"."+sob+"/"+e1);
                setDireccionFinal(a1+"."+b1+"."+(c1+1)+"."+sob+"/"+e1);
            }
            else {
                df1.setText(a1 + "." + b1 + "." + c1 + "." + d1 + "/" + e1);
                setDireccionFinal(a1 + "." + b1 + "." + c1 + "." + d1 + "/" + e1);
            }*/
        }
    }//funcion imprimir


}
