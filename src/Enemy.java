public class Enemy extends Unit{
    private boolean Movable=false;
    public Enemy(Position pos,String Name,int AttackPoints, int DefencePoints,Health h)
    {
        super(pos, Name, AttackPoints, DefencePoints, h);
    }
}
