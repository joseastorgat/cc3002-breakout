package logic.brick;

import logic.visitor.Visitor;

import java.util.Observable;
import java.util.Observer;

public abstract class AbstractBrick extends Observable implements Brick {
    private int score;
    private int hits;

    public AbstractBrick (int hits, int score){
        this.score = score;
        this.hits = hits;
    }
    
    @Override
    public void hit() {
        if(!this.isDestroyed()){
            this.hits-=1;
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

    public void subscribe(Observer observer){
        addObserver(observer);
    }

    @Override
    public abstract accept(Visitor visitor);
}

