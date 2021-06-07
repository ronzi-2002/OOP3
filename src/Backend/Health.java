package Backend;

public class Health {
    int HealthPool;
    int HealthAmount;
    public Health(int HealthPool)
    {
        this.HealthPool=HealthPool;
        this.HealthAmount=HealthPool;
    }
    public boolean DecreaseHealth(int damageToDeal){
        boolean isDead=false;
        if(HealthAmount-damageToDeal>0)
            this.HealthAmount=HealthAmount-damageToDeal;
        else{
            HealthAmount=0;
            isDead=true;
        }
        return isDead;
    }
    public void IncreaseHealthAmount(int healthAdd){
        if(HealthAmount+healthAdd>HealthPool)
            HealthAmount=HealthPool;
        else
            HealthAmount=HealthAmount+healthAdd;
    }
    public String toString()
    {
        return HealthAmount+"/"+HealthPool;
    }

    public int getHealthPool() {
        return HealthPool;
    }

    public void setHealthPool(int healthPool) {
        HealthPool = healthPool;
    }

    public int getHealthAmount() {
        return HealthAmount;
    }

    public void setHealthAmount(int healthAmount) {
        HealthAmount = healthAmount;
    }
}
