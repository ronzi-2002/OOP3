public class Monster extends Enemy {
    private int VisionRange;
    public Monster(char c,Position pos, String Name, int AttackPoints, int DefencePoints, Health h, int VisionRange,int ExperienceValue) {
        super(c,pos, Name, AttackPoints, DefencePoints, h,ExperienceValue);
        this.VisionRange=VisionRange;
    }
    @Override
    public Enemy clone(){
        return new Monster(this.c,this.pos,this.Name,this.AttackPoints,this.DefencePoints,this.h,this.VisionRange,this.ExperienceValue);
    }
    @Override
    public Position Move(Player player){
        double Range = this.pos.Range(player.pos);
        if(Range!=1.0) {
            if (Range <= this.VisionRange) {
                double dx = (this.pos.x - player.pos.x);
                double dy = (this.pos.y - player.pos.y);
                if (Math.abs(dx) > Math.abs(dy)) {
                    if (dx > 0)
                        return this.pos.left();
                    else
                        return this.pos.right();

                } else {
                    if (dy > 0)
                        return this.pos.down();
                    else
                        return this.pos.up();
                }
            } else {
                return this.RandomPos();
            }
        }else{
            this.Combat(player);
            return this.pos;
        }
    }
    private Position RandomPos(){
        double x= Math.random();
        if (x<0.4) {
            x = Math.random();
            if(x<0.5)
                return this.pos.right();
            else
                return this.pos.left();
        }else if (x>=0.6){
            x = Math.random();
            if(x<0.5)
                return this.pos.up();
            else
                return this.pos.down();
        }
        return this.pos;
    }
}
