public  class Player extends Unit{
    int Experience;
    int PlayerLevel;
    public Player(Position pos,String Name,int AttackPoints, int DefencePoints,Health h)
    {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.Experience=0;
        this.PlayerLevel=1;
    }
    public void setPosition(Position p)
    {
        this.pos=p;
    }
    //public abstract void SpecialAbility();
    public String describe()
    {
        return Name;
    }

}
