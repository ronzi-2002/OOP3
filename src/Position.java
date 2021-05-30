public class Position {
    int x;
    int y;
    public Position(int x,int y)
    {
        this.x=x;
        this.y=y;
    }
    public boolean ReplacePos(Tile replacement){
        if(!replacement.isMovable())
            return false;
        int temp=this.x;
        this.x=replacement.pos.x;
        replacement.pos.x=temp;
        temp=this.y;
        this.y=replacement.pos.y;
        replacement.pos.y=temp;
        return true;
    }

}
