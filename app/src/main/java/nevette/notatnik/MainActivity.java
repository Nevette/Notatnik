package nevette.notatnik;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button b1, b2;


    public void nowy_ekran_notatki(){
        Intent i = new Intent(this,nowa_notatka.class);
        startActivity(i);
    }

    public void nowy_ekran_zapisane(){
        Intent j = new Intent(this,zapisane.class);
        startActivity(j);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        b1=(Button) findViewById(R.id.przycisk_nowa);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowy_ekran_notatki();
            }
        });

        b2=(Button) findViewById(R.id.przycisk_zapisane);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nowy_ekran_zapisane();

            }
        });


    }

    }



