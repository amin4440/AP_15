package Model;

import Model.Positions.Position;

import java.util.ArrayList;

public class Cell {
    Position position;
    ArrayList<Model.Item> items =new ArrayList<>(0);
    private Grass grass;




    public boolean cage(){

        return true;
    }

    public Grass getGrass() {
        return grass;
    }

    public void setGrass(Grass grass) {
        this.grass = grass;
    }

    public void grassMaker() {
        this.grass = new Grass();
    }

    public boolean collect(){
        return false;
    }


    public Position getPosition() {
        return position;
    }

    public ArrayList<Model.Item> getItems() {
        return items;
    }

    public void setPosition(Position position) {
        this.position = position;
    }




    public void addItem(Model.Item item){




    }


}
