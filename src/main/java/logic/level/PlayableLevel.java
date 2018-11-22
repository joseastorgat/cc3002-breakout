package logic.level;

import logic.brick.Brick;
import logic.visitor.Visitor;

import java.util.List;
import java.util.Observer;

public class PlayableLevel extends AbstractLevel implements Observer {
    private List<Brick> bricksList;
    private int numberOfBricks;

    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){
        super(name);
    }

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    @Override
    public List<Brick> getBricks() {
        return null;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitPlayableLevel(this);
    }
}
