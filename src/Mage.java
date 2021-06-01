import java.util.List;

public class Mage extends Player {
    private int manaPool;
    private int CurrentMana;
    private int ManaCost;
    private int SpellPower;
    private int HitsCount;
    private int AbilityRange;
    private String SpecialAbility="Blizzard";

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
        String s = "";
        if( CurrentMana-ManaCost>=0){
        while(!enemiesInRange.isEmpty()) {
            double random =  (Math.random());
            messageCallBack.Print(String.format("%s cast %s " ,this.Name ,this.SpecialAbility));
            if(random<0.5) {
                messageCallBack.Print(String.format("%s rolled %d attack points" ,this.Name ,SpellPower));
                 s += enemiesInRange.get(0).Name+" ";
                 enemiesInRange.remove(0).Defence(SpellPower);
            }else{
                enemiesInRange.remove(0);
            }

        }
        if(s.length()>0)
            messageCallBack.Print(String.format("%s cast %s and hit:%s " ,this.Name ,this.SpecialAbility,s));
        CurrentMana-=ManaCost;
        }else{
            messageCallBack.Print(String.format("%s tried to cast %s, but there was not enough mana: %d/%d",this.Name ,this.SpecialAbility,SpellPower));
        }
    }

    public String describe() {
        return String.format("%s\t\tmanaPool: %d/%d\t spell power: %d", super.describe(),CurrentMana,manaPool,this.SpellPower );
    }
}
