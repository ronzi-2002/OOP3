public class Mage extends Player {
    private int manaPool;
    private int CurrentMana;
    private int ManaCost;
    private int SpellPower;
    private int HitsCount;
    private int AbilityRange;
    public Mage(Position pos,int SpellPower, String Name, int AttackPoints, int DefencePoints, Health h, int experience, int playerLevel,int ManaPool) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.manaPool=ManaPool;
        this.CurrentMana=manaPool/4;
        this.SpellPower=SpellPower;
    }
}
