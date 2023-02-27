import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.showMessageDialog;
import javax.swing.JFrame;

/**
 *
 * Beschreibung
 *
 * @version 1.0 vom 09.03.2010
 * @author Hans-Christian Müller
 */

public class Spiel extends JFrame {
  // Anfang Attribute
  private JButton jButton1 = new JButton();
  private JButton jButtonNorth = new JButton("Norden");
  private JButton jButtonSouth = new JButton("Süden");
  private JButton jButtonWest = new JButton("Westen");
  private JButton jButtonOst = new JButton("Osten");
  private JButton jButtonSuch = new JButton("Such");
  private JTextField jTextField1 = new JTextField();
  private JTextArea jTextArea1 = new JTextArea("");

  JLabel bildLabel = new JLabel();
  
  Raum aktuellerRaum;
  Inventory inventory = new Inventory();
  boolean isMessageShown = false;
  boolean seenSecretEntrance = false;
  boolean startFight = false;
  boolean attackiert = false;
  boolean verteidigt = true;
  boolean finishFight = false;
  Spieler Spieler;
  Gegner Anubi;
  Kampf Kampf;
  


  Raum outside, entrance, kitchen, salon, ovenEntrance, firstCorridor, room1, room2, staircase, StaircaseUpperpart, HKoffice, upperCorridor, room3, room4, secretentrance, underCorridor, libEntrance, fightRoom, secretroom, doorRoom1, doorRoom2, doorRoom3, doorRoom4;
  // Ende Attribute

  public Spiel(String title) {
    // Frame-Initialisierung
    super(title);
    setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    int frameWidth = 700;
    int frameHeight = 300;
    setSize(frameWidth, frameHeight);
    Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    int x = (d.width - getSize().width) / 2;
    int y = (d.height - getSize().height) / 2;
    setLocation(x, y);
    Container cp = getContentPane();
    cp.setLayout(null);
    // Anfang Komponenten

    jButton1.setBounds(256, 216, 75, 25);
    jButton1.setText("Go!");
    jButton1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
        jButton1_ActionPerformed(evt);
      }
    });
    jButton1.setFont(new Font("Tahoma", Font.BOLD, 11));
    cp.add(jButton1);

    // nord button
    jButtonNorth.setBounds(140, 195, 80, 30);
    jButtonNorth.setVisible(false);
    jButtonNorth.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
           jTextField1.setText("Norden");
           jButton1_ActionPerformed(evt);
      }
      });
    jButtonNorth.setFont(new Font("Tahoma", Font.BOLD, 10));
    cp.add(jButtonNorth);

    // Süd botton
   jButtonSouth.setBounds(140, 230, 80, 30);
   jButtonSouth.setVisible(false);
   jButtonSouth.addActionListener(new ActionListener() {
     public void actionPerformed(ActionEvent evt) {
         jTextField1.setText("Süden");
         jButton1_ActionPerformed(evt);
      }
      });
    jButtonSouth.setFont(new Font("Tahoma", Font.BOLD, 10));
    cp.add(jButtonSouth);

    // west button
    jButtonWest.setBounds(55, 213, 80, 30);
    jButtonWest.setVisible(false);
    jButtonWest.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
           jTextField1.setText("Westen");
           jButton1_ActionPerformed(evt);
      }
      });
    jButtonWest.setFont(new Font("Tahoma", Font.BOLD, 10));
    cp.add(jButtonWest);

    // ost button 
    jButtonOst.setBounds(225, 213, 80, 30);
    jButtonOst.setVisible(false);
    jButtonOst.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
           jTextField1.setText("Osten");
           jButton1_ActionPerformed(evt);
      }
      });
    jButtonOst.setFont(new Font("Tahoma", Font.BOLD, 10));
    cp.add(jButtonOst);

    // such button
    jButtonSuch.setBounds(230, 187, 65, 25);
    jButtonSuch.setVisible(false);
    jButtonSuch.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent evt) {
           jTextField1.setText("Suchen");
           jButton1_ActionPerformed(evt);
      }
      });
    jButtonSuch.setFont(new Font("Tahoma", Font.BOLD, 10));
    cp.add(jButtonSuch);
    
    // textfield
    jTextField1.setBounds(24, 216, 169, 21);
    jTextField1.setVisible(false);
    jTextField1.setText("");
    jTextField1.setFont(new Font("Tahoma", Font.PLAIN, 11));
    cp.add(jTextField1);
    jTextArea1.setBounds(24, 16, 305, 170);
    jTextArea1.setText("");
    jTextArea1.setFont(new Font("Tahoma", Font.PLAIN, 11));
    cp.add(jTextArea1);


    jButton1.setDefaultCapable(true);
    getRootPane().setDefaultButton(jButton1);
    // Ende Komponenten

    setResizable(false);
    setVisible(true);

    
    
     
    // --- Initialisierung der Räume ---
    
    outside = new Raum("Du stehst vor dem Haus.", "Draußen", "Bilder/HEntrance.png");

    entrance = new Raum("Du hast den Eingang betreten.", "Eingang", "Bilder/entryway.jpg");

    salon = new Raum("Du befindest dich im Hauptsalon des Hauses, du bist im Wohnzimmer.", "Wohnzimmer", "Bilder/LivingA.jpg");

    kitchen = new Raum("Du befindest dich jetzt in der Küche...", "Küche", "Bilder/kitchen.png");

    ovenEntrance = new Raum("Dies ist ein geheimer Eingang, der zuvor nicht existierte. Versuch weiterzugehen...", "Geheimer Eingang", "Bilder/ovenENT.png");

    firstCorridor = new Raum("Dies ist der Flur im Erdgeschoss.", "Flur", "Bilder/FFlor.png");

    room1 = new Raum("Du bist im Raum nr. 100", "Raum 100", "Bilder/100.jpg");

    doorRoom1 = new Raum("Du stehst vor der Tür von Raum nr. 100, aber du hast die Schlüssel nicht.", "Tür von Raum nr. 100", "Bilder/door.jpg");

    room2 = new Raum("Du bist im Raum nr. 101", "Raum 101", "Bilder/100.jpg");

    doorRoom2 = new Raum("Du stehst vor der Tür von Raum nr. 101, aber du hast die Schlüssel nicht.", "Tür von Raum nr. 101", "Bilder/door.jpg");

    staircase = new Raum("Du befindest dich auf der Treppe.", "Treppen", "Bilder/Staircase.jpg");

    StaircaseUpperpart = new Raum("Jetzt befindest du dich auf dem Treppenabsatz.", "Treppenabsatz", "Bilder/landing.jpg");

    HKoffice = new Raum("Du befindest dich im Büro des Hausverwalters.", "Büro des Hausverwalters", "Bilder/HKOffice.jpg");

    upperCorridor = new Raum("Du befindest dich im oberen Korridor.", "Oberen Korridor", "Bilder/uppercorridor.png");

    room3 = new Raum("Du bist im Raum nr. 102", "Raum 102", "Bilder/100.jpg");

    doorRoom3 = new Raum("Du stehst vor der Tür von Raum nr. 102.", "Tür von Raum nr. 102", "Bilder/door.jpg");

    room4 = new Raum("Du bist im Raum nr. 103", "Raum 103", "Bilder/100.jpg");

    doorRoom4 = new Raum("Du stehst vor der Tür von Raum nr. 103, aber du hast die Schlüssel nicht.", "Tür von Raum nr. 103", "Bilder/door.jpg");

    secretentrance = new Raum("Du befindest dich in der Untergrundbibliothek.", "Untergrundbibliothek", "Bilder/SecretEntrace.png");

    libEntrance = new Raum("Du stehst vor einer Wand aus Büchern, wo sich eine leere Stelle befindet, die mit etwas gefüllt werden muss. Dies könnte eine Tür sein. Daneben steht geschrieben...", "Library Secret entrance", "Bilder/Riddle1.png");

    underCorridor = new Raum("Du hast die Tür geöffnet, jetzt befindest du dich im geheimen Korridor.", "Geheimen Korridor", "Bilder/UGTunnel.jpg");

    fightRoom = new Raum("Das ist Anubis, er versucht, den Durchgang zur Entdeckung der Geheimnisse zu blockieren. Kämpfe jetzt gegen ihn und überlebe!", "Mistical Room", "Bilder/fight.png");
    
    secretroom = new Raum("Du hast es geschafft, du bist im Geheimzimmer angekommen. Jetzt wirst du erfahren, warum all diese seltsamen Dinge im Haus passierten...", "Geheimzimmer", "Bilder/finals.png");


    aktuellerRaum = outside;

    bildLabel.setBounds(350, 16, 310, 220);
    cp.add(bildLabel); 

    // --- Initialisierung: Spieler / Gegner / Kampf ---

    Spieler = new Spieler("Main", 50, (int)Math.floor(Math.random() * (40 - 5 + 1) + 0), 50);
    Anubi = new Gegner("Anubi", 80, 25, 20);

    Kampf = new Kampf(Spieler, Anubi);

    
    // --- Belegung der Ausgänge ---

    // NORD / OST / SUD / WEST

    outside.setzeAusgaenge(entrance, null, null, null);

    entrance.setzeAusgaenge(firstCorridor, staircase, outside, salon);

    salon.setzeAusgaenge(kitchen, entrance, null, null);

    kitchen.setzeAusgaenge(null, firstCorridor, salon, ovenEntrance);

    firstCorridor.setzeAusgaenge( doorRoom1, doorRoom2, entrance, kitchen);

    room1.setzeAusgaenge( null, null, doorRoom1, null);

    room2.setzeAusgaenge( null, null, null, doorRoom2);

    staircase.setzeAusgaenge( StaircaseUpperpart, null, null, entrance);

    StaircaseUpperpart.setzeAusgaenge( null, upperCorridor, staircase, HKoffice);

    HKoffice.setzeAusgaenge( null, StaircaseUpperpart, null, null);
