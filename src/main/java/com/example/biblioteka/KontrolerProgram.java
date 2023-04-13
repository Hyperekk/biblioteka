package com.example.biblioteka;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class KontrolerProgram
{
    public Button btnLewo;

    public TextField txtUser;
    public Button btnPrawo;
    public TextField txt1, txt2, txt3, txt4, txt5, txt6;

    public List<Rozdzielacz> ksiazki = new ArrayList<>();
    public Button btnDodaj;
    public Button btnZamień;
    public Button btnUsuń;
    public Button btnZapisz;
    public Button btnAnuluj;
    public Label lb1;

    public int numerKsiazki = 0;

    public void initialize() throws IOException {
        //String login = txtUser.getText();
        //lb1.setText(login);

        Path path = Paths.get("C:/Users/Bob/Desktop/Biblioteka.txt");
        if (!Files.exists(path))
            Files.createFile(path);

        List<String> linie = Files.readAllLines(path);

        for (String linia : linie )
        {
            String[] parts = linia.split("#");
            Rozdzielacz rozdzielacz = new Rozdzielacz(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            ksiazki.add(rozdzielacz);

            txt1.setText(ksiazki.get(numerKsiazki).tutul = parts[0]);
            txt2.setText(ksiazki.get(numerKsiazki).IBSBN = parts[1]);
            txt3.setText(ksiazki.get(numerKsiazki).autor = parts[2]);
            txt4.setText(ksiazki.get(numerKsiazki).rokWydania = parts[3]);
            txt5.setText(ksiazki.get(numerKsiazki).wydawnictwo = parts[4]);
            txt6.setText(ksiazki.get(numerKsiazki).opis = parts[5]);

        }
    }

    public void btnPrawoLewo(ActionEvent actionEvent)
    {
        ksiazki.get(numerKsiazki - 1);

        txt1.setText(ksiazki.get(numerKsiazki).tutul);
        txt2.setText(ksiazki.get(numerKsiazki).IBSBN);
        txt3.setText(ksiazki.get(numerKsiazki).autor);
        txt4.setText(ksiazki.get(numerKsiazki).rokWydania);
        txt5.setText(ksiazki.get(numerKsiazki).wydawnictwo);
        txt6.setText(ksiazki.get(numerKsiazki).opis);
    }
    public void btnPrawoRuch(ActionEvent actionEvent)
    {
        ksiazki.get(numerKsiazki + 1);

        txt1.setText(ksiazki.get(numerKsiazki).tutul);
        txt2.setText(ksiazki.get(numerKsiazki).IBSBN);
        txt3.setText(ksiazki.get(numerKsiazki).autor);
        txt4.setText(ksiazki.get(numerKsiazki).rokWydania);
        txt5.setText(ksiazki.get(numerKsiazki).wydawnictwo);
        txt6.setText(ksiazki.get(numerKsiazki).opis);
    }
}
