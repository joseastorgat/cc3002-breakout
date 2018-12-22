package logic.brick;

import visitor.UpdateGamePointsVisitor;
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
                this.notifyUp();
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

    /**
     * Method that create an {@link UpdateGamePointsVisitor} and deliver it to observers
     */
    public void notifyUp(){
        Visitor visitor =new UpdateGamePointsVisitor(this.getScore());
        notifyObservers(visitor);
    }
}

