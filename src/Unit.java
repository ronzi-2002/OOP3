public class Unit extends Tile{
    String Name;
    int AttackPoints;
    int DefencePoints;
    Health h;
    private boolean Movable;
    public Unit(Position pos,String Name,int AttackPoints, int DefencePoints,Health h)//todo can receive two ints instead of Health
    {
        super('@',pos);
        this.Name=Name;
        this.AttackPoints= AttackPoints;
        this.DefencePoints= DefencePoints;

    }
    @Override
    public boolean isMovable() {
        return Movable;
    }
}
