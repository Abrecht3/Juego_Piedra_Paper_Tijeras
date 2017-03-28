/*Aplicacion desarrollada por Alberto Perez Covarrubias
* juego de piedra, papel o tijeras para mostrar el uso
* de los imagebuton, imageview y el temporizador para
* ocultar la barra de navegacion
* usando estructuras de seleccion switch
* */

package com.uamazc.albertoperez.piedrapapeltijeras;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static int TIEMPO_ESPERA = 15000;

    ImageButton rock,paper,scissor;
    ImageView tiro,tirocom;
    TextView resultado;
    int jugada,jugadaCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jugador();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            setContentView(R.layout.creditos);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        View decorView=getWindow().getDecorView();
        if (hasFocus) {
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_FULLSCREEN);
            ocultar(TIEMPO_ESPERA);
        }

    }

    //Funcion para realizar las tiradas del jugador
    public void jugador()
    {
        rock=(ImageButton) findViewById(R.id.piedra);
        paper=(ImageButton) findViewById(R.id.papel);
        scissor=(ImageButton) findViewById(R.id.tijeras);


        rock.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tiro=(ImageView)findViewById(R.id.tirada);
                tiro.setImageResource(R.drawable.stone);
                tiroCom();
                jugada =1;
                resultado(jugada,jugadaCom);
            }
        });
        paper.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tiro=(ImageView)findViewById(R.id.tirada);
                tiro.setImageResource(R.drawable.paper);
                tiroCom();
                jugada =2;
                resultado(jugada,jugadaCom);
            }
        });
        scissor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                tiro=(ImageView)findViewById(R.id.tirada);
                tiro.setImageResource(R.drawable.scissors);
                tiroCom();
                jugada =3;
                resultado(jugada,jugadaCom);
            }
        });
    }

    //Funcion que genera al azar las tiradas de la computadora
    public void tiroCom()
    {
        int opc=(int) (Math.random()*3)+1;
        switch (opc)
        {
            case 1: tirocom=(ImageView)findViewById(R.id.tiradacom);
                tirocom.setImageResource(R.drawable.stonecom);
                jugadaCom =1;
                break;
            case 2:  tirocom=(ImageView)findViewById(R.id.tiradacom);
                tirocom.setImageResource(R.drawable.papercom);
                jugadaCom =2;
                break;
            default: tirocom=(ImageView)findViewById(R.id.tiradacom);
                tirocom.setImageResource(R.drawable.scissorscom);
                jugadaCom =3;
                break;
        }
    }

    // esta funcion determina el resultado
    public void resultado(int opc,int resp)
    {
        switch (opc)
        {
            case 1: switch (resp)
            {
                case 1: resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.empata);
                    break;
                case 2: resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.pierde);
                    break;
                default:resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.gana);
                    break;
            }
                break;
            case 2: switch (resp)
            {
                case 1: resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.gana);
                    break;
                case 2: resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.empata);
                    break;
                default:resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.pierde);
                    break;
            }
                break;
            default:switch (resp)
            {
                case 1: resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.pierde);
                    break;
                case 2: resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.gana);
                    break;
                default:resultado=(TextView) findViewById(R.id.reslutado);
                    resultado.setText(R.string.empata);
                    break;
            }
                break;
        }
    }

    //Funcion para ocultar la navigation bar despues de un tiempo determinado
    public void ocultar(int milisegundos) {
        final View decorView=getWindow().getDecorView();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                // acciones que se ejecutan tras los milisegundos
                int uiOptions=   View.SYSTEM_UI_FLAG_FULLSCREEN;
                decorView.setSystemUiVisibility(uiOptions);
                ocultar(TIEMPO_ESPERA);
            }
        }, milisegundos);
    }
}