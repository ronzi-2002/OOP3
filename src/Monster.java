public class Monster extends Enemy {
    private int VisionRange;
    public Monster(char c,Position pos, String Name, int AttackPoints, int DefencePoints, Health h, int VisionRange,int ExperienceValue) {
        super(c,pos, Name, AttackPoints, DefencePoints, h,ExperienceValue);
        this.VisionRange=VisionRange;
    }
    @Override
    public Enemy clone(){
        return new Monster(this.c,this.pos,this.Name,this.AttackPoints,this.DefencePoints,this.h,this.VisionRange,this.ExperienceValue);
    }
}
