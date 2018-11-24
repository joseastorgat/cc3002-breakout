package logic.brick;

import visitor.Visitor;

import java.util.Observable;
import java.util.Observer;

/**
 * AbstractBrick Class implements {@link Brick}
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
    public AbstractBrick(int hits, int score){
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

    @Override
    public abstract void accept(Visitor visitor);


}

