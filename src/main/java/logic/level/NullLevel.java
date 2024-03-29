package logic.level;
import logic.brick.Brick;
import visitor.Visitor;

import java.util.Collections;
import java.util.List;
import java.util.Observer;

/**
 *  NullLevel is an Null implementation of Level
 * @author José Astorga
 */
public class NullLevel implements Level {
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
    public int getCurrentPoints(){return 0;}

    @Override
    public void addPoints(int points){}

    @Override
    public void accept(Visitor visitor){
    }

    @Override
    public void subscribe(Observer observer){
    }



}

