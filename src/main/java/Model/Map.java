package Model;

import Model.Positions.Position;

import java.util.ArrayList;

public class Map{





    private static final int Num_Of_CELLS_IN_ROW=5;
    private static final int Num_Of_CELLS_IN_COLOUM=5;
    Cell[][] cells =new Cell[Num_Of_CELLS_IN_COLOUM][Num_Of_CELLS_IN_ROW];

    private Map() {
        this.cells = cells;
    }


    public ArrayList<Cell> getAjacentCells(Cell cell){
        return null;
    }


    public boolean cage(Position position){
        return true;
    }


    public void turn(){


    }


    public void printMap(){

    }

}
