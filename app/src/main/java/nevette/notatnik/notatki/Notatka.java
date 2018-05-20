package nevette.notatnik.notatki;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Nevire on 2016-11-29.
 */

public class Notatka implements Serializable{

    private Long id;
    private String tytul;
    private Date data;
    private String tresc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getTresc() {
        return tresc;
    }

    public void setTresc(String tresc) {
        this.tresc = tresc;
    }





}
