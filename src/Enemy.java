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
        messageCallBack.Print(String.format("%s engaged to fight with %s" ,this.Name ,player.Name));
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
    public void Combat(Player player){
        int Damage=(int) (Math.random()*(AttackPoints+1));
        messageCallBack.Print(String.format("%s rolled %d attack points" ,this.Name ,Damage));
        player.Defence(Damage);
    }
    public void initialize(MessageCallBack messageCallBack1) {
        super.initialize(messageCallBack1);
        this.enemyDeathCallBack=enemyDeathCallBack;
        return ;
    }
}
