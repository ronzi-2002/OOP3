public class Warrior extends Player {
    private int AbilityCoolDown;
    private int RemainingCoolDown;
    public Warrior(Position pos, String Name, int AttackPoints, int DefencePoints, Health h,int AbilityCoolDown) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.AbilityCoolDown=AbilityCoolDown;
        this.RemainingCoolDown=0;
    }
}
