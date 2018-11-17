package logic.brick;

public abstract class AbstractBrick implements Brick {
    @Override
    public void hit() {

    }

    @Override
    public boolean isDestroyed() {
        return false;
    }

    @Override
    public int getScore() {
        return 0;
    }

    @Override
    public int remainingHits() {
        return 0;
    }
}
