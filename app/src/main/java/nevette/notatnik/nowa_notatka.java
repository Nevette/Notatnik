package nevette.notatnik;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import nevette.notatnik.notatki.BazaDanych;
import nevette.notatnik.notatki.Notatka;

public class nowa_notatka extends AppCompatActivity {

    EditText pole_text;
    Button b1, b2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final BazaDanych bd = new BazaDanych(getBaseContext());
        setContentView(R.layout.activity_nowa_notatka);

        b1 = (Button) findViewById(R.id.przycisk_wyczysc);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pole_text.setText("");
            }
        });

        b2 = (Button) findViewById(R.id.przycisk_zapisz);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wpisany_tekst = pole_text.getText().toString();
                Notatka notatka = new Notatka();
                notatka.setData(new Date());
                notatka.setTresc(wpisany_tekst);

                if (wpisany_tekst.length() > 20)
                {
                    notatka.setTytul(wpisany_tekst.substring(0,19));
                }
                else
                {
                    notatka.setTytul((wpisany_tekst));
                }

                bd.dodajNotatke(notatka);
            }
        });

        pole_text = (EditText) findViewById(R.id.pole_text);
        pole_text.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            String poletext = pole_text.getText().toString();
            if (poletext.equals("Wpisz tekst")) {
                pole_text.setText("");

            }
            }
        }
        );
    }


}

