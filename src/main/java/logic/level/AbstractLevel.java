package logic.level;

import logic.brick.Brick;

import java.util.List;

public abstract class AbstractLevel implements Level {
    private Level nextLevel;
    private String name;

    public AbstractLevel(String name){
        this.name = name;
        this.nextLevel = new EmptyLevel();
    }

    @Override
    public String getName(){
        return this.name;
    };

    @Override
    public Level getNextLevel() {
        return this.nextLevel;
    }

    @Override
    public boolean hasNextLevel() {
        return this.nextLevel.isPlayableLevel();
    }


    @Override
    public Level addPlayingLevel(Level level) {

        if (this.hasNextLevel()){
            return this.nextLevel.addPlayingLevel(level);
        }
        else{
            this.setNextLevel(level);
            return level;
        }
    }

    @Override
    public void setNextLevel(Level level) {
        this.nextLevel = level;
    }

    @Override
    public abstract int getNumberOfBricks();

    @Override
    public abstract List<Brick> getBricks();

    @Override
    public abstract boolean isPlayableLevel();

    @Override
    public abstract int getPoints();

}
