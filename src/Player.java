public  class Player extends Unit{
    int Experience;
    int PlayerLevel;
    public Player(Position pos,String Name,int AttackPoints, int DefencePoints,Health h)
    {
        super('@',pos, Name, AttackPoints, DefencePoints, h);
        this.Experience=0;
        this.PlayerLevel=1;
    }
    public void accept(Unit u){
    }

    public void visit(Enemy e){
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
//
//    private int levelUpRequirement(){
//        return REQ_EXP * level;
//    }
//
//    public int getLevel() {
//        return level;
//    }
//    public int getExperience() {
//        return experience;
//    }
//
//    public void visit(Player p){
//
//    }
//
//    public String describe() {
//        return String.format("%s\t\tLevel: %d\t\tExperience: %d/%d", super.describe(), getLevel(), getExperience(), levelUpRequirement());
//    }
    public String describe(){
        return "pickle rick";
    }
}
