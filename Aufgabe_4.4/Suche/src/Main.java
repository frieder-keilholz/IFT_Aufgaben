import java.util.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Test");    
        Spielfeld feld = new Spielfeld(3);
        feld.fillRandom();
        System.out.println(feld.toString());

        // Plan A: Alle möglichen Wege vom Ausgangs-Spielfeld aus bauen und danach die RDFGT 
        // Plan B: Während des RDFGT die nächsten Spielfelder erstellen

        // Plan A Impl:
        
    }
}
