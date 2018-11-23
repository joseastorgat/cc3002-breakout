package logic.level;

import logic.brick.Brick;
import logic.brick.GlassBrick;
import logic.brick.MetalBrick;
import logic.brick.WoodenBrick;
import logic.visitor.Visitor;

import java.util.*;

public class PlayableLevel implements Observer, Level {
    private List<Brick> bricksList;
    private Level nextLevel;
    private String name;

    public PlayableLevel(String name, int numberOfBricks, double probOfGlass, double probOfMetal, int seed){

        this.name = name;
        this.nextLevel = new EmptyLevel();
        this.bricksList = new ArrayList<>();
        Random r = new Random(seed);

        for(int i =0; i<numberOfBricks; i++){
            double p = r.nextDouble();
            if(p<probOfGlass) {
                bricksList.add(new GlassBrick());
            }
            else{
                bricksList.add(new WoodenBrick());
            }
        }
        for( int i = 0; i<numberOfBricks; i++){
            double p = r.nextDouble();
            if(p<probOfMetal) {
                bricksList.add(new MetalBrick());
            }
        }
        for( Brick brick : bricksList){
            brick.addObserver(this);
        }
    }

    @Override
    public String getName(){
        return this.name;
    };

    @Override
    public int getNumberOfBricks() {
        return bricksList.size();
    }

    @Override
    public List<Brick> getBricks() {
        return bricksList;
    }

    @Override
    public Level getNextLevel(){
        return this.nextLevel;
    }

    @Override
    public boolean isPlayableLevel() {
        return true;
    }

    @Override
    public boolean hasNextLevel(){
        return this.nextLevel.isPlayableLevel();
    };

    @Override
    public Level addPlayingLevel(Level level){
        this.nextLevel = this.nextLevel.addPlayingLevel(level);
        return this;
    }

    @Override
    public void setNextLevel(Level level){
        this.nextLevel = level;
    }

    @Override
    public int getPoints() {
        int pt = 0;
        for(Brick brick: bricksList){
            pt+=brick.getScore();
        }
        return pt;
    }

    @Override
    public void accept(Visitor visitor){
        visitor.visitPlayableLevel(this);
    }

    @Override
    public void update(Observable o, Object arg) {
    }
}
