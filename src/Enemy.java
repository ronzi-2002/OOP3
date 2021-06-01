public class Enemy extends Unit{
    private boolean Movable=false;
    protected int ExperienceValue;
    protected EnemyDeathCallBack enemyDeathCallBack;
    public Enemy(char c,Position pos,String Name,int AttackPoints, int DefencePoints,Health h,int ExperienceValue)
    {
        super( c,pos, Name, AttackPoints, DefencePoints, h);
        this.ExperienceValue=ExperienceValue;
    }
    public boolean Visit(Enemy enemy){
        return false;
    }

    @Override
    public boolean Visit(Player player) {
        this.Combat(player);
        return true;
    }


    public Position Move(Player player){
        this.updateTicks();
        return this.pos;
    }
    @Override
    public Enemy clone(){
        return new Enemy(this.c,this.pos,this.Name,this.AttackPoints,this.DefencePoints,this.h,this.ExperienceValue);
    }
    public boolean Defence(int Damage){
        int d = (int) (Math.random()*(this.DefencePoints+1));
        if((Damage-d)>0){
                if (this.h.DecreaseHealth(Damage-d)){
                    this.enemyDeathCallBack.call();
                    return true;
            }
        }
        return false;

    }

    public void setDeathCallback(EnemyDeathCallBack enemyDeathCallBack) {
        this.enemyDeathCallBack=enemyDeathCallBack;
    }

    @Override
    public boolean accept(Unit unit) {
       return unit.Visit(this);
    }
    public void Combat(Player player){
        player.Defence((int) (Math.random()*(AttackPoints+1)));
    }
}
