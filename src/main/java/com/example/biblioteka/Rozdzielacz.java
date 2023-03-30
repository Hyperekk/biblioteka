package com.example.biblioteka;

public class Rozdzielacz
{
    public String tutuł;
    public String IBSBN;
    public String autor;
    public String rokWydania;
    public String wydawnictwo;
    public String opis;

    public Rozdzielacz(String tutuł,String IBSBN, String autor, String rokWydania, String wydawnictwo, String opis)
    {
        this.tutuł = tutuł;
        this.IBSBN = IBSBN;
        this.autor = autor;
        this.rokWydania = rokWydania;
        this.wydawnictwo = wydawnictwo;
        this.opis = opis;
    }
}
