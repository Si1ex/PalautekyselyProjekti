package com.example.harjoitustyo;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.*;

/**
 * Luokka luo kurssin palautekyselyn usemmalle sivulle.
 * @author Daniel Kurhinen
 */
public class Kysely extends Application implements Serializable {
    /**
     * Luo JavaFX ikkunan.
     */
    private Stage STAGE;
    /**
     * Ensimmäinen ikkuna.
     */
    private Scene SCENE1;
    /**
     * Toinen ikkuna.
     */
    private Scene SCENE2;
    /**
     * Kolmas ikkuna.
     */
    private Scene SCENE3;
    /**
     * Totuusarvomuuttuja.
     */
    private boolean SET = false;
    /**
     * Kenttä auttaa päivittämään valintalistan valinnan.
     */
    private String TUNTEJA;
    /**
     * Kenttää käytetään tiedoston lukemisessa.
     */
    private String LUKIJA;

    /**
     * Alustaja.
     */
    public Kysely() {
    }
    /**
     * Metodi liittää kolme ikkunaa niille kuuluviin metodeihin sekä määrittelee käynnistyksen.
     */
    public void start(Stage paaIkkuna)  {
        STAGE = paaIkkuna;
        SCENE1 = luodaanEnsimmainenSivu();
        SCENE2 = luodaanToinenSivu();
        SCENE3 = luodaanKolmasSivu();

        STAGE.setTitle("Palautekysely");
        STAGE.setScene(SCENE1);
        STAGE.show();
    }
    /**
     * Metodi ensimmäisen ikkunan luomista varten.
     * @return scene1
     */
    private Scene luodaanEnsimmainenSivu() {
        GridPane paneeli = new GridPane();  //Luodaan paneeli ensimmäiselle sivulle ja määritellään se
        paneeli.setMinSize(200,200);
        paneeli.setAlignment(Pos.TOP_CENTER);
        paneeli.setHgap(10);
        paneeli.setVgap(20);

        Label tervetuloa = new Label("Palautekysely 2022");     //Otsikko ja sen määrittely.
        GridPane.setHalignment(tervetuloa, HPos.CENTER);
        GridPane.setValignment(tervetuloa, VPos.CENTER);
        tervetuloa.setFont(Font.font("Calibri",FontWeight.BOLD, 20));
        paneeli.getChildren().add(tervetuloa);

        Label taustatiedot = new Label("Taustatiedot");
        paneeli.add(taustatiedot, 0,1);

        Label olen = new Label("1. Olen: ");
        paneeli.add(olen, 0,2);
        olen.setFont(Font.font("Calibri",FontWeight.BOLD ,17));

        VBox paneeliPainikkeille = new VBox(20);    //Paneeli RadioButton painikkeille.
        paneeliPainikkeille.setPadding(new Insets(5,5,5,5));
        RadioButton tktOpiskelija = new RadioButton("Tietojenkäsittelytieteen opiskelija");
        RadioButton sivuaineOpiskelija = new RadioButton("Sivuaine opiskelija");
        RadioButton avoimenOpiskelija = new RadioButton("Avoimen yliopiston opiskelija");
        paneeliPainikkeille.getChildren().addAll(tktOpiskelija,sivuaineOpiskelija,avoimenOpiskelija);
        paneeli.add(paneeliPainikkeille, 0, 3);     //Lisätään näppäimien paneeli ikkunan paneeliin.

        ToggleGroup ryhma1 = new ToggleGroup();     //Määritellään, että voi valita vain yhden vaihtoehdon.
        tktOpiskelija.setToggleGroup(ryhma1);
        sivuaineOpiskelija.setToggleGroup(ryhma1);
        avoimenOpiskelija.setToggleGroup(ryhma1);

        //RadioButton muuttuu harmaaksi kun hiiri on sen päällä seuraavalla koodipätkällä.

        tktOpiskelija.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> tktOpiskelija.setStyle("-fx-background-color: lightgrey"));
        tktOpiskelija.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> tktOpiskelija.setStyle(null));

        sivuaineOpiskelija.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> sivuaineOpiskelija.setStyle("-fx-background-color: lightgrey"));
        sivuaineOpiskelija.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> sivuaineOpiskelija.setStyle(null));

        avoimenOpiskelija.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> avoimenOpiskelija.setStyle("-fx-background-color: lightgrey"));
        avoimenOpiskelija.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> avoimenOpiskelija.setStyle(null));

        Label harjoitusryhma = new Label("2. Mikä on harjoitusryhmäsi");    //Kysymys
        paneeli.add(harjoitusryhma, 0, 4);
        harjoitusryhma.setFont(Font.font("Calibri", FontWeight.BOLD ,17));

        VBox harjoitusryhmaPaneeli = new VBox(20);      //Paneeli RadioButton painikkelle.
        harjoitusryhmaPaneeli.setPadding(new Insets(5,5,5,5));
        RadioButton JoensuuR1 = new RadioButton("Joensuun ryhmä 1");
        RadioButton JoensuuR2 = new RadioButton("Joensuun ryhmä 2");
        RadioButton JoensuuR3 = new RadioButton("Joensuun ryhmä 3");
        RadioButton JoensuuR4 = new RadioButton("Joensuun ryhmä 4");
        RadioButton KuopioR1 = new RadioButton("Kuopion ryhmä 1");
        RadioButton KuopioR2 = new RadioButton("Kuopion ryhmä 2");
        RadioButton KuopioR3 = new RadioButton("Kuopion ryhmä 3");
        RadioButton KuopioR4 = new RadioButton("Kuopion ryhmä 4");
        harjoitusryhmaPaneeli.getChildren().addAll(JoensuuR1,JoensuuR2,JoensuuR3,JoensuuR4,KuopioR1,KuopioR2,KuopioR3,KuopioR4);
        paneeli.add(harjoitusryhmaPaneeli,0,5);     //Lisätään näppäimien paneeli ikkunan paneeliin.

        ToggleGroup ryhma2 = new ToggleGroup();    //Määritellään, että voi valita vain yhden vaihtoehdon.
        JoensuuR1.setToggleGroup(ryhma2);
        JoensuuR2.setToggleGroup(ryhma2);
        JoensuuR3.setToggleGroup(ryhma2);
        JoensuuR4.setToggleGroup(ryhma2);
        KuopioR1.setToggleGroup(ryhma2);
        KuopioR2.setToggleGroup(ryhma2);
        KuopioR3.setToggleGroup(ryhma2);
        KuopioR4.setToggleGroup(ryhma2);

        //RadioButton muuttuu harmaaksi kun hiiri on sen päällä seuraavalla koodipätkällä.

        JoensuuR1.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> JoensuuR1.setStyle("-fx-background-color: lightgrey"));
        JoensuuR1.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> JoensuuR1.setStyle(null));

        JoensuuR2.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> JoensuuR2.setStyle("-fx-background-color: lightgrey"));
        JoensuuR2.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> JoensuuR2.setStyle(null));

        JoensuuR3.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> JoensuuR3.setStyle("-fx-background-color: lightgrey"));
        JoensuuR3.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> JoensuuR3.setStyle(null));

        JoensuuR4.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> JoensuuR4.setStyle("-fx-background-color: lightgrey"));
        JoensuuR4.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> JoensuuR4.setStyle(null));

        KuopioR1.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> KuopioR1.setStyle("-fx-background-color: lightgrey"));
        KuopioR1.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> KuopioR1.setStyle(null));

        KuopioR2.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> KuopioR2.setStyle("-fx-background-color: lightgrey"));
        KuopioR2.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> KuopioR2.setStyle(null));

        KuopioR3.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> KuopioR3.setStyle("-fx-background-color: lightgrey"));
        KuopioR3.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> KuopioR3.setStyle(null));

        KuopioR4.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> KuopioR4.setStyle("-fx-background-color: lightgrey"));
        KuopioR4.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> KuopioR4.setStyle(null));

        Button vaihdaTeema = new Button("Vaihda tummaan teemaan");  //Näppäin teeman vaihtamiseen.
        vaihdaTeema.setOnMouseClicked(e -> {
            SET = ! SET;
            if (e.getButton() == MouseButton.PRIMARY) {
                vaihdaTeema.setText(SET? "Vaihda vaaleaan teemaan" : "Vaihda tummaan teemaan");
                paneeli.setStyle(SET? "-fx-base: rgba(60, 60, 60, 255);" : "-fx-background-color: null");
            }
        });
        paneeli.add(vaihdaTeema,1,1);

        Button nappainSEURAAVA = new Button("Seuraava");    //Näppäin seuraavalle sivulle siirtymistä varten.
        nappainSEURAAVA.setOnAction(e -> switchScenes(SCENE2));
        paneeli.add(nappainSEURAAVA,0,6);

        SCENE1 = new Scene(paneeli, 500,650);
        return SCENE1;
    }
    /**
     * Metodi toisen ikkunan luomista varten.
     * @return scene2
     */
    private Scene luodaanToinenSivu() {     //Toisen sivun asettelu, toiminnallisuudet ja määrittely.
        GridPane paneeli2 = new GridPane();     //Luodaan paneeli toiselle sivulle ja määritellään se.
        paneeli2.setMinSize(200,200);
        paneeli2.setAlignment(Pos.TOP_CENTER);
        paneeli2.setHgap(10);
        paneeli2.setVgap(20);

        Label tervetuloa2 = new Label("Palautekysely 2022");    //Otsikko
        GridPane.setHalignment(tervetuloa2, HPos.CENTER);
        GridPane.setValignment(tervetuloa2, VPos.CENTER);
        tervetuloa2.setFont(Font.font("Calibri",FontWeight.BOLD, 20));
        paneeli2.getChildren().add(tervetuloa2);

        Label kurssillaopiskelu = new Label("Kurssilla opiskelu");
        paneeli2.add(kurssillaopiskelu, 0,1);

        Label opiskelumateriaalia = new Label("3. Kävin läpi opiskelumateriaalia");
        paneeli2.add(opiskelumateriaalia, 0, 2);
        opiskelumateriaalia.setFont(Font.font("Calibri", FontWeight.BOLD ,17));

        GridPane paneeliRadioButtoneille = new GridPane();  //Paneeli näppäimien asettelua varten.
        paneeliRadioButtoneille.setPadding(new Insets(10,10,10,10));
        paneeliRadioButtoneille.setHgap(10);
        paneeliRadioButtoneille.setVgap(10);

        Label lahesKaikki = new Label("lähes\nkaikki");
        paneeliRadioButtoneille.add(lahesKaikki, 3,0);

        Label suurimmanOsan = new Label("suurimman\nosan");
        paneeliRadioButtoneille.add(suurimmanOsan, 4,0);

        Label noinPuolet = new Label("noin\npuolet");
        paneeliRadioButtoneille.add(noinPuolet, 5,0);

        Label jonkinVerran = new Label("jonkin\nverran");
        paneeliRadioButtoneille.add(jonkinVerran, 6,0);

        Label vainVahan = new Label("vain vähän tai en\nlainkaan");
        paneeliRadioButtoneille.add(vainVahan, 7,0);

        Label luentodiosta = new Label("Luentodioista");
        paneeliRadioButtoneille.add(luentodiosta, 0,1);     //Lisätään näppäimien paneeli ikkunan paneeliin.

        RadioButton b1 = new RadioButton();
        paneeliRadioButtoneille.add(b1, 3,1);
        b1.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b1.setStyle("-fx-background-color: lightgrey"));
        b1.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b1.setStyle(null));

        RadioButton b2 = new RadioButton();
        paneeliRadioButtoneille.add(b2, 4,1);
        b2.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b2.setStyle("-fx-background-color: lightgrey"));
        b2.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b2.setStyle(null));

        RadioButton b3 = new RadioButton();
        paneeliRadioButtoneille.add(b3, 5,1);
        b3.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b3.setStyle("-fx-background-color: lightgrey"));
        b3.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b3.setStyle(null));

        RadioButton b4 = new RadioButton();
        paneeliRadioButtoneille.add(b4, 6,1);
        b4.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b4.setStyle("-fx-background-color: lightgrey"));
        b4.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b4.setStyle(null));

        RadioButton b5 = new RadioButton();
        paneeliRadioButtoneille.add(b5, 7,1);
        b5.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b5.setStyle("-fx-background-color: lightgrey"));
        b5.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b5.setStyle(null));

        ToggleGroup ryhma3 = new ToggleGroup();     //Määritellään, että voi valita vain yhden vaihtoehdon.
        b5.setToggleGroup(ryhma3);
        b4.setToggleGroup(ryhma3);
        b3.setToggleGroup(ryhma3);
        b2.setToggleGroup(ryhma3);
        b1.setToggleGroup(ryhma3);

        Label videotallenteista = new Label("Videotallenteista");
        paneeliRadioButtoneille.add(videotallenteista, 0,2);

        RadioButton b6 = new RadioButton();
        paneeliRadioButtoneille.add(b6, 3,2);
        b6.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b6.setStyle("-fx-background-color: lightgrey"));
        b6.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b6.setStyle(null));

        RadioButton b7 = new RadioButton();
        paneeliRadioButtoneille.add(b7, 4,2);
        b7.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b7.setStyle("-fx-background-color: lightgrey"));
        b7.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b7.setStyle(null));

        RadioButton b8 = new RadioButton();
        paneeliRadioButtoneille.add(b8, 5,2);
        b8.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b8.setStyle("-fx-background-color: lightgrey"));
        b8.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b8.setStyle(null));

        RadioButton b9 = new RadioButton();
        paneeliRadioButtoneille.add(b9, 6,2);
        b9.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b9.setStyle("-fx-background-color: lightgrey"));
        b9.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b9.setStyle(null));

        RadioButton b10 = new RadioButton();
        paneeliRadioButtoneille.add(b10, 7,2);
        b10.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b10.setStyle("-fx-background-color: lightgrey"));
        b10.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b10.setStyle(null));

        ToggleGroup ryhma4 = new ToggleGroup();     //Määritellään, että voi valita vain yhden vaihtoehdon.
        b6.setToggleGroup(ryhma4);
        b7.setToggleGroup(ryhma4);
        b8.setToggleGroup(ryhma4);
        b9.setToggleGroup(ryhma4);
        b10.setToggleGroup(ryhma4);

        Label oppikirjasta = new Label("Oppikirjasta");
        paneeliRadioButtoneille.add(oppikirjasta, 0,3);

        RadioButton b11 = new RadioButton();
        paneeliRadioButtoneille.add(b11, 3,3);
        b11.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b11.setStyle("-fx-background-color: lightgrey"));
        b11.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b11.setStyle(null));

        RadioButton b12 = new RadioButton();
        paneeliRadioButtoneille.add(b12, 4,3);
        b12.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b12.setStyle("-fx-background-color: lightgrey"));
        b12.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b12.setStyle(null));

        RadioButton b13 = new RadioButton();
        paneeliRadioButtoneille.add(b13, 5,3);
        b13.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b13.setStyle("-fx-background-color: lightgrey"));
        b13.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b13.setStyle(null));

        RadioButton b14 = new RadioButton();
        paneeliRadioButtoneille.add(b14, 6,3);
        b14.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b14.setStyle("-fx-background-color: lightgrey"));
        b14.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b14.setStyle(null));

        RadioButton b15 = new RadioButton();
        paneeliRadioButtoneille.add(b15, 7,3);
        b15.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b15.setStyle("-fx-background-color: lightgrey"));
        b15.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b15.setStyle(null));

        ToggleGroup ryhma5 = new ToggleGroup();     //Määritellään, että voi valita vain yhden vaihtoehdon.
        b11.setToggleGroup(ryhma5);
        b12.setToggleGroup(ryhma5);
        b13.setToggleGroup(ryhma5);
        b14.setToggleGroup(ryhma5);
        b15.setToggleGroup(ryhma5);

        Label nettilinkeistaOheismateriaaliin = new Label("Nettilinkeistä oheismateriaaliin");
        paneeliRadioButtoneille.add(nettilinkeistaOheismateriaaliin, 0,4);

        RadioButton b16 = new RadioButton();
        paneeliRadioButtoneille.add(b16, 3,4);
        b16.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b16.setStyle("-fx-background-color: lightgrey"));
        b16.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b16.setStyle(null));

        RadioButton b17 = new RadioButton();
        paneeliRadioButtoneille.add(b17, 4,4);
        b17.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b17.setStyle("-fx-background-color: lightgrey"));
        b17.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b17.setStyle(null));

        RadioButton b18 = new RadioButton();
        paneeliRadioButtoneille.add(b18, 5,4);
        b18.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b18.setStyle("-fx-background-color: lightgrey"));
        b18.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b18.setStyle(null));

        RadioButton b19 = new RadioButton();
        paneeliRadioButtoneille.add(b19, 6,4);
        b19.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b19.setStyle("-fx-background-color: lightgrey"));
        b19.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b19.setStyle(null));

        RadioButton b20 = new RadioButton();
        paneeliRadioButtoneille.add(b20, 7,4);
        b20.addEventHandler(MouseEvent.MOUSE_ENTERED, mouseEvent -> b20.setStyle("-fx-background-color: lightgrey"));
        b20.addEventHandler(MouseEvent.MOUSE_EXITED, mouseEvent -> b20.setStyle(null));

        paneeli2.add(paneeliRadioButtoneille, 0, 3);    //Lisätään näppäimien paneeli ikkunan paneeliin.

        ToggleGroup ryhma6 = new ToggleGroup();     //Määritellään, että voi valita vain yhden vaihtoehdon.
        b16.setToggleGroup(ryhma6);
        b17.setToggleGroup(ryhma6);
        b18.setToggleGroup(ryhma6);
        b19.setToggleGroup(ryhma6);
        b20.setToggleGroup(ryhma6);

        Label tuntimaara = new Label("4. Käytin kurssin opiskeluun viikottain keskimäärin: ");
        paneeli2.add(tuntimaara, 0,4);      //Lisätään otsikko ikkunan paneeliin.
        tuntimaara.setFont(Font.font("Calibri", FontWeight.BOLD ,17));

        String[] alkiot = {"Alle 2 tuntia","2-5 tuntia","5-8 tuntia","8-10 tuntia","10-12 tuntia","12-15 tuntia","15-20 tuntia",
                "20-25 tuntia", "yli 30 tuntia"};   //Valinnat listaa varten.

        ListView<String> lv = new ListView<>(FXCollections.observableArrayList(alkiot));    //Valintalistan luominen.

        lv.setPrefSize(50,150);     //Valintalistan koko.

        lv.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);      //Sallitaan vain yhden valinnan tekeminen.

        VBox valitut = new VBox();      //Luodaan paneeli, johon kirjataan tuntien määrä.
        Text text = new Text(" ");
        text.setFont(Font.font("Calibri", 16));
        valitut.getChildren().add(text);
        paneeli2.add(valitut, 1,4);

        lv.getSelectionModel().selectedItemProperty().addListener(ov -> {       //Päivitetään paneeliin käyttäjän valinta.
                    TUNTEJA = " ";
                    for (Integer i : lv.getSelectionModel().getSelectedIndices())
                        TUNTEJA += alkiot[i] + " ";
                    text.setText(TUNTEJA);
                }
        );
        paneeli2.add(lv,0,5);       //Lisätään käyttäjän valinnan päivittyminen paneeliin.

        Button vaihdaTeema2 = new Button("Vaihda tummaan teemaan");     //Näppäin teeman vaihtamiseen.
        vaihdaTeema2.setOnMouseClicked(e -> {
            SET = ! SET;
            if (e.getButton() == MouseButton.PRIMARY) {
                vaihdaTeema2.setText(SET? "Vaihda vaaleaan teemaan" : "Vaihda tummaan teemaan");
                paneeli2.setStyle(SET? "-fx-base: rgba(60, 60, 60, 255);" : "-fx-background-color: null");
            }
        });
        paneeli2.add(vaihdaTeema2,1,1);

        Button nappainSEURAAVA = new Button("Seuraava");
        nappainSEURAAVA.setOnAction(e -> switchScenes(SCENE3));
        paneeli2.add(nappainSEURAAVA, 1,9);

        Button nappainEDELLINEN = new Button("Edellinen");
        nappainEDELLINEN.setOnAction(e -> switchScenes(SCENE1));
        paneeli2.add(nappainEDELLINEN, 0, 9);

        SCENE2 = new Scene(paneeli2, 700,650);
        return SCENE2;
    }

    /**
     * Metodi kolmannen ikkunan luomista varten.
     * @return scene3
     */
    private Scene luodaanKolmasSivu() {     //Kolmannen sivun asettelu, toiminnallisuudet ja määrittely.
        GridPane paneeli3 = new GridPane();     //Luodaan paneeli kolmannelle sivulle ja määritellään se.
        paneeli3.setMinSize(200,200);
        paneeli3.setAlignment(Pos.TOP_CENTER);
        paneeli3.setHgap(10);
        paneeli3.setVgap(20);

        Label tervetuloa3 = new Label("Palautekysely 2022");    //Otsikko
        GridPane.setHalignment(tervetuloa3, HPos.CENTER);
        GridPane.setValignment(tervetuloa3, VPos.CENTER);
        tervetuloa3.setFont(Font.font("Calibri",FontWeight.BOLD, 20));
        paneeli3.getChildren().add(tervetuloa3);

        Button vaihdaTeema3 = new Button("Vaihda tummaan teemaan");     //Näppäin teeman vaihtamiseen.
        vaihdaTeema3.setOnMouseClicked(e -> {
            SET = ! SET;
            if (e.getButton() == MouseButton.PRIMARY) {
                vaihdaTeema3.setText(SET? "Vaihda vaaleaan teemaan" : "Vaihda tummaan teemaan");
                paneeli3.setStyle(SET? "-fx-base: rgba(60, 60, 60, 255);" : "-fx-background-color: null");
            }
        });
        paneeli3.add(vaihdaTeema3,1,1);     //Lisätään teeman vaihtamisen näppäin ikkunaan.

        Label opiskelutehtavista = new Label("5. Minkä arvosanan antaisit itsellesi kurssista?");
        paneeli3.add(opiskelutehtavista, 0, 2);
        opiskelutehtavista.setFont(Font.font("Calibri", FontWeight.BOLD ,17));

        ComboBox<Object> arvosanalista = new ComboBox<>();      //Lista arvosanan valintaa varten.
        arvosanalista.getItems().add("0");
        arvosanalista.getItems().add("1");
        arvosanalista.getItems().add("2");
        arvosanalista.getItems().add("3");
        arvosanalista.getItems().add("4");
        arvosanalista.getItems().add("5");
        paneeli3.add(arvosanalista,0,3);

        Label kirjallinenPalaute = new Label("6. Perustele arvosanasi");
        paneeli3.add(kirjallinenPalaute, 0, 4);
        kirjallinenPalaute.setFont(Font.font("Calibri", FontWeight.BOLD ,17));

        TextField palauteLaatikko = new TextField();        //Tekstikenttä palautteen kirjoittamista varten.
        palauteLaatikko.setPrefHeight(100);
        palauteLaatikko.setMaxWidth(250);
        paneeli3.add(palauteLaatikko,0,5);

        Button tallennaPalaute = new Button("Tallenna");    //Tallennna-painike
        paneeli3.add(tallennaPalaute,0,6);

        tallennaPalaute.setOnMouseClicked(e -> {        //Näppäin kirjaa käyttäjän tekstin sarjallistettuna tiedostoon.
            try {
                FileOutputStream tiedosto;
                tiedosto = new FileOutputStream("KirjallinenPalaute.dat");
                ObjectOutputStream olio = new ObjectOutputStream(tiedosto);
                olio.writeObject(palauteLaatikko.getText());
                System.out.println("Tallennettu");
                tiedosto.close();

                Label tallennettu = new Label("Tallennettu!"); //Jos kirjaaminen onnistuu, tekstikentän viereen ilmestyy "Tallennettu!"-teksti.
                paneeli3.add(tallennettu,1,5);
                tallennettu.setFont(Font.font("Calibri", FontWeight.BOLD ,15));
            }
            catch (Exception exception){
                System.out.println("Tapahtui virhe. Kokeile uudestaan.");
            }

            try {       //Luetaan käyttäjän teksti tiedostosta.
                FileReader tiedosto = new FileReader("KirjallinenPalaute.dat");
                BufferedReader olio = new BufferedReader(tiedosto);
                LUKIJA = olio.readLine();
                System.out.println(LUKIJA);
            } catch (FileNotFoundException d) {
                System.out.println("Tiedostoa ei löytynyt");
            } catch (EOFException ex) {
                System.out.println("Tiedosto päättynyt");
            } catch (IOException ex) {
                System.out.println("Tiedostoa lukiessa tapahtui jokin muu virhe");
            }
        });

        Button nappainLAHETA = new Button("Lähetä");        //Näppäin avaa uuden ikkunan, joka ilmoittaa, että lomake on lähetetty.
        nappainLAHETA.setOnAction(actionEvent -> {       //Tapahtumankäsittelijä.
            BorderPane ikkunanPaneeli = new BorderPane();
            Label lomakeLahetetty = new Label("Lomake on lähetetty!");
            ikkunanPaneeli.setTop(lomakeLahetetty);
            BorderPane.setAlignment(lomakeLahetetty, Pos.TOP_CENTER);
            lomakeLahetetty.setFont(Font.font("Calibri",16));

            Button OK = new Button("OK");       //Kun painaa "OK"-painiketta, ohjelma sulkeutuu.
            ikkunanPaneeli.setCenter(OK);
            OK.setOnAction(e -> Platform.exit());

            Stage uusiIkkuna = new Stage();     //Määritellään uusi ikkuna.
            uusiIkkuna.setTitle("Palautekysely");
            Scene scene = new Scene(ikkunanPaneeli, 290,170);
            uusiIkkuna.setScene(scene);
            uusiIkkuna.show();
        });
        paneeli3.add(nappainLAHETA, 1,12);

        Button nappainEDELLINEN = new Button("Edellinen");
        nappainEDELLINEN.setOnAction(e -> switchScenes(SCENE2));
        paneeli3.add(nappainEDELLINEN, 0, 12);

        SCENE3 = new Scene(paneeli3, 600,650);
        return SCENE3;
    }

    /**
     * Metodi määrittelee siirtymisen ikkunoiden välillä.
     */
    public void switchScenes(Scene scene) {
        STAGE.setScene(scene);
    }

    /**
     * Pääohjelma käynnistää ohjelman.
     */
    public static void main(String[] args) {
        Application.launch();
    }
}
