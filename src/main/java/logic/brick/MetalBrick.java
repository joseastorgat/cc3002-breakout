package logic.brick;

import logic.visitor.Visitor;

public class MetalBrick extends AbstractBrick {

	public GlassBrick(){
		super(3, 200);
	}

	@Override
	public void accept(Visitor v){
		v.visitMetalBrick(this);
	}
}
}
