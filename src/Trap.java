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
        if(TicksCount==(visibilityTime+InvisibilityTime))
            TicksCount=0;
        visible=TicksCount<visibilityTime;
    }

    @Override
    public void Combat(Player player) {
        if(this.pos.Range(player.pos)<2) {
            int x = (int) (Math.random() * (AttackPoints + 1));
            player.Defence(x);
        }
    }

    @Override
    public void updateTicks() {
        visibleToInvisible();
        this.TicksCount++;
    }

    @Override
    public Enemy clone(){
        return new Trap(this.c,this.h,this.Name,this.pos,this.AttackPoints,this.DefencePoints,this.ExperienceValue,this.visibilityTime,this.InvisibilityTime);
    }
}
