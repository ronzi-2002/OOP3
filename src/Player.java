import java.util.List;

public abstract class Player extends Unit{

    int Experience;
    int PlayerLevel;
    protected int SpecialAbilityRange;
    protected PlayerDeathCallback deathCallback;
    public InputProvider inputProvider ;
    public Player(Position pos,String Name,int AttackPoints, int DefencePoints,Health h)
    {
        super('@',pos, Name, AttackPoints, DefencePoints, h);
        this.Experience=0;
        this.PlayerLevel=1;
    }
    public void setDeathCallback(PlayerDeathCallback deathCallback) {
        this.deathCallback=deathCallback;
    }

    public void Defence(int Damage){
        int d = (int) (Math.random()*this.DefencePoints)+1;
        if(Damage-d>0){
            if (this.h.DecreaseHealth(Damage))
                this.deathCallback.call();

        }
    }
    public abstract void specialAbility(List<Enemy> enemiesInRange);


    public boolean accept(Unit u){
        return u.Visit(this);
    }
    //    public ? getAction(){
//        return inputProvider.getAction();
//    }
//
//    // Deals damage to the enemy with ability
//    protected void abilityDamage(Enemy e, int abilityDamage) {
//        e.
//    }
//
//    // When the player kills an enemy
//    protected void onKill(Enemy e){
//    }
//
//    // When the player dies
//    @Override
//    public void onDeath() {
//        messageCallback.send("You lost.");
//        // Use deathCallback to alert the level manager
//        deathCallback.call();
//    }
//
//    // Player level up
//    protected void levelUp(){
//		...
//    }
//
//    @Override
//    public String toString() {
//        return alive() ? super.toString() : "X";
//    }
//
//    // Health / attack / defense gain when the player levels up - should be overriden by player subclasses and call super.gainHealth() for the base amount, then add the extra value
//    protected int gainHealth(){
//        return level * HEALTH_BONUS;
//    }
//    protected int gainAttack(){
//        return level * ATTACK_BONUS;
//    }
//    protected int gainDefense(){
//        return level * DEFENSE_BONUS;
//    }

   private int levelUpRequirement(){
       return 50 * PlayerLevel;
   }
//
    public int getLevel() {
        return PlayerLevel;
    }
    public int getExperience() {
        return Experience;
    }
//
//    public void visit(Player p){
//
//    }
//
        public void LevelUp(){
            this.Experience-=this.levelUpRequirement();
            PlayerLevel+=1;
            h.HealthPool+=10*PlayerLevel;
            h.HealthAmount=h.HealthAmount;
            AttackPoints+=4*PlayerLevel;
            DefencePoints+=PlayerLevel;
        }
        public void AddExperience(int Experience){
            this.Experience+=Experience;
            if(this.Experience>=this.levelUpRequirement()){
                LevelUp();
            }
        }
        public String describe() {
            return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
        }

    public boolean Combat(Enemy e) {
        int Damage=(int) (Math.random() * (this.AttackPoints + 1));
        return e.Defence(Damage);
    }
    public boolean Visit(Enemy e) {
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
}
