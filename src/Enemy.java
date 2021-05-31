public class Enemy extends Unit{
    private boolean Movable=false;
    protected int ExperienceValue;
    protected EnemyDeathCallBack enemyDeathCallBack;
    public Enemy(char c,Position pos,String Name,int AttackPoints, int DefencePoints,Health h,int ExperienceValue)
    {
        super( c,pos, Name, AttackPoints, DefencePoints, h);
        this.ExperienceValue=ExperienceValue;
    }
    @Override
    public Enemy clone(){
        return new Enemy(this.c,this.pos,this.Name,this.AttackPoints,this.DefencePoints,this.h,this.ExperienceValue);
    }
    public void Defence(int Damage){
        int d = (int) (Math.random()*this.DefencePoints)+1;
        if(Damage>0){
            if (this.h.DecreaseHealth(Damage))
                this.enemyDeathCallBack.call();

        }

    }

    public void setDeathCallback(EnemyDeathCallBack enemyDeathCallBack) {
        this.enemyDeathCallBack=enemyDeathCallBack;
    }
}
