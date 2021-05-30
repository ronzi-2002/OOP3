public class Tile {
    char c;
    Position pos;
    private boolean Movable;
    public Tile(char c,Position pos)
    {
        this.c=c;
        this.pos=pos;
    }

    public boolean isMovable() {
        return Movable;
    }
}
