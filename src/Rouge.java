import java.util.List;

public class Rouge extends Player{
    private int cost;
    private int CurrentEnergy;
    public String SpecialAbility="Fan of Knives";
    public Rouge(Position pos, String Name, Health h, int AttackPoints, int DefencePoints, int cost) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.cost=cost;
        this.CurrentEnergy=100;
        this.SpecialAbilityRange=2;
    }
    public void updateTicks() {
        CurrentEnergy=Math.min(CurrentEnergy+10,100);
    }

    @Override
    public void specialAbility(List<Enemy> enemiesInRange) {
        if(this.CurrentEnergy-cost>0) {
            messageCallBack.Print(String.format("%s cast %s ", this.Name, this.SpecialAbility));
            while (!enemiesInRange.isEmpty()) {
                messageCallBack.Print(String.format("%s rolled %d attack points", this.Name, AttackPoints));
                enemiesInRange.remove(0).Defence(AttackPoints,true);
            }
            this.CurrentEnergy -= cost;
            h.IncreaseHealthAmount(DefencePoints * 10);
            messageCallBack.Print(String.format("%s cast %s, healing for %d ", this.Name, this.SpecialAbility, this.DefencePoints * 10));
        }else{
            messageCallBack.Print(String.format("%s tried to cast %s but there was not enough energy %d/%d ", this.Name, this.SpecialAbility,this.CurrentEnergy,100));

        }
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getCurrentEnergy() {
        return CurrentEnergy;
    }

    public void setCurrentEnergy(int currentEnergy) {
        CurrentEnergy = currentEnergy;
    }
}
