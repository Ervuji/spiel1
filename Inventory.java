import java.util.ArrayList;
import java.util.List;

public class Inventory{
  
  List<String> inventory = new ArrayList<>();
  
      private List<String> items;

        public Inventory() {
          items = new ArrayList<String>();
        }

        public boolean hasItem(String item) {
          return items.contains(item);
        }

        public void addItem(String item) {
          if (!items.contains(item)) {
             items.add(item);
         }
        }

        public void removeItem(String item) {
          items.remove(item);
        }

        

        public boolean hasAnyItem() {
          
         return items.size() >= 0;
        }

        public boolean notHaveItem() {
          
         return items.size() != 0;
        }

        public int newItem(){
          int n = items.size();

          return n;
        }

   public String getInventoryString() {
    StringBuilder sb = new StringBuilder();
    sb.append("Inventar: \n");
    if (items.size() == 0) {
    sb.append(" Kein\n");
  } else {
    for (String item : items) {
      sb.append(" ---> " + item + "\n");
    }
  }
    return sb.toString();
  }
}

