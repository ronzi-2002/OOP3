public abstract class Unit extends Tile{
    String Name;
    int AttackPoints;
    int DefencePoints;
    Health h;

    public Unit(char c,Position pos,String Name,int AttackPoints, int DefencePoints,Health h)//todo can receive two ints instead of Health
    {
        super(c,pos);
        this.Name=Name;
        this.AttackPoints= AttackPoints;
        this.DefencePoints= DefencePoints;
        this.h=h;
    }
    public abstract boolean Visit(Enemy enemy);
    public abstract boolean Visit(Player player);
    public boolean Visit(Wall wall){
        return false;
    }
    public boolean Visit(Empty empty){
        return true;
    }
    public void setPosition(Position p) { this.pos=p;}
}
