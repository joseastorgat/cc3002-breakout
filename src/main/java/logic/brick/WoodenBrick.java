package logic.brick;

import logic.visitor.Visitor;

public class WoodenBrick extends AbstractBrick {
	
	public GlassBrick(){
		super(10, 0);
	}

	@Override
	public void accept(Visitor v){
		v.visitWoodenBrick(this);
	}
}
}
