public  class Player extends Unit{
    int Experience;
    int PlayerLevel;
    public Player(Position pos,String Name,int AttackPoints, int DefencePoints,Health h, int experience, int playerLevel)
    {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.Experience=experience;
        this.PlayerLevel=playerLevel;
    }
    //public abstract void SpecialAbility();

}
