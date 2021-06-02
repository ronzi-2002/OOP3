public abstract class Enemy extends Unit{
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
        messageCallBack.Print(String.format("%s engaged to fight with %s" ,this.Name ,player.Name));
        this.Combat(player);
        return true;
    }


    public Position Move(Player player){
        this.updateTicks();
        return this.pos;
    }
    @Override
    public abstract Enemy clone();
    public boolean Defence(int Damage){
        int d = (int) (Math.random()*(this.DefencePoints+1));
        messageCallBack.Print(String.format("%s rolled %d defence points",this.Name,d));
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
    public abstract void Combat(Player player);
    public void initialize(MessageCallBack messageCallBack1) {
        super.initialize(messageCallBack1);
        this.enemyDeathCallBack=enemyDeathCallBack;
        return ;
    }
}
