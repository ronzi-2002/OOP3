import java.util.LinkedList;
import java.util.List;

public class Position {
    int x;
    int y;
    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public List<Enemy> InRange(List<Enemy> enemies,int range){
        List <Enemy> enemiesList= new LinkedList<>();
        for (Enemy e: enemies) {
            if(Range(e.pos)<range)
                enemiesList.add(e);
        }
        return enemiesList;
    }
    public double Range(Position position){
       return Math.sqrt(Math.pow(this.x- position.x,2)+Math.pow(this.y- position.y,2));
    }
    public boolean ReplacePos(Tile replacement){
        int temp=this.x;
        this.x=replacement.pos.x;
        replacement.pos.x=temp;
        temp=this.y;
        this.y=replacement.pos.y;
        replacement.pos.y=temp;
        return true;
    }
    public Position left()
    {
        return new Position(x-1,y);
    }
    public Position right()
    {
        return new Position(x+1,y);
    }
    public Position up()
    {
        return new Position(x,y+1);
    }
    public Position down()
    {
        return new Position(x,y-1);
    }

}
