package apps.myapplication;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Tim on 8/21/2015.
 */
public class TheScore {

    public static ArrayList<Integer> xcoordinates(ArrayList<Integer> blocks) {

        ArrayList<Integer> xcoordinates = new ArrayList<Integer>();

        for (int i = 0; i < blocks.size(); i++) {
            int block = blocks.get(i);
            int y = (int) (block / 10);
            int x = block - y * 10;
            xcoordinates.add(x);
        }
        return xcoordinates;
    }

    public static ArrayList<Integer> ycoordinates(ArrayList<Integer> blocks) {

        ArrayList<Integer> ycoordinates = new ArrayList<Integer>();

        for (int i = 0; i < blocks.size(); i++) {
            int block = blocks.get(i);
            int y = (int) (block / 10);
            int x = block - y * 10;
            ycoordinates.add(y);
        }
        return ycoordinates;
    }

    public static int scoreColumn(ArrayList<Integer> blocks, int columnNumber){

        ArrayList<Integer> xcoordinates = xcoordinates(blocks);
        ArrayList<Integer> ycoordinates = ycoordinates(blocks);
        int scoretmp =0;

        ArrayList<Integer> columnX = new ArrayList<Integer>();

        // if the array contains a block in the column
        if(xcoordinates.contains(columnNumber)){
            // take the indexes of all those blocks and adds it to columnIndex
            for(int n =0; n < xcoordinates.size(); n++) {
                if(xcoordinates.get(n)==columnNumber){
                    columnX.add(n);
                }

            }

            if(columnX.size() > 2){
                ArrayList<Integer> column = xFromy(columnX, ycoordinates);
                Collections.sort(column);

                int index = 0;
                int count = 0;
                int numberTwo = 0;
                int numberOne  =0 ;

                for(int p =0; p < column.size() -1; p ++ ){

                    numberOne = column.get(index);
                    numberTwo = column.get(index + 1);
                    if(numberOne+1 ==numberTwo){
                        count++;
                    }else{
                        if(count>1){
                            scoretmp += count + 1;
                        }
                        count = 0;
                    }


                    index++;
                }

                if(count>1){
                    scoretmp += count + 1;
                }

            }
        }
        return scoretmp;
    }

    public static int scoreRow(ArrayList<Integer> blocks, int rowNumber){

        ArrayList<Integer> xcoordinates = xcoordinates(blocks);
        ArrayList<Integer> ycoordinates = ycoordinates(blocks);
        int scoretmp =0;

        ArrayList<Integer> rowX = new ArrayList<Integer>();

        // if the array contains a block in the row
        if(ycoordinates.contains(rowNumber)){
            // take the indexes of all those blocks and adds it to rowIndex
            for(int n =0; n < ycoordinates.size(); n++) {
                if(ycoordinates.get(n)==rowNumber){
                    rowX.add(n);
                }

            }

            if(rowX.size() > 2){
                ArrayList<Integer> row = yFromx(rowX, xcoordinates);
                Collections.sort(row);

                int index = 0;
                int count = 0;
                int numberTwo = 0;
                int numberOne  =0 ;

                for(int p =0; p < row.size() -1; p ++ ){

                    numberOne = row.get(index);
                    numberTwo = row.get(index + 1);
                    if(numberOne+1 ==numberTwo){
                        count++;
                    }else{
                        if(count>1){
                            scoretmp += count + 1;
                        }
                        count = 0;
                    }


                    index++;
                }

                if(count>1){
                    scoretmp += count + 1;
                }

            }
        }
        return scoretmp;
    }
    public static int scoreAllDiagonals(ArrayList<Integer> blocks, int columns, int rows){
        int scoreDiagonal = 0;
        // the positive diagonalsss
        for(int i = 1; i < rows - 1; i++){
            scoreDiagonal+= scoreDiagonalPositive(blocks, columns, rows, 1, i);
        }
        //skip the 1 because 1,1 is already counted above :)
        for(int i = 2; i < columns - 1; i++){
            scoreDiagonal+= scoreDiagonalPositive(blocks, columns, rows, i , 1);
        }

        // the negative diagonalsss
        for(int i = 3; i < rows + 1; i++){
            scoreDiagonal+= scoreDiagonalNegative(blocks, columns, rows, i,1 );
        }
        //skip the 1 because 1,1 is already counted above :)
        for(int i = 2; i < columns -1; i++){
            scoreDiagonal+= scoreDiagonalNegative(blocks, columns, rows, columns, i);
        }
        return scoreDiagonal;

    }
    public static int scoreDiagonalPositive(ArrayList<Integer> blocks, int columns, int rows, int startX, int startY){

        ArrayList<Integer> xcoordinates = xcoordinates(blocks);
        ArrayList<Integer> ycoordinates = ycoordinates(blocks);
        int scoretmp =0;

        ArrayList<Integer> diaBlocks = new ArrayList<Integer>();
        //important for the length of a possible max diagonal
        int shortestSide = 0;
        if ( columns < rows){
            shortestSide = columns;
        }else{
            shortestSide = rows;
        }
        int c = startX;
        int r = startY;

        for(int i = 0; i < shortestSide; i++){
            int diaBlock = (r+ i) * 10 + c + i;
            if(blocks.contains(diaBlock)){
                diaBlocks.add(diaBlock);
            }
        }
        Collections.sort(diaBlocks);
        //Log.d("test", "diaStartIndexX" + diaBlocks.toString());

        ArrayList<Integer> diaBlocksY = new ArrayList<Integer>();
        for(int i = 0; i < diaBlocks.size(); i ++){
            diaBlocksY.add((int)diaBlocks.get(i)/10 );
        }

        int index = 0;
        int count = 0;
        int numberTwo = 0;
        int numberOne  =0 ;

        for(int p =0; p < diaBlocksY.size() -1; p ++ ){

            numberOne = diaBlocksY.get(index);
            numberTwo = diaBlocksY.get(index + 1);
            if(numberOne+1 ==numberTwo){
                count++;
            }else{
                if(count>1){
                    scoretmp += count + 1;
                }
                count = 0;
            }


            index++;
        }

        if(count>1){
            scoretmp += count + 1;
        }

        return scoretmp;
    }

    public static int scoreDiagonalNegative(ArrayList<Integer> blocks, int columns, int rows, int startX, int startY){

        ArrayList<Integer> xcoordinates = xcoordinates(blocks);
        ArrayList<Integer> ycoordinates = ycoordinates(blocks);
        int scoretmp =0;

        ArrayList<Integer> diaBlocks = new ArrayList<Integer>();
        //important for the length of a possible max diagonal
        int shortestSide = 0;
        if ( columns < rows){
            shortestSide = columns;
        }else{
            shortestSide = rows;
        }
        // loop through all diagnals
        // the -1 is because the last 2 columns can't start a diagnol 3 or more
        ///////////////// //for(int c =1; c < columns -1; c++){
        // if there are blocks in this column
        int c = startX;
        int r = startY;

        for(int i = 0; i < shortestSide; i++){
            int diaBlock = (r+ i) * 10 + c - i;
            if(blocks.contains(diaBlock)){
                diaBlocks.add(diaBlock);
            }
        }
        // not needed ?..
        Collections.sort(diaBlocks);
        //Log.d("test", "diaStartIndexX" + diaBlocks.toString());

        ArrayList<Integer> diaBlocksY = new ArrayList<Integer>();
        for(int i = 0; i < diaBlocks.size(); i ++){
            diaBlocksY.add((int)diaBlocks.get(i)/10 );
        }

        int index = 0;
        int count = 0;
        int numberTwo = 0;
        int numberOne  =0 ;

        for(int p =0; p < diaBlocksY.size() -1; p ++ ){

            numberOne = diaBlocksY.get(index);
            numberTwo = diaBlocksY.get(index + 1);
            if(numberOne+1 ==numberTwo){
                count++;
            }else{
                if(count>1){
                    scoretmp += count + 1;
                }
                count = 0;
            }


            index++;
        }

        if(count>1){
            scoretmp += count + 1;
        }

        return scoretmp;
    }

    // name not perfect is for indexes (x)
    public static ArrayList<Integer> xFromy(ArrayList<Integer> x, ArrayList<Integer> y){
        ArrayList<Integer> result = new  ArrayList<Integer>();
        for(int n =0; n < x.size(); n++) {
            result.add(y.get(x.get(n)));
        }
        return result;
    }

    // name not perfect is for indexes (y)
    public static ArrayList<Integer> yFromx(ArrayList<Integer> y, ArrayList<Integer> x){
        ArrayList<Integer> result = new  ArrayList<Integer>();
        for(int n =0; n < y.size(); n++) {
            result.add(x.get(y.get(n)));
        }
        return result;
    }


    public static int scoreAllColumn(ArrayList<Integer> blocks, int columns){
        int scoreColumns = 0;
        for(int i = 1; i < columns + 1 ; i ++){
            scoreColumns += scoreColumn(blocks, i);
        }
        return scoreColumns;
    }
    public static int scoreAllRows(ArrayList<Integer> blocks, int rows){
        int scoreColumns = 0;
        for(int i = 1; i < rows + 1 ; i ++){
            scoreColumns += scoreRow(blocks, i);
        }
        return scoreColumns;
    }

    public static int totalScore(ArrayList<Integer> blocks, int columns,int rows){
        return scoreAllColumn(blocks,columns) + scoreAllRows(blocks, rows) + scoreAllDiagonals(blocks, columns, rows );
    }
}