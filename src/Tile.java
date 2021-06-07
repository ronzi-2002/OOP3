public abstract class Tile {
    protected char c;
    Position pos;
    protected boolean visible;
    //protected boolean Movable;
    public Tile(char c,Position pos)
    {
        this.c=c;
        this.pos=pos;
        //Movable=false;
        visible=true;
    }
    public abstract boolean accept(Unit unit);
    public char getC() {
        return c;
    }
    //public boolean isMovable() {
       // return Movable;
    //}
}
