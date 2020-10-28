import java.util.*;

public class Spielfeld {
    int size;
    int[][] content;

    public Spielfeld(int size){
        this.size = size;
        content = new int[size][size];
    }

    public void fillSpielfeld(int[][] initContent){
        this.content = initContent;
    }

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
        Spielfeld newMove;
        // Option 1
        if (emptyArray > 0) {
            newMove = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray-1][emptyIndex];
            newMove.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove);
        }
        // Option 2
        if (emptyArray < this.size-1) {
            newMove = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray+1][emptyIndex];
            newMove.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove);
        }
        // Option 3
        if (emptyIndex > 0) {
            newMove = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray][emptyIndex-1];
            newMove.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove);
        }
        // Option 4
        if (emptyIndex < this.size-1) {
            newMove = new Spielfeld(this.size);
            int[][] newSpielfeldContent = copyContent();
            newSpielfeldContent[emptyArray][emptyIndex] = this.content[emptyArray][emptyIndex+1];
            newMove.fillSpielfeld(newSpielfeldContent);
            returnList.add(newMove);
        }
        return returnList;
    }

    public int[][] copyContent(){
        int[][] returnContentArray = new int[this.size][this.size];
        for (int i = 0; i < this.content.length; i++) {
            for (int j = 0; j < this.content.length; j++) {
                returnContentArray[i][j] = this.content[i][j];
            }
        }
        return returnContentArray;
    }

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
