import java.util.List;

public class Warrior extends Player {
    private int AbilityCoolDown;
    private int RemainingCoolDown;
    private String SpecialAbility="Avengers shield ";

    public Warrior(Position pos, String Name, Health h, int AttackPoints, int DefencePoints, int AbilityCoolDown) {
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
                this.h.IncreaseHealthAmount(this.DefencePoints*10);//added
                int random = (int) (Math.random() * (enemiesInRange.size()));
                messageCallBack.Print(String.format("%s cast %s, healing for %d " ,this.Name ,this.SpecialAbility,this.DefencePoints*10));
                messageCallBack.Print(String.format("%s rolled %d attack points" ,this.Name ,(h.HealthPool) / 10));
                enemiesInRange.remove(random).Defence((h.HealthPool) / 10,true);
            }
            h.IncreaseHealthAmount(DefencePoints * 10);
            RemainingCoolDown = AbilityCoolDown+1;
        }else {
            messageCallBack.Print(String.format("%s try to cast %s but there is a coolDown %d", this.Name, this.SpecialAbility,this.RemainingCoolDown));
        }
    }
    public String describe() {
        return String.format("%s\t\tCoolDown: %d/%d", super.describe(),RemainingCoolDown,AbilityCoolDown );
    }
}
