package com.example.biblioteka;

import javafx.event.ActionEvent;
import javafx.scene.control.*;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
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
    public Button btnWypozycz;
    public DatePicker dteWypozyczenie;

    public String mail = "";


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
        blockButtons();
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

        blockButtons();
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

        blockButtons();
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

            ksiazki.remove(numerKsiazki);
            resetBooks();
        }

        resetButtons();

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

        //btnWypozycz.setDisable(true);

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

    public void blockButtons()
    {
        if (numerKsiazki <= 0)
        {
            btnLewo.setDisable(true);
        }
        else
        {
           btnLewo.setDisable(false);
        }

        if(numerKsiazki >= ksiazki.size() - 1)
        {
            btnPrawo.setDisable(true);
        }
        else
        {
            btnPrawo.setDisable(false);
        }
    }

    public void btnWypozyczBorrow(ActionEvent actionEvent)
    {
        LocalDate terminWypozyczena = dteWypozyczenie.getValue();
        LocalDate teraz = LocalDate.now();

        Boolean czyTrue = teraz.isBefore(terminWypozyczena);

        if (czyTrue == false)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ERROR");
            alert.setHeaderText("Podaj poprawną datę!");

            alert.showAndWait();
            return;
        }
        /**
        *   else
        *   {
        *   btnWypozycz.setDisable(false);
        *   }
        */

        TextInputDialog dialog = new TextInputDialog("Email");

        dialog.setTitle("Wprowadzanie e-maila");
        dialog.setHeaderText("Podaj mail abyśmy mogli potwierdzić \ntwoje wypożyczenie na e-mail");
        dialog.setContentText("e-mail:");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(name -> {
            mail = name;
        });

        String to = mail;
        String from = "agatka0@buziaczek.pl";
        String password = "Bartek2008";
        Properties properties = System.getProperties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.poczta.onet.pl");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

        Session session = Session.getInstance(properties, new MyAuthenticator(from, password));

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Wypożyczenie");
            message.setText("Witaj,"
                    + "\njest to wiadomość wysyłana aby potwierdzić twoje wypożyczenie ksiażki \n,,"+ksiazki.get(numerKsiazki).tutul+"''. \nPolecamy naszą bibliotekę!");
            Transport.send(message);
            System.out.println("Wiadomość została wysłana!");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }





    }
}
