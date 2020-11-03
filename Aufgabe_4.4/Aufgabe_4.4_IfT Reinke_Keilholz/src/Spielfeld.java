import java.util.*;

/**
 * Spielfeld-Objekte speichern den Zustand eines Push-Puzzles.
 * Bei der Initialisierung muss die Größe des Spielfeldes angegeben werden.
 * Über toString() kann das Spielfeld ausgegeben werden.
 * Außerdem kann ein Spielfeld zufällig oder mit einem gegebenen 2D-Array gefüllt werden.
 * Die möglichen nächsten Spielfelder bei einem Zug können ausgegeben werden.
 * Der Inhalt eines Spielfeldes kann mit einem anderen verglichen oder kopiert werden.
 * @author Julia Reinke & Frieder Keilholz  
 */
public class Spielfeld {
    /**
     * Größe des Spielfeldes (size x size)
     */
    int size;
    /**
     * Der Inhalt des Spielfeldes wird in einem 2D-Array gespeichert
     */
    int[][] content;

    /**
     * Konstruktor eines Spielfeldes. Legt ein neues Feld an.
     * @param size Größe des Feldes
     */
    public Spielfeld(int size){
        this.size = size;
        content = new int[size][size];
    }

    /**
     * Änders den Inhalt eines Spielfeldes
     * @param initContent Der Zustand des Feldes
     */
    public void fillSpielfeld(int[][] initContent){
        this.content = initContent;
    }

    /**
     * Füllt das Spielfeld mit einer zufälligen Positionierung des Zahlen
     */
    public void fillRandom(){
        HashSet<Integer> numbers = new HashSet<Integer>();
        for(int i = 0; i < this.size; i++){
            for(int j = 0; j < this.size; j++){
                int randomNumber;
                do{
                    randomNumber = (int) (Math.random()*this.size*this.size);
                }while(numbers.contains(randomNumber));
                numbers.add(randomNumber);
                content[i][j] = randomNumber;
            }
        }
    }

    /**
     * Prüft die möglichen nächsten Zustände des Spielfeldes und legt entsprechende Spielfeld-Objekte an.
     * @return alle möglichen Folge-Spielfelder
     */
    public ArrayList<Spielfeld> possibleMoves(){
        ArrayList<Spielfeld> returnList = new ArrayList<Spielfeld>();
        int emptyArray = 0;
        int emptyIndex = 0;
        for (int i = 0; i < content.length; i++) {
           for (int j = 0; j < content[i].length; j++) {
                if(this.content[i][j] == 0){
                    emptyArray = i;
                    emptyIndex = j;
                }
           } 
        }
        
        // Option 1
        if (emptyArray > 0) {
            Spielfeld newMove1;
            newMove1 = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray-1][emptyIndex];
            newSpielfeldContent[emptyArray-1][emptyIndex] = this.content[emptyArray][emptyIndex];
            newMove1.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove1);
        }
        // Option 2
        if (emptyArray < this.size-1) {
            Spielfeld newMove2 = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray+1][emptyIndex];
            newSpielfeldContent[emptyArray+1][emptyIndex] = this.content[emptyArray][emptyIndex];
            newMove2.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove2);
        }
        // Option 3
        if (emptyIndex > 0) {
            Spielfeld newMove3 = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray][emptyIndex-1];
            newSpielfeldContent[emptyArray][emptyIndex-1] = this.content[emptyArray][emptyIndex];
            newMove3.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove3);
        }
        // Option 4
        if (emptyIndex < this.size-1) {
            Spielfeld newMove4 = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray][emptyIndex+1];
            newSpielfeldContent[emptyArray][emptyIndex+1] = this.content[emptyArray][emptyIndex];
            newMove4.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove4);
        }
        return returnList;
    }

    /**
     * Legt ein neues 2D-Array an und kopiert den Inhalt der Spielfeldes in diesen.
     * @return ein 2D-Array mit dem Inhalt wie das Spielfeld
     */
    public int[][] copyContent(){
        int[][] returnContentArray = new int[this.size][this.size];
        for (int i = 0; i < this.content.length; i++) {
            for (int j = 0; j < this.content.length; j++) {
                returnContentArray[i][j] = this.content[i][j];
            }
        }
        return returnContentArray;
    }

    /**
     * Vergleicht den Inhalt des Spielfelds mit einem weiteren gegebenen.
     * @param feld2 das gegebne Spielfeld, welches mit dem Spielfeld verglichen werden soll
     * @return true, wenn der Inhalt der Felder übereinstimmt; false, wenn nicht
     */
    public boolean compareContent(Spielfeld feld2){
        if(this.size != feld2.size){
            return false;
        }
        for (int i = 0; i < this.content.length; i++) {
            for (int j = 0; j < this.content.length; j++) {
                if(this.content[i][j] != feld2.content[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gibt Spielfeld aus
     * @return String mit Darstellung des Spielfeldes
     */
    public String toString(){
        String returnString = "";
        for(int i = 0; i < this.size; i++){
            returnString += "[";
            for(int j = 0; j < this.size; j++){           
                if(this.content[i][j] != 0){
                    returnString += this.content[i][j]+" ";
                }else{
                    returnString += "  ";
                }
            }
            returnString += "]\n";
        }
        return returnString;
    }
}
