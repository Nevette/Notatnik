package nevette.notatnik;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

import nevette.notatnik.notatki.BazaDanych;
import nevette.notatnik.notatki.Notatka;

public class SavedNotesActivity extends AppCompatActivity {

    private Context context;
    private LinearLayout listaZapisanych;
    private BazaDanych bd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_notes);

        context = this.getBaseContext();
        bd = new BazaDanych(getBaseContext());

         listaZapisanych = (LinearLayout) findViewById(R.id.lista_notatek);

        odswiez();
    }

    public void odswiez()
    {
        listaZapisanych.removeAllViews();

        List<Notatka> listaNotatek = new ArrayList<>();
        listaNotatek.addAll(bd.zwrocWszystkieNotatki());

        for (Notatka notatka: listaNotatek)
        {
            LinearLayout wiersz = new LinearLayout(context);
            wiersz.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            wiersz.setOrientation(LinearLayout.HORIZONTAL);

            Button etykieta = new Button(this);
            etykieta.setBackgroundResource(R.drawable.round_button_2);
            etykieta.setTextSize(18);
            etykieta.setTextColor(Color.BLACK);
            etykieta.setClickable(true);
            etykieta.setText(notatka.getTytul());
            listaZapisanych.addView(wiersz);
            wiersz.addView(etykieta);


            Button usun = new Button(this);
            usun.setBackgroundResource(R.drawable.round_button);

            usun.setTextSize(18);
            usun.setText("Usuń");
            usun.setClickable(true);
            wiersz.addView(usun);

            etykieta.setOnClickListener(zwrocAkcjeKliknieciaNotatki(notatka));
            usun.setOnClickListener(zwrocAkcjeUsunieciaNotatki(notatka));
        }
    }

    private View.OnClickListener zwrocAkcjeUsunieciaNotatki(final Notatka notatka){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bd.usunNotatke(notatka);
                odswiez();
            }
        };
    }

    private View.OnClickListener zwrocAkcjeKliknieciaNotatki(final Notatka notatka){
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowyEkranNotatki(notatka);
            }
        };
    }

    public void nowyEkranNotatki(Notatka notatka){
        Intent i = new Intent(this,NewNoteActivity.class);
        i.putExtra("Notatka", notatka);
        startActivity(i);
    }
}
