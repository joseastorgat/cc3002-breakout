package logic.level;
import logic.brick.Brick;
import logic.visitor.Visitor;

import java.util.List;

public class EmptyLevel extends AbstractLevel {

    public EmptyLevel(){
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
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitEmptyLevel(this);
    }
}

