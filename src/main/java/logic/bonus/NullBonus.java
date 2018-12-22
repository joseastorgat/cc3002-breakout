package logic.bonus;
/**
 * NullBonus is a {@link Bonus}.
 *
 * @author José Astorga
 */
public class NullBonus extends AbstractBonus{
    @Override
    public void triggerBonus() {
    }
    @Override
    public boolean isNullBonus(){
        return true;
    }
}
