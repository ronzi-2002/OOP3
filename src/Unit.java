public abstract class Unit extends Tile{
    MessageCallBack messageCallBack;
    String Name;
    int AttackPoints;
    int DefencePoints;
    Health h;
    protected int TicksCount;
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
    public void updateTicks(){ return;}
    public String describe() {return String.format("Name: %s\t\tAttack Points: %d\t\tDefence Points: %d\t\tHealth: %s", Name, AttackPoints,DefencePoints , h.toString()); }
    public void initialize(MessageCallBack messageCallBack1){this.messageCallBack=messageCallBack1; }
}
