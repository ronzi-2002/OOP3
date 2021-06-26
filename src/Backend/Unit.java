package Backend;

public abstract class Unit extends Tile{
    public MessageCallBack messageCallBack;
    public Move moveCallBack;//todo change to private
    String Name;
    int AttackPoints;
    int DefencePoints;
    Health h;
    protected int TicksCount;
    public Unit(char c,Position pos,String Name,int AttackPoints, int DefencePoints,Health h)
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
        moveCallBack.move(empty.pos);
        return true;
    }
    public void setPosition(Position p) { this.pos=p;}
    public void updateTicks(){ return;}
    public String describe() {return String.format("Name: %s\t\tAttack Points: %d\t\tDefence Points: %d\t\tHealth: %s", Name, AttackPoints,DefencePoints , h.toString()); }
    public void initialize(MessageCallBack messageCallBack1){this.messageCallBack=messageCallBack1; }

    public MessageCallBack getMessageCallBack() {
        return messageCallBack;
    }

    public void setMessageCallBack(MessageCallBack messageCallBack) {
        this.messageCallBack = messageCallBack;
    }

    public Move getMoveCallBack() {
        return moveCallBack;
    }

    public void setMoveCallBack(Move moveCallBack) {
        this.moveCallBack = moveCallBack;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getAttackPoints() {
        return AttackPoints;
    }

    public void setAttackPoints(int attackPoints) {
        AttackPoints = attackPoints;
    }

    public int getDefencePoints() {
        return DefencePoints;
    }

    public void setDefencePoints(int defencePoints) {
        DefencePoints = defencePoints;
    }

    public Health getH() {
        return h;
    }

    public void setH(Health h) {
        this.h = h;
    }

    public int getTicksCount() {
        return TicksCount;
    }

    public void setTicksCount(int ticksCount) {
        TicksCount = ticksCount;
    }
}
