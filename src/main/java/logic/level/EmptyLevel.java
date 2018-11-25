package logic.level;
import logic.brick.Brick;
import visitor.Visitor;

import java.util.Collections;
import java.util.List;
import java.util.Observer;

/**
 *  EmptyLevel is an Null implementation of Level
 * @author José Astorga
 */
public class EmptyLevel implements Level {
    @Override
    public String getName(){
        return "";
    }

    @Override
    public int getNumberOfBricks() {
        return 0;
    }

    @Override
    public List<Brick> getBricks() {
        return Collections.emptyList();
    }

    @Override
    public Level getNextLevel(){
        return this;
    }

    @Override
    public boolean isPlayableLevel() {
        return false;
    }

    @Override
    public boolean hasNextLevel(){
        return false;
    }

    @Override
    public Level addPlayingLevel(Level level){
        return level;
    }

    @Override
    public void setNextLevel(Level level){}

    @Override
    public int getPoints() {
        return 0;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitEmptyLevel(this);
    }
    @Override
    public void subscribe(Observer observer){
    }



}

