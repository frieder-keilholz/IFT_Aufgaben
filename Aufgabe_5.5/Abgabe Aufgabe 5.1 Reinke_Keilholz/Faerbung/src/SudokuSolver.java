import graph.*;
import graph.algorithm.BackTrackColoring;
import graph.algorithm.BackTrackColoringCopy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Übung IFT
 * Aufgabe 5.5
 * @author Julia Reinke and Frieder Keilholz
 * 
 * This class contains the void main to run the SudokuSolver.
 * The SudokuSolver tries to use BackTracking, to solve a given Sudoku.
 * A Sudoku can by entered by providing a textfile containing an unsolved sudoku.
 * 
 * At first the textfile under "../../Sudoku/Sudoku1.txt" is read in.
 * The numbers are stored in an array and given out.
 * 81 Vertices are created and linked to the given number using a hashmap.
 * The 81 Vertices are added to a graph.
 * To set the dependencies of a Sudoku, the method prepareMapAndGraph() creates edges for the fields in a row,
 * column and 3x3 field.
 * That graph and the initialMap containing Vertices and numbers is then given to an adapted version of the
 * BackTrackColoring-class.
 */
public class SudokuSolver {
    
    public SudokuSolver(String fName) throws Exception{
        System.out.println("Filename: "+fName);
        this.initialField = readSudoku(fName);
        this.sudokuGraph = new GraphImpl(false);
    }

    public void solve(){
        this.showField(this.initialField);
        this.prepareMapAndGraph();
        BackTrackColoringCopy backtrack = new BackTrackColoringCopy(this.sudokuGraph, this.initialMap);
        int[] resultField = new int[81];

        try{
            /**
             * The coloring function with a parameter is used, because we know the number of needed colors.
             */
            Map<Vertex, Integer> solution = backtrack.coloring();
            

            for(int i = 0; i < 81;i++){
                resultField[i] = solution.get(this.vertices[i]);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        //System.out.println(this.sudokuGraph.toString());
        System.out.println();
        showField(resultField);
    }

    public static void main(String[] args) {
        try{
            SudokuSolver sudokuSolver = new SudokuSolver("../../Sudoku/Sudoku1.txt");
            sudokuSolver.solve();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private int[] initialField;
    private Vertex[] vertices;
    private Graph sudokuGraph;
    private HashMap<Vertex, Integer> initialMap;
    private int[] readSudoku(String fName) throws Exception{
        int[] numbers = new int[81];

        File sudokuFile = new File(fName);
        Scanner fileReader = new Scanner(sudokuFile);
        int i = 0;
        while(fileReader.hasNext()){
            String line = fileReader.nextLine();
            for(int j = 0;j < line.length(); j++){
                numbers[i++] = line.charAt(j)-'0';
            }
        }
        return numbers;        
    }
    private void prepareMapAndGraph(){
        vertices = new Vertex[81];
        this.initialMap = new HashMap<Vertex, Integer>();
        try{
            /**
             * Create Vertices correstponding to fields in a Sudoku.
             */
            for(int i = 0; i < 81; i++){
                vertices[i] = new Vertex();
                this.sudokuGraph.add(vertices[i]);
                if(this.initialField[i] == 0){
                    this.initialMap.put(vertices[i], 0);
                }else{
                    this.initialMap.put(vertices[i], this.initialField[i]);
                }
            }
            /**
             * Create Edges corresponding to Sudoku realations.
             */
            // Connect rows
            for(int r = 0; r < 9; r++){
                for(int indexV1 = r*9; indexV1 < r*9+8; indexV1++){
                    //System.out.print(i+" ");
                    for(int indexV2 = indexV1+1; indexV2 < r*9+9; indexV2++){
                        //System.out.print(indexV1 + " -> "+ indexV2 + "   ");
                        this.sudokuGraph.addEdge(vertices[indexV1], vertices[indexV2]);
                    }
                }
                //System.out.println();
            }
            // Connect column
            for(int c = 0; c < 9; c++){
                for(int indexV1 = c; indexV1 < 72+c; indexV1+=9){
                    for(int indexV2 = indexV1+9; indexV2 < 72+c+1; indexV2+=9){
                        //System.out.print(indexV1 + " -> "+ indexV2 + "   ");
                        this.sudokuGraph.addEdge(vertices[indexV1], vertices[indexV2]);
                    }
                }
                //System.out.println();
            }
            // Connect 3x3 field
            for(int i = 0; i <=54; i+=27){
                for(int iniField = i; iniField <= i+6; iniField+= 3){
                    //System.out.println(iniField);
                    for(int ii = iniField; ii <= iniField+18; ii+=9){
                        for(int indexV1 = ii; indexV1 <= ii+2; indexV1++){
                            //System.out.print("i1:"+indexV1);
                            for(int jj = ii; jj <= iniField+18; jj+=9){
                                //System.out.println(jj);
                                for(int indexV2=jj; indexV2 <= jj+2; indexV2++){
                                    if (indexV2 > indexV1) {
                                        //System.out.print(" "+indexV1+ "->"+indexV2+" | ");    
                                        this.sudokuGraph.addEdge(vertices[indexV1], vertices[indexV2]);
                                    }
                                }
                            }
                            //System.out.println();
                        }
                    }
                    //System.out.println();
                }
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    private void showField(int[] nums){
        for(int i = 0; i < 9; i++){
            for(int j = i*9; j < i*9+9; j++){
                System.out.print(nums[j]+" ");
            }
            System.out.println();
        }
    }
}
