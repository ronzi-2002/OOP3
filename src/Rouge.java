import java.util.List;

public class Rouge extends Player{
    private int cost;
    private int CurrentEnergy;
    public Rouge(Position pos, String Name, int AttackPoints, int DefencePoints, Health h, int experience, int playerLevel,int CurrentEnergy,int cost) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.cost=cost;
        this.CurrentEnergy=CurrentEnergy;
        this.SpecialAbilityRange=2;
    }
    public void updateTicks() {
        CurrentEnergy=Math.min(CurrentEnergy+10,100);
    }

    @Override
    public void specialAbility(List<Enemy> enemiesInRange) {
        while(!enemiesInRange.isEmpty()) {
            enemiesInRange.remove(0).Defence( (AttackPoints));
        }
        this.CurrentEnergy-=cost;
        h.IncreaseHealthAmount(DefencePoints * 10);
    }
}
