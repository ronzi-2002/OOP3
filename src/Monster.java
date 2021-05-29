public class Monster extends Enemy {
    private int VisionRange;
    public Monster(Position pos, String Name, int AttackPoints, int DefencePoints, Health h, int VisionRange) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.VisionRange=VisionRange;
    }
}
