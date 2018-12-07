package logic.brick;

import visitor.Visitor;
import java.util.Observable;

/**
 * AbstractBrick Class implements {@link Brick}
 *
 * @author José Astorga
 */
public abstract class AbstractBrick extends Observable implements Brick {
    private int score;
    private int hits;

    /**
     * AbstractBrick Constructor
     *
     * @param hits Number of Hit that the brick resist
     * @param score Score delivered when destroyed
     */
    AbstractBrick(int hits, int score){
        this.score = score;
        this.hits = hits;
    }
    
    @Override
    public void hit() {
        if(!this.isDestroyed()){
            this.hits-=1;
            if (this.isDestroyed()){
                this.setChanged();
                this.notifyObservers();
            }
        }
    }
    @Override
    public boolean isDestroyed() {
        return this.hits==0;
    }

    @Override
    public int getScore() {
        return this.score;
    }

    @Override
    public int remainingHits() {
        return this.hits;
    }

    /**
     * Accept the visit of a {@link Visitor}
     * @param visitor Visitor visiting brick.
     */
    @Override
    public abstract void accept(Visitor visitor);

    @Override
    public boolean isWoodenBrick(){
        return false;
    }

    @Override
    public boolean isGlassBrick(){
        return false;
    }

    @Override
    public boolean isMetalBrick(){
        return false;
    }

}

