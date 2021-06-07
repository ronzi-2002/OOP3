package Backend;

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
            int hits=0;
        while(!enemiesInRange.isEmpty() & hits<getHitsCount()) {
            int random = (int)  (Math.random()*enemiesInRange.size());
            messageCallBack.Print(String.format("%s cast %s " ,this.Name ,this.SpecialAbility));
            messageCallBack.Print(String.format("%s rolled %d attack points" ,this.Name ,SpellPower));
            Enemy e=enemiesInRange.get(random);
            s += e.Name+" ";
            e.Defence(SpellPower,true);
            hits++;
            if(e.h.HealthAmount==0)
                enemiesInRange.remove(random);
        }
        if(s.length()>0)
            messageCallBack.Print(String.format("%s cast %s and hit:%s " ,this.Name ,this.SpecialAbility,s));
        CurrentMana-=ManaCost;
        }else{
            messageCallBack.Print(String.format("%s tried to cast %s, but there was not enough mana: %d/%d",this.Name ,this.SpecialAbility,this.CurrentMana,this.ManaCost));
        }
    }

    public String describe() {
        return String.format("%s\t\tmanaPool: %d/%d\t spell power: %d", super.describe(),CurrentMana,manaPool,this.SpellPower );
    }

    public int getManaPool() {
        return manaPool;
    }

    public void setManaPool(int manaPool) {
        this.manaPool = manaPool;
    }

    public int getCurrentMana() {
        return CurrentMana;
    }

    public void setCurrentMana(int currentMana) {
        CurrentMana = currentMana;
    }

    public int getManaCost() {
        return ManaCost;
    }

    public void setManaCost(int manaCost) {
        ManaCost = manaCost;
    }

    public int getSpellPower() {
        return SpellPower;
    }

    public void setSpellPower(int spellPower) {
        SpellPower = spellPower;
    }

    public int getHitsCount() {
        return HitsCount;
    }

    public void setHitsCount(int hitsCount) {
        HitsCount = hitsCount;
    }

    public int getAbilityRange() {
        return AbilityRange;
    }

    public void setAbilityRange(int abilityRange) {
        AbilityRange = abilityRange;
    }

    public String getSpecialAbility() {
        return SpecialAbility;
    }

    public void setSpecialAbility(String specialAbility) {
        SpecialAbility = specialAbility;
    }
}
