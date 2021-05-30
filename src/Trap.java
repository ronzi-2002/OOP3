public class Trap extends Enemy {
    private int visibilityTime;
    private int InvisibilityTime;
    private int TicksCount;
    private boolean visible;
    private boolean Movable=false;
    public Trap(char c ,Position pos, String Name, int AttackPoints, int DefencePoints, Health h,int VisibilityTime,int InvisibilityTime,int ExperienceValue) {
        super(c ,pos, Name, AttackPoints, DefencePoints, h,ExperienceValue);
        this.visibilityTime=VisibilityTime;
        this.InvisibilityTime=InvisibilityTime;
        this.TicksCount=0;
    }

    public boolean isVisible() {
        return visible;
    }


    @Override
    public Enemy clone(){
        return new Trap(this.c,this.pos,this.Name,this.AttackPoints,this.DefencePoints,this.h,this.ExperienceValue,this.InvisibilityTime,this.visibilityTime);
    }
}
