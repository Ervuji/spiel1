public class Kampf {

  Spieler spieler;
  Gegner gegner;
  
  public Kampf(Spieler spieler, Gegner gegner) {
    this.spieler = spieler;
    this.gegner = gegner;
  }

  public void Attack() {
    gegner.lebenspunkte = gegner.lebenspunkte - spieler.angriffswert; 
    spieler.Stamina = spieler.Stamina - 5;
    gegner.Stamina = gegner.Stamina + 10;

    if(gegner.lebenspunkte <= 0){
      gegner.lebenspunkte = 0;
    }
    if(spieler.Stamina <= 0){
      spieler.Stamina = 0;
    }
  }

  public void Defend() {
    spieler.lebenspunkte = spieler.lebenspunkte - gegner.angriffswert;
    spieler.Stamina = spieler.Stamina + 20;
    gegner.Stamina = gegner.Stamina - 10;
    
    if(spieler.lebenspunkte <= 0){
      spieler.lebenspunkte = 0;
    }
    if(gegner.Stamina <= 0){
       gegner.Stamina = 0;
    }
    
    
    
  }

  public void Dodge() {
    spieler.Stamina = spieler.Stamina - 20;
    spieler.lebenspunkte = spieler.lebenspunkte + 10;
  }

  

  

}
