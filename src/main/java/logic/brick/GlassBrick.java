package logic.brick;

public class GlassBrick extends AbstractBrick {

	public GlassBrick(){
		super(1, 50);
	}
	accept(Visitor v){
		v.visitGlassBrick()
	}
}
