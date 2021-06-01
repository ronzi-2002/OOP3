import java.util.List;

public class Mage extends Player {
    private int manaPool;
    private int CurrentMana;
    private int ManaCost;
    private int SpellPower;
    private int HitsCount;
    private int AbilityRange;
    public Mage(Position pos,  String Name, Health h, int AttackPoints, int DefencePoints,int ManaPool,int ManaCost, int SpellPower, int hitsCount,int Range) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.AbilityRange=Range;
        this.HitsCount=hitsCount;
        this.ManaCost=ManaCost;
        this.manaPool=ManaPool;
        this.CurrentMana=manaPool/4;
        this.SpellPower=SpellPower;
        this.SpecialAbilityRange=AbilityRange;
    }
    public void updateTicks() {
        CurrentMana=Math.min(CurrentMana+1*this.PlayerLevel,manaPool);
    }
    @Override
    public void specialAbility(List<Enemy> enemiesInRange) {
        while(!enemiesInRange.isEmpty() && CurrentMana>=ManaCost) {
            double random =  (Math.random());
            if(random<0.5)
                enemiesInRange.remove(0).Defence( SpellPower);
            else
                enemiesInRange.remove(0);
        }
        CurrentMana-=ManaCost;
    }
}
