package logic.brick;

import visitor.Visitor;

/**
 * MetalBrick class is an {@link Brick}
 * <br>
 * 10 hit destroys it. Score 0 Points.
 *
 * @author José Astorga
 */
public class MetalBrick extends AbstractBrick {
	/**
	 * Metal Brick constructor
	 */
	public MetalBrick(){
		super(10, 0);
	}

	@Override
	public void accept(Visitor v){
		v.visitMetalBrick(this);
	}
}

