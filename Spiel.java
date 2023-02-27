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
    upperCorridor.setzeAusgaenge( doorRoom3, null, doorRoom4, StaircaseUpperpart);

    room3.setzeAusgaenge( null, null,  doorRoom3, null);

    room4.setzeAusgaenge( doorRoom4, null, null, null);

    underCorridor.setzeAusgaenge( fightRoom, libEntrance, null, null);

    secretroom.setzeAusgaenge(null, null, fightRoom, null);

    jTextArea1.setLineWrap(true);
    jTextArea1.setWrapStyleWord(true);
    jTextArea1.append("INTRODUCTION: Du bist ein neuer Schüler an einer neuen Schule und hast herausgefunden, dass hier seltsame Dinge vor sich gehen. Gerüchte über die Geschichte der Schule sind im Umlauf, einschließlich Geschichten über eine mächtige ägyptische Königin, die einst auf dem Schulgelände gelebt haben soll. Ein Kamerad erzählt dir von einem Geheimraum in der Schule, der mächtiges Wissen und Geheimnisse enthält, die seit Jahrhunderten bewacht werden. Ihr beginnt nach dem Raum zu suchen, aber die Suche wird durch verborgene Durchgänge, Fallen und Rätsel erschwert.\n");
  }

  // Anfang Methoden
  public void jButton1_ActionPerformed(ActionEvent evt) {

    jTextArea1.setText("");
    String eingabe = jTextField1.getText();
    
    jButtonSuch.setVisible(true);
    jButton1.setVisible(false);
    
    // --- Raum wechseln ---

    inventory.addItem("Raum 102 Schlüssel");

        if (eingabe.equals("Norden") && aktuellerRaum.nord != null) {
            aktuellerRaum = aktuellerRaum.nord;

       } else if (eingabe.equals("Osten") && aktuellerRaum.ost != null) {
            aktuellerRaum = aktuellerRaum.ost;

       } else if (eingabe.equals("Süden") && aktuellerRaum.sud != null) {
            aktuellerRaum = aktuellerRaum.sud;

       } else if (eingabe.equals("Westen") && aktuellerRaum.west != null) {
            aktuellerRaum = aktuellerRaum.west;
       }
    
    
        if (eingabe.equals("Suchen") && aktuellerRaum.name == "Raum 102"){
          inventory.addItem("Ra's Auge Halsband");
          JOptionPane.showMessageDialog(null, "Sehr gut, du hast die erste Quest abgeschlossen. Die zweite Quest besteht darin, zu überprüfen, \nob sich etwas im Haus verändert hat. Ich denke, es gab ein Geräusch in der Küche. \nWarum gehst du nicht nachschauen?!","SECOND TASK", JOptionPane.PLAIN_MESSAGE);
          
        }  
        if (inventory.hasItem("Ra's Auge Halsband")){
          kitchen.setzeAusgaenge(null, firstCorridor, salon, ovenEntrance);
          ovenEntrance.setzeAusgaenge(null, kitchen, null, secretentrance);
          
          
        }else{
          kitchen.setzeAusgaenge(null, firstCorridor, salon, null);
          ovenEntrance.setzeAusgaenge(null, kitchen, null, null);
        }
    
        if (inventory.hasItem("Raum 100 Schlüssel")){
          doorRoom1.setzeAusgaenge(room1, null, firstCorridor, null);
          doorRoom1.beschreibung = "Jetzt, da du den Schlüssel hast, kannst du eintreten.";
        }else{
          doorRoom1.setzeAusgaenge( null, null, firstCorridor, null);
        }

        if (inventory.hasItem("Raum 101 Schlüssel")){
          doorRoom2.setzeAusgaenge( null, room2, null, firstCorridor);
          doorRoom2.beschreibung = "Jetzt, da du den Schlüssel hast, kannst du eintreten.";
        }else{
          doorRoom2.setzeAusgaenge( null, null, null, firstCorridor);
        }

        if (inventory.hasItem("Raum 102 Schlüssel")){
          doorRoom3.setzeAusgaenge( room3, null, upperCorridor, null);
          doorRoom3.beschreibung = "Jetzt, da du den Schlüssel hast, kannst du eintreten.";
        }else{
          doorRoom3.setzeAusgaenge( upperCorridor, null, null, null);
        }

        if (aktuellerRaum.name == "Raum 102" && inventory.hasItem("Raum 102 Schlüssel")){
          room3.setzeAusgaenge( null, null, upperCorridor, null);
        }

        if (aktuellerRaum.name == "Untergrundbibliothek" && inventory.hasItem("Ra's Auge Halsband")){
          secretentrance.setzeAusgaenge( null, kitchen, null, libEntrance);
        }

        if (aktuellerRaum.name == "Library Secret entrance" && inventory.hasItem("Goldene Pyramide")){
          libEntrance.setzeAusgaenge( null, secretentrance, null, underCorridor);
          libEntrance.beschreibung = "Die Tür hat auf die Goldene Pyramide reagiert.";
          
          
        }else {
          libEntrance.setzeAusgaenge( null, secretentrance, null, null);
        }

        if (aktuellerRaum.name == "Library Secret entrance"){
          seenSecretEntrance = true;
        }

        if (inventory.hasItem("Raum 103 Schlüssel")){
          doorRoom4.setzeAusgaenge(upperCorridor, null, room4, null);
          doorRoom4.beschreibung = "Jetzt, da du den Schlüssel hast, kannst du eintreten.";
        }else{
          doorRoom4.setzeAusgaenge(upperCorridor, null, null, null);
        }
    
    
        if (aktuellerRaum.name == "Eingang" && !isMessageShown){
        JOptionPane.showMessageDialog(null, "Du bist jetzt ins Haus eingetreten und die erste Quest beginnt. Deine Aufgabe ist es, \nalle Räume zu besuchen und nach Gegenständen mit dem Button 'SUCH' zu suchen. \nVergiss nicht, dein Zimmer im Obergeschoss, Zimmernummer 102, zu besuchen. \nDu solltest bereits den Schlüssel dafür haben!","FIRST TASK", JOptionPane.PLAIN_MESSAGE);
        isMessageShown = true;
        }  
    
        if (eingabe.equals("Suchen") && inventory.hasItem("Ra's Auge Halsband") && seenSecretEntrance == true && aktuellerRaum.name == "Treppen"){
           inventory.addItem("Goldene Pyramide");
        }

        if (eingabe.equals("Suchen")){
          System.out.println(inventory.newItem());
        }
    // --- Beschreibung des aktuellen Raums ausgeben ---
    bildLabel.setIcon(new ImageIcon(aktuellerRaum.bild));

    jTextArea1.append(aktuellerRaum.beschreibung+"\n\n");

    if(aktuellerRaum.name == "Geheimen Korridor" && finishFight == false){
          isMessageShown = false;
        }
    // --- Kampf anfangen ---

    if(aktuellerRaum.name == "Mistical Room" && finishFight == false){

      jTextField1.setVisible(true);
      jButton1.setVisible(true);
      fightRoom.setzeAusgaenge(null, null, null, null);
      jButtonSuch.setVisible(false);
      jTextField1.setText("");
      startFight = true;
    }


    

    if (aktuellerRaum.name == "Mistical Room" && !isMessageShown && finishFight == false){
      JOptionPane.showMessageDialog(null, "Du bist in den Kampfraum eingetreten. Dies ist dein letztes Hindernis, bevor du den geheimen \nRaum erreichen und dein Ziel erreichen kannst. In diesem Kampf sind deine Schäden um 20% \nhöher dank der zuvor erhaltenen goldenen Pyramide, aber deine Lebenspunkte sind im Vergleich \nzu deinem Gegner Anubis niedriger. \nDafür hast du jedoch mehr Ausdauer und jedes Mal, wenn du erfolgreich 'Dodge' benutzt,\ngewinnst du ein wenig Lebensenergie zurück. Sei dennoch vorsichtig, denn jedes Mal, wenn du\n'Dodge' benutzt, verbrauchst du auch Ausdauer, und der einzige Weg, sie zurückzugewinnen, \nist durch Verteidigung. \nVIEL GLÜCK!!","ALERT", JOptionPane.PLAIN_MESSAGE);

      isMessageShown = true; 
    }


    
    
    if(aktuellerRaum.name == "Mistical Room" && startFight == true && finishFight == false){

      if(startFight == true){
         jTextArea1.append("Kampf info: \n" + " ---> " + Spieler.name + ": " + Spieler.lebenspunkte + "HP  / " + Spieler.Stamina + " STA");

         jTextArea1.append("\n ---> " + Anubi.name + ": " + Anubi.lebenspunkte + "HP  / " + Anubi.Stamina + " STA");

         jTextField1.setText("Attack");

          if(attackiert == false){
            jTextArea1.append("\n\nKampf optionen: "+"\n" + " ---> Attack  <-\n" + " ---> Defend\n" + " ---> Dodge");

              if(eingabe.equals("Attack")){
                Kampf.Attack();
                attackiert = true;
                verteidigt = false;
            
              }
          
          }else if(verteidigt == false && attackiert == true){
            jTextField1.setText("Defend");
            jTextArea1.append("\n\nKampf optionen: "+"\n" + " ---> Attack\n" + " ---> Defend <-\n" + " ---> Dodge <-");

                if(eingabe.equals("Defend")){
                  Kampf.Defend();
                  attackiert = false;
                  verteidigt = true;
                }else if(Spieler.Stamina >= 20){
                  jTextField1.setText("Dodge");
                }
                if(eingabe.equals("Dodge") && Spieler.Stamina >= 20){
                  Kampf.Dodge();
                  attackiert = false;
                  verteidigt = true;
                  jTextField1.setText("Dodge");
                }else if(eingabe.equals("Dodge") && Spieler.Stamina < 20){
                  JOptionPane.showMessageDialog(null, "Du hast nicht genug Ausdauer, um dem Angriff auszuweichen. Versuch stattdessen 'Defend' zu benutzen.","ALERT", JOptionPane.PLAIN_MESSAGE);
                  attackiert = true;
                  verteidigt = false;
                }
          }
      }
      
    }else{
      
      if (inventory.hasAnyItem()){
           jTextArea1.append(inventory.getInventoryString() +"\n");
      }
      jTextArea1.append("Richtungen: "+"\n");
    }

    if (Anubi.lebenspunkte == 0 && startFight == true && finishFight == false){
      startFight = false;
      finishFight = true;
      JOptionPane.showMessageDialog(null, "DU HAST ANUBI GETÖTET!!!","ALERT", JOptionPane.PLAIN_MESSAGE);
      jTextField1.setVisible(false);   
      jButton1.setVisible(false);
      jTextArea1.setText("");
      jTextArea1.append("Quest abgeschlossen: Durch das Besiegen von Anubis wurde eine neue Tür geöffnet. Mach dich auf den Weg und finde das geheime Zimmer!\n\n");
      aktuellerRaum = fightRoom;
      fightRoom.setzeAusgaenge( secretroom,  null, underCorridor, null);  
      fightRoom.beschreibung = "Dies ist der Raum, in dem du Anubis besiegt hast.";
      fightRoom.bild = "Bilder/fightRoomAF.png";
    }

    if (Spieler.lebenspunkte == 0 && startFight == true){
      startFight = false;
      JOptionPane.showMessageDialog(null, "VERLOREN, ANUBI HAT DICH GETÖTET!!!","ALERT", JOptionPane.PLAIN_MESSAGE);
      System.exit(0); 
    }    
    
    // --- mögliche Ausgänge der Räume angeben ---

    if (aktuellerRaum.nord != null){
      jTextArea1.append(" ---> Norden: "+ aktuellerRaum.nord.name +"\n");
      jButtonNorth.setVisible(true);
    }else {
      jButtonNorth.setVisible(false);
    }
    
    if (aktuellerRaum.ost != null){
      jTextArea1.append(" ---> Osten: "+ aktuellerRaum.ost.name +"\n");       
      jButtonOst.setVisible(true);
    }else{
      jButtonOst.setVisible(false);
    }
    
    if (aktuellerRaum.west != null){
      jTextArea1.append(" ---> Westen: "+ aktuellerRaum.west.name +"\n");
      
      jButtonWest.setVisible(true);
  
     }else {
      jButtonWest.setVisible(false);
     }

    if (aktuellerRaum.sud != null){
      jTextArea1.append(" ---> Süden: "+ aktuellerRaum.sud.name +"\n"); 
      jButtonSouth.setVisible(true); 
    }else{
      jButtonSouth.setVisible(false);
    }

  }

  // Ende Methoden

  public static void main(String[] args) {
    try {
      // Set System L&F
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (Exception e) {
    }

    new Spiel("HOUSE OF ANUBIS");
  }
}

