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
        else
            isDead=true;
        return isDead;
    }
}
