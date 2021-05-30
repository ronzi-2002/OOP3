public class Rouge extends Player{
    private int cost;
    private int CurrentEnergy;
    public Rouge(Position pos, String Name, int AttackPoints, int DefencePoints, Health h, int experience, int playerLevel,int CurrentEnergy,int cost) {
        super(pos, Name, AttackPoints, DefencePoints, h);
        this.cost=cost;
        this.CurrentEnergy=CurrentEnergy;
    }
}
