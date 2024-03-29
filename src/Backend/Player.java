package Backend;

import java.util.List;

public abstract class Player extends Unit{
    protected static final int REQ_EXP = 50;
    protected static final int ATTACK_BONUS = 4;
    protected static final int DEFENCE_BONUS = 1;
    protected static final int HEALTH_BONUS = 10;
    int Experience;
    int PlayerLevel;
    protected int SpecialAbilityRange;
    protected PlayerDeathCallback deathCallback;
    public InputProvider inputProvider ;
    //private boolean wasSpecialAbillity;
    public Player(Position pos,String Name,int AttackPoints, int DefencePoints,Health h)
    {
        super('@',pos, Name, AttackPoints, DefencePoints, h);
        this.Experience=0;
        this.PlayerLevel=1;
        //wasSpecialAbillity=false;
    }
    public void setDeathCallback(PlayerDeathCallback deathCallback) {
        this.deathCallback=deathCallback;
    }

    public int Defence(int Damage){
        int d = NumericGenrator.getInstance().NextInt(0,this.DefencePoints);
        messageCallBack.Print(String.format("%s rolled %d defence points",this.Name,d));
        if(Damage-d>0){
            if (this.h.DecreaseHealth(Damage-d)) {
                this.deathCallback.call();
                return -1;
            }
            return Damage-d;
        }
        return 0;
    }
    public abstract void specialAbility(List<Enemy> enemiesInRange);


    public boolean accept(Unit u){
        return u.Visit(this);
    }
    public int gainHealth(){
        return PlayerLevel * HEALTH_BONUS;

    }
    public int gainAttack(){
        return PlayerLevel * ATTACK_BONUS;
    }
    public int gainDefence(){
        return PlayerLevel * DEFENCE_BONUS;
    }

   public int levelUpRequirement(){
       return REQ_EXP * PlayerLevel;
   }

    public int getLevel() {
        return PlayerLevel;
    }
    public int getExperience() {
        return Experience;
    }

        public void LevelUp(){
            this.Experience-=this.levelUpRequirement();
            PlayerLevel+=1;
            this.h.HealthPool+=gainHealth();
            this.h.HealthAmount=this.h.HealthPool;
            this.AttackPoints+=gainAttack();
            this.DefencePoints+=gainDefence();
            messageCallBack.Print(String.format("%s reached level %d: +%d health, +%d attack points, +%d defence points ",this.Name,this.PlayerLevel,gainHealth(),gainAttack(),gainDefence()));
        }
        public void AddExperience(int Experience){
            this.Experience+=Experience;
            while(this.Experience>=this.levelUpRequirement()){
                LevelUp();
            }
        }
        public String describe() {
            return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
        }

    public boolean Combat(Enemy e) {
        int Damage=NumericGenrator.getInstance().NextInt(0,AttackPoints);
        messageCallBack.Print(String.format("%s rolled %d attack points" ,this.Name ,Damage));
        int Caused=e.Defence(Damage,false);
        messageCallBack.Print(String.format("%s dealt %d damage to %s " ,this.Name,Caused,e.Name));

        return true;
    }
    public boolean Visit(Enemy e) {
        messageCallBack.Print(String.format("%s engaged to fight with %s" ,this.Name ,e.Name));
        return this.Combat(e);

    }

    public void Dead(){
        this.c='X';
    }
    public boolean Visit(Player player) {
        return true;
    }

    public void SetInputProvider(InputProvider inputProvider) {
        this.inputProvider=inputProvider;
    }
    public Player initialize(MessageCallBack messageCallBack1,InputProvider inputProvider) {
        super.initialize(messageCallBack1);
        this.inputProvider=inputProvider;
        return this;
    }
    public void EasterEgg(){
        this.Name="Tal Barami";
        this.AttackPoints+=1000000;
        this.DefencePoints+=1000000;
        this.messageCallBack.Print("U just became  the legend \"Tal Barami\"");
    }

    public void setExperience(int experience) {
        Experience = experience;
    }
}
