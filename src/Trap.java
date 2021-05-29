public class Trap extends Enemy {
    private int visibilityTime;
    private int InvisibilityTime;
    private int TicksCount;
    private boolean visible;
    private boolean Movable=false;
    public Trap(Position pos, String Name, int AttackPoints, int DefencePoints, Health h,int VisibilityTime,int InvisibilityTime) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.visibilityTime=VisibilityTime;
        this.InvisibilityTime=InvisibilityTime;
        this.TicksCount=0;
    }

    public boolean isVisible() {
        return visible;
    }


}
