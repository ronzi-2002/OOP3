public class Tile {
    char c;
     Position pos;
    protected boolean Movable;
    public Tile(char c,Position pos)
    {
        this.c=c;
        this.pos=pos;
        Movable=false;
    }

    public char getC() {
        return c;
    }

    public boolean isMovable() {
        return Movable;
    }
}
