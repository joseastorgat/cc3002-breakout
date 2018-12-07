package logic.brick;

import visitor.Visitor;

/**
 * GlassBrick class is an Brick
 * <br>
 * 1 hit destroys it. Score 50 Points.
 *
 * @author José Astorga
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


	@Override
	public boolean isGlassBrick(){
		return true;
	}
}
