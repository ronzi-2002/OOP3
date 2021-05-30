public class Warrior extends Player {
    private int AbilityCoolDown;
    private int RemainingCoolDown;
    public Warrior(int RemainingCoolDown,int AbilityCoolDown,Position pos, String Name, int AttackPoints, int DefencePoints, Health h, int experience, int playerLevel) {
        super(pos, Name, AttackPoints, DefencePoints, h, experience, playerLevel);
        this.AbilityCoolDown=AbilityCoolDown;
        this.RemainingCoolDown=0;
    }
}
