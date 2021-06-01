public class Trap extends Enemy {
    private int visibilityTime;
    private int InvisibilityTime;
    private int TicksCount;
    private boolean Movable=false;
    public Trap(char c, Health h ,String Name, Position pos, int AttackPoints, int DefencePoints,int ExperienceValue,int VisibilityTime,int InvisibilityTime) {
        super(c ,pos, Name, AttackPoints, DefencePoints, h,ExperienceValue);
        this.visibilityTime=VisibilityTime;
        this.InvisibilityTime=InvisibilityTime;
        this.TicksCount=0;
    }
    public void visibleToInvisible(){
        visible=TicksCount<visibilityTime;
        if(TicksCount==(visibilityTime+InvisibilityTime))
            TicksCount=0;
    }

    @Override
    public void updateTicks() {
        this.TicksCount++;
        visibleToInvisible();
    }

    @Override
    public Enemy clone(){
        return new Trap(this.c,this.h,this.Name,this.pos,this.AttackPoints,this.DefencePoints,this.ExperienceValue,this.visibilityTime,this.InvisibilityTime);
    }
}
