package com.example.biblioteka;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class KontrolerProgram
{
    public Button btnLewo;

    public TextField txtUser;
    public Button btnPrawo;
    public TextField txt1, txt2, txt3, txt4, txt5, txt6;

    public List<Rozdzielacz> ksiazki = new ArrayList<>();
    public Button btnDodaj;
    public Button btnZamien;
    public Button btnUsun;
    public Button btnZapiszSave;
    public Button btnAnuluj;
    public Label lb1;

    public int numerKsiazki = 0;

    public int numberOfTries = 0;


    public void initialize() throws IOException {
        lb1.setText(DataExchage.userName);


        //Stream.of( txt1, txt2, txt3, txt4, txt5, txt6).forEach(txt -> {
            //txt.setDisable(true);
        //});

        Path path = Paths.get("C:/Users/Bob/Desktop/Biblioteka.txt");
        if (!Files.exists(path))
            Files.createFile(path);

        List<String> linie = Files.readAllLines(path);

        for (String linia : linie )
        {
            String[] parts = linia.split("#");
            Rozdzielacz rozdzielacz = new Rozdzielacz(parts[0], parts[1], parts[2], parts[3], parts[4], parts[5]);
            ksiazki.add(rozdzielacz);
            System.out.println(ksiazki.size());

        }

        System.out.println(numerKsiazki);

        txt1.setText(ksiazki.get(numerKsiazki).tutul);
        txt2.setText(ksiazki.get(numerKsiazki).IBSBN);
        txt3.setText(ksiazki.get(numerKsiazki).autor);
        txt4.setText(ksiazki.get(numerKsiazki).rokWydania);
        txt5.setText(ksiazki.get(numerKsiazki).wydawnictwo);
        txt6.setText(ksiazki.get(numerKsiazki).opis);

        resetButtons();
    }

    public void btnPrawoLewo(ActionEvent actionEvent)
    {
        numerKsiazki--;
        numberOfTries = 0;

        /**
         * zrobione do sprawdzania numeru ksiazki i poprawnego dzialania
         */
        System.out.println(numerKsiazki);

        txt1.setText(ksiazki.get(numerKsiazki).tutul);
        txt2.setText(ksiazki.get(numerKsiazki).IBSBN);
        txt3.setText(ksiazki.get(numerKsiazki).autor);
        txt4.setText(ksiazki.get(numerKsiazki).rokWydania);
        txt5.setText(ksiazki.get(numerKsiazki).wydawnictwo);
        txt6.setText(ksiazki.get(numerKsiazki).opis);
    }
    public void btnPrawoRuch(ActionEvent actionEvent)
    {
        numerKsiazki++;
        numberOfTries = 0;

        /**
         * zrobione do sprawdzania numeru ksiazki i poprawnego dzialania
         */
        System.out.println(numerKsiazki);

        txt1.setText(ksiazki.get(numerKsiazki).tutul);
        txt2.setText(ksiazki.get(numerKsiazki).IBSBN);
        txt3.setText(ksiazki.get(numerKsiazki).autor);
        txt4.setText(ksiazki.get(numerKsiazki).rokWydania);
        txt5.setText(ksiazki.get(numerKsiazki).wydawnictwo);
        txt6.setText(ksiazki.get(numerKsiazki).opis);
    }

    public void btnDodajAdd(ActionEvent actionEvent)
    {
        /**
         * zrobione do sprawdzania numeru ksiazki i poprawnego dzialania
         */
        System.out.println(ksiazki.size());

        Stream.of( txt1, txt2, txt3, txt4, txt5, txt6).forEach(txt -> {
            txt.setDisable(false);
        });

        Stream.of( txt1, txt2, txt3, txt4, txt5, txt6).forEach(txt -> {
            txt.setText("");
        });

        Stream.of(btnDodaj, btnUsun, btnLewo, btnPrawo, btnZamien).forEach(btn -> {
            btn.setDisable(true);
        });

        Stream.of(btnZapiszSave, btnAnuluj).forEach(btn -> {
            btn.setDisable(false);
        });

    }

    public void btnAnulujGoBack(ActionEvent actionEvent) throws IOException
    {

        /**
         * zrobione do sprawdzania numeru ksiazki i poprawnego dzialania
         */
        System.out.println(ksiazki.size());

        resetButtons();
    }

    public void btnZapisz(ActionEvent actionEvent) throws IOException {

        /**
         * zrobione do sprawdzania numeru ksiazki i poprawnego dzialania
         */
        System.out.println(ksiazki.size());

        Rozdzielacz rozdzielacz2 = new Rozdzielacz(txt1.getText(), txt2.getText(), txt3.getText(), txt4.getText(), txt5.getText(), txt6.getText());
        ksiazki.add(rozdzielacz2);

        String tekstDoZapisania = " ";
        for ( Rozdzielacz rozdzielacz : ksiazki)
        {
            tekstDoZapisania += rozdzielacz.tutul + "#" + rozdzielacz.IBSBN + "#" + rozdzielacz.autor + "#" + rozdzielacz.rokWydania + "#" + rozdzielacz.wydawnictwo + "#" + rozdzielacz.opis + "\n";
        }
        Path pathZapis = Paths.get("C:/Users/Bob/Desktop/Biblioteka.txt");
        Files.write(pathZapis, tekstDoZapisania.getBytes());

        resetButtons();
    }


    public void btnUsunDelete(ActionEvent actionEvent) throws IOException {
        /**
         * zrobione do sprawdzania numeru ksiazki i poprawnego dzialania
         */
        System.out.println(ksiazki.size());


        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Zapytanie");
        alert.setHeaderText("Czy napewno chcesz usunać? \naby usunać kliknij jeszcze raz \naby anulować klinknij anuluj");
        alert.showAndWait();


        numberOfTries++;

        if (numberOfTries == 2) {

            ksiazki.remove(numerKsiazki - 1);
        }

        resetButtons();
        resetBooks();
    }
    public void resetButtons()
    {
        Stream.of(btnZapiszSave, btnAnuluj).forEach(btn -> {
            btn.setDisable(true);
        });

        Stream.of(btnDodaj, btnUsun, btnLewo, btnPrawo, btnZamien).forEach(btn -> {
            btn.setDisable(false);
        });

        Stream.of( txt1, txt2, txt3, txt4, txt5, txt6).forEach(txt -> {
            txt.setDisable(true);
        });

        txt1.setText(ksiazki.get(numerKsiazki).tutul);
        txt2.setText(ksiazki.get(numerKsiazki).IBSBN);
        txt3.setText(ksiazki.get(numerKsiazki).autor);
        txt4.setText(ksiazki.get(numerKsiazki).rokWydania);
        txt5.setText(ksiazki.get(numerKsiazki).wydawnictwo);
        txt6.setText(ksiazki.get(numerKsiazki).opis);
    }

    public void resetBooks() throws IOException {
        String tekstDoZapisania = " ";
        for ( Rozdzielacz rozdzielacz : ksiazki)
        {
            tekstDoZapisania += rozdzielacz.tutul + "#" + rozdzielacz.IBSBN + "#" + rozdzielacz.autor + "#" + rozdzielacz.rokWydania + "#" + rozdzielacz.wydawnictwo + "#" + rozdzielacz.opis + "\n";
        }
        Path pathZapis = Paths.get("C:/Users/Bob/Desktop/Biblioteka.txt");
        Files.write(pathZapis, tekstDoZapisania.getBytes());

        resetButtons();
    }

}
