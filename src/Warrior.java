import java.util.List;

public class Warrior extends Player {
    private int AbilityCoolDown;
    private int RemainingCoolDown;
    private String SpecialAbility="Avengers shield ";

    public Warrior(Position pos, String Name, int AttackPoints, int DefencePoints, Health h,int AbilityCoolDown) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.AbilityCoolDown=AbilityCoolDown;
        this.RemainingCoolDown=0;
        this.SpecialAbilityRange=3;
    }
    @Override
    public void updateTicks() {
        if (RemainingCoolDown > 0) {
            RemainingCoolDown--;
        }
    }
    @Override
    public void specialAbility(List<Enemy> enemiesInRange) {
        if (RemainingCoolDown == 0) {
            if (!enemiesInRange.isEmpty()) {
                int random = (int) (Math.random() * (enemiesInRange.size() - 1));
                enemiesInRange.get(random).Defence((h.HealthPool) / 10);
            }
            h.IncreaseHealthAmount(DefencePoints * 10);
            RemainingCoolDown = AbilityCoolDown+1;
        }

    }
    public String describe() {
        return String.format("%s\t\tCoolDown: %d/%d", super.describe(),RemainingCoolDown,AbilityCoolDown );
    }
}
