public class Enemy extends Unit{
    private boolean Movable=false;
    protected int ExperienceValue;
    public Enemy(char c,Position pos,String Name,int AttackPoints, int DefencePoints,Health h,int ExperienceValue)
    {
        super( c,pos, Name, AttackPoints, DefencePoints, h);
        this.ExperienceValue=ExperienceValue;
    }
    @Override
    public Enemy clone(){
        return new Enemy(this.c,this.pos,this.Name,this.AttackPoints,this.DefencePoints,this.h,this.ExperienceValue);
    }
}
