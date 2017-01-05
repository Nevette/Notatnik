package nevette.notatnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import nevette.notatnik.notatki.Notatka;

public class MenuActivity extends AppCompatActivity {

    private Button b1, b2;

    public void nowyEkranNotatki(){
        Intent i = new Intent(this,NewNoteActivity.class);
        Notatka notatka = new Notatka();
        i.putExtra("Notatka", notatka);
        startActivity(i);
    }

    public void nowyEkranZapisane(){
        Intent j = new Intent(this,SavedNotesActivity.class);
        startActivity(j);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        b1=(Button) findViewById(R.id.przycisk_nowa);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowyEkranNotatki();
            }
        });

        b2=(Button) findViewById(R.id.przycisk_zapisane);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowyEkranZapisane();

            }
        });
    }
}



