package Model.Animals;

import Model.Cell;
import Model.Farm;
import Model.Item;
import Model.Map;
import Model.Positions.MapPosition;
import Model.Positions.NonMapPosition;
import controller.InputProcessor;

import java.util.HashSet;
import java.util.List;
import java.util.ListIterator;

public class WildAnimal extends Animal {
    public static HashSet<WildAnimalInfo> wildAnimalInfos = new HashSet<>(0);

    static {
        wildAnimalInfos.add(new WildAnimalInfo("Lion", -1, -1, 150, 50));
        wildAnimalInfos.add(new WildAnimalInfo("Bear", -1, -1, 200, 40));

    }

    private Cage cage = new Cage(this);

    //Finished

    public WildAnimal(WildAnimalInfo wildAnimalInfo, Map map) {

        super(wildAnimalInfo, map);
        cage.wildAnimal= this;

    }

    //Finished
    public static WildAnimal getInstance(String name) {
        for (WildAnimalInfo wildAnimalInfo :
                wildAnimalInfos) {
            if (wildAnimalInfo.getItemName().equalsIgnoreCase(name)) {
                return new WildAnimal(wildAnimalInfo, InputProcessor.game.getFarm().getMap());
            }

        }
        return null;
    }

    public Cage getCage() {
        return cage;
    }

    public void setCage(Cage cage) {
        this.cage = cage;
    }

    public boolean kill() {
        Cell cell = map.getCellByPosition((MapPosition) this.getPosition());
        List<Item> items = cell.getItems();
        boolean isThereADog = false;
        for (Item item : items) {
            if (item instanceof Dog) {
                isThereADog = true;
            }

        }

        for (Item item:items) {
            if (!(item instanceof WildAnimal)) {
                item.die();
                InputProcessor.game.getFarm().getMap().getCellByPosition((MapPosition) this.getPosition()).removeItem(item);
            }
        }

        if (isThereADog){
            InputProcessor.game.getFarm().getMap().getCellByPosition((MapPosition) this.position).removeItem(this);
        }
        return false;


    }


    @Override
    public boolean upgrade(Farm farm) {
        return false;
    }

    @Override
    public int getUpgradeCost() {
        return 0;
    }

    public void getCaged() {
        setCage(new Cage(this));
        // setCage(new Cage(((MapPosition) getPosition()).getX(), ((MapPosition) getPosition()).getY()));
    }

    public void escape() {
        setCage(null);
        InputProcessor.game.getFarm().getMap().getCellByPosition((MapPosition) this.position).removeItem(this);

    }

    @Override
    public boolean move() {
        if (getPosition() instanceof NonMapPosition) {
            return false;
        }
        return super.move();
    }

    @Override
    public boolean turner() {
        super.turner();

        cage.turn();

        if (!this.isCaged()) {
            if (!this.kill()) {
                this.move();
            }
        }else {
            show(this.itemInfo.getItemName()+ "Caged");
        }

        return false;
    }

    public boolean isCaged() {
        return this.getCage().getCompletnessPercetage() == this.getCage().getProgressMaxValue();
    }


    public static class WildAnimalInfo extends AnimalInfo {


        public WildAnimalInfo(String itemName, int depotSize, int buyCost, int SaleCost, int speed) {
            super(itemName, depotSize, buyCost, SaleCost, speed);
        }

    }
}
