public class Unit extends Tile{
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
    public void setPosition(Position p)
    {
        this.pos=p;
    }

}
