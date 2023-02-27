public class Raum {

  String beschreibung;
  String name;
  String bild;
  Raum ost;
  Raum nord;
  Raum west;
  Raum sud;

  public Raum(String beschreibung, String name, String bild) {
    this.beschreibung = beschreibung;
    this.name = name;
    this.bild = bild;
  }

  public void setzeAusgaenge(Raum nord, Raum ost, Raum sud, Raum west) {
    this.nord = nord;
    this.ost = ost;
    this.sud = sud;
    this.west = west;
  }

  

}
