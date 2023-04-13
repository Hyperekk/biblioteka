package com.example.biblioteka;

public class Rozdzielacz
{
    public String tutul;
    public String IBSBN;
    public String autor;
    public String rokWydania;
    public String wydawnictwo;
    public String opis;

    public Rozdzielacz(String tutul,String IBSBN, String autor, String rokWydania, String wydawnictwo, String opis)
    {
        super();
        this.tutul = tutul;
        this.IBSBN = IBSBN;
        this.autor = autor;
        this.rokWydania = rokWydania;
        this.wydawnictwo = wydawnictwo;
        this.opis = opis;
    }
}
