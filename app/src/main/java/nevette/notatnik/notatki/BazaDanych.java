package nevette.notatnik.notatki;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Nevire on 2016-11-28.
 */

public class BazaDanych extends SQLiteOpenHelper {

    private static final String BAZA_NOTATEK = "bazaNotatek";
    private static final Integer WERSJA_BAZY = 1;

    private static final String TABELA_NOTATEK = "notatki";
    private static final String ID_NOTATKI = "id";
    private static final String TYTUL_NOTATKI = "tytul";
    private static final String DATA_NOTATKI = "data";
    private static final String TRESC_NOTATKI = "tresc";

    private static final String[] KOLUMNY_NOTATEK = {ID_NOTATKI, TYTUL_NOTATKI, TRESC_NOTATKI};

    public BazaDanych(Context context) {
        super(context, BAZA_NOTATEK, null, WERSJA_BAZY);
    }

    public void dodajNotatke(Notatka notatka)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues wartosci = new ContentValues();
        wartosci.put(TYTUL_NOTATKI, notatka.getTytul());
        wartosci.put(TRESC_NOTATKI, notatka.getTresc());
        db.insert(TABELA_NOTATEK, null, wartosci);
        db.close();
    }

    public Notatka pobierzNotatke (Long id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.query(TABELA_NOTATEK, KOLUMNY_NOTATEK, "id = ?", new String[] {id.toString()}, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Notatka notatka = new Notatka();
        notatka.setId(Long.parseLong(cursor.getString(0)));
        notatka.setTytul(cursor.getString(1));
        notatka.setTresc(cursor.getString(2));
        return notatka;
    }

    public void zapiszNotatke(Notatka notatka)
    {
        SQLiteDatabase db = this.getWritableDatabase();
       /* String sql = "update " + TABELA_NOTATEK + " set " +
                TYTUL_NOTATKI + " = " + notatka.getTytul() + "," +
                TRESC_NOTATKI + " = " + notatka.getTresc() +
                " where id = " + notatka.getId().toString();*/

        ContentValues values = new ContentValues();
        values.put(TYTUL_NOTATKI, notatka.getTytul());
        values.put(TRESC_NOTATKI, notatka.getTresc());

        int i = db.update(TABELA_NOTATEK, values, "id = ?", new String[]{notatka.getId().toString()});
        db.close();

    }

    public void usunNotatke(Notatka notatka)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "delete from " + TABELA_NOTATEK + " where id = " + notatka.getId().toString();
        System.out.println(sql);
        db.execSQL(sql);
        db.close();
    }

    public List<Notatka> zwrocWszystkieNotatki()
    {
        List<Notatka> listaNotatek = new ArrayList();
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "select * from " + TABELA_NOTATEK;
        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null && cursor.moveToFirst())
        {
            do
            {
                Notatka notatka = new Notatka();
                notatka.setId(Long.parseLong(cursor.getString(0)));
                notatka.setTytul(cursor.getString(1));
                notatka.setTresc(cursor.getString(2));
                listaNotatek.add(notatka);
            }
            while (cursor.moveToNext());
        }
        return listaNotatek;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    String sql = "create table notatki " +
            "( id integer primary key autoincrement, " +
            "tytul text," +
            "tresc text )";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
    String sql = "drop table if exists notatki";
        db.execSQL(sql);
        this.onCreate(db);
    }
}
