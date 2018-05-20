package nevette.notatnik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import nevette.notatnik.notatki.BazaDanych;
import nevette.notatnik.notatki.Notatka;

public class zapisane extends AppCompatActivity {


    LinearLayout listazapisanych;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zapisane);

         listazapisanych = (LinearLayout) findViewById(R.id.lista_notatek);

        List<Notatka> listaNotatek = new ArrayList<>();

        BazaDanych bd = new BazaDanych(getBaseContext());
        listaNotatek.addAll(bd.zwrocWszystkieNotatki());

        for (Notatka notatka: listaNotatek)
        {
            TextView etykieta = new TextView(this);
            etykieta.setText(notatka.getTytul());
            listazapisanych.addView(etykieta);
        }

    }
}
