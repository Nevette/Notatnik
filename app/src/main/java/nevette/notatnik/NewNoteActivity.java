package nevette.notatnik;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import nevette.notatnik.notatki.BazaDanych;
import nevette.notatnik.notatki.Notatka;

public class NewNoteActivity extends AppCompatActivity {

    private EditText poleText;
    private Button b1, b2;
    private BazaDanych bd;
    private Context context;

    private Notatka wyswietlanaNotatka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_note);
        wyswietlanaNotatka = (Notatka) getIntent().getSerializableExtra("Notatka");
        context = getBaseContext();

        bd = new BazaDanych(this.getBaseContext());

        b1 = (Button) findViewById(R.id.przycisk_wyczysc);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poleText.setText("");
            }
        });


        b2 = (Button) findViewById(R.id.przycisk_zapisz);
        b2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String wpisanyTekst = poleText.getText().toString();
                wyswietlanaNotatka.setData(new Date());
                wyswietlanaNotatka.setTresc(wpisanyTekst);

                if (wpisanyTekst.length() > 20)
                {
                    wyswietlanaNotatka.setTytul(wpisanyTekst.substring(0,19));
                }
                else
                {
                    wyswietlanaNotatka.setTytul((wpisanyTekst));
                }
                if (wyswietlanaNotatka.getId() == null)
                    bd.dodajNotatke(wyswietlanaNotatka);
                else
                    bd.zapiszNotatke(wyswietlanaNotatka);

            Toast toast = Toast.makeText(context,"Zapisano",Toast.LENGTH_SHORT);
            toast.show();
            }
        });

        poleText = (EditText) findViewById(R.id.pole_text);
        if (wyswietlanaNotatka.getId() != null)
        {
            poleText.setText(wyswietlanaNotatka.getTresc());
        }

        poleText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String poletext = poleText.getText().toString();
                if (poletext.equals("Wpisz tekst")) {
                    poleText.setText("");
                }
            }
        }
        );
    }
}

