package com.example.biblioteka;

import javafx.scene.control.Button;
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
    public Button btnPrawo;
    public TextField txt1, txt2, txt3, txt4, txt5, txt6;

    public void initialize() throws IOException {
        Path path = Paths.get("C://biblioteka.txt");
        if (!Files.exists(path))
            Files.createFile(path);

        List<String> linie = Files.readAllLines(path);
        List<Object> ksiazki = new ArrayList<>();

        for (String linia : linie )
        {
            String[] parts = linia.split("#");
            Rozdzielacz rozdzielacz = new Rozdzielacz(parts[1], parts[2], parts[3], parts[4], parts[5], parts[6]);
            ksiazki.add(rozdzielacz);
        }
    /*
        String tesktDoZapisania = "";
        for (Object rozdzielacz : ksiazki)
        {
            tesktDoZapisania += rozdzielacz.tytuł
        }
    */

    }

}
