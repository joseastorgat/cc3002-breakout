package logic.brick;

import visitor.Visitor;

/**
 * GlassBrick class is an Brick
 * <br>
 * 1 hit destroys it. Score 50 Points.
 */
public class GlassBrick extends AbstractBrick {
	/**
	 * Glass Brick constructor
	 */
	public GlassBrick(){
		super(1, 50);
	}

	@Override
	public void accept(Visitor v){
		v.visitGlassBrick(this);
	}
}
