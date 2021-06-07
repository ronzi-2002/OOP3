package Backend;

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
        int Damage=this.Combat(player);
        messageCallBack.Print(String.format("%s rolled %d attack points" ,this.Name ,Damage));
        int Caused=player.Defence(Damage);
        if(Caused!=-1)
            messageCallBack.Print(String.format("%s dealt %d damage to %s " ,this.Name,Caused,player.Name));
        return true;
    }


    public Position Move(Player player){
        this.updateTicks();
        if(this.pos.Range(player.pos)<2)
            return player.pos;
        else
            return this.pos;
    }
    @Override
    public abstract Enemy clone();

    public int Defence(int Damage,boolean IsSpecialAttack){
        int d =  NumericGenrator.getInstance().NextInt(0,this.DefencePoints);
        messageCallBack.Print(String.format("%s rolled %d defence points",this.Name,d));
        if((Damage-d)>0){
                if (this.h.DecreaseHealth(Damage-d)){
                    this.enemyDeathCallBack.call(IsSpecialAttack);
            }
            return Damage-d;
        }
        return 0;

    }


    public void setDeathCallback(EnemyDeathCallBack enemyDeathCallBack) {
        this.enemyDeathCallBack=enemyDeathCallBack;
    }

    @Override
    public boolean accept(Unit unit) {
       return unit.Visit(this);
    }
    public abstract int Combat(Player player);
    public void initialize(MessageCallBack messageCallBack1) {
        super.initialize(messageCallBack1);
        this.enemyDeathCallBack=enemyDeathCallBack;
        return ;
    }
}
