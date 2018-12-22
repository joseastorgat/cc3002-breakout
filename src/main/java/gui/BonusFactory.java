//package gui;
//
//import com.almasb.fxgl.entity.Entities;
//import com.almasb.fxgl.entity.Entity;
//import com.almasb.fxgl.entity.components.CollidableComponent;
//import com.almasb.fxgl.physics.BoundingShape;
//import com.almasb.fxgl.physics.HitBox;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import logic.bonus.Bonus;
//
//import java.util.Random;
//
//public class BonusFactory {
//    private Bonus extraBallBonus;
//    private Bonus extraPointsBonus;
//    private Random r = new Random();
//
//    public void setExtraBallBonus(Bonus extraBallBonus) {
//        this.extraBallBonus = extraBallBonus;
//    }
//
//    public void setExtraPointsBonus(Bonus extraPointsBonus) {
//        this.extraPointsBonus = extraPointsBonus;
//    }
//
//     public Entity newBonus(double x, double y) {
//        Bonus bonus;
//        if(r.nextDouble()<0.6){
//            bonus = extraBallBonus;
//
//        }
//        else{
//            bonus
//        }
//
//        return Entities.builder()
//                .at(x, y)
//                .type(BreakoutGameType.BONUS)
//                .bbox(new HitBox("Bonus", BoundingShape.box(30, 30)))
//                .viewFromNode(new Rectangle(30, 30, Color.GOLDENROD))
//                .with(new CollidableComponent(true), BonusControl(bonus))
//                .build();
//    }
//
//}
