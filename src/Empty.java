public class Empty extends Tile{
    private boolean Movable=true;

    public Empty(Position pos)
    {
        super('.',pos);
        Movable=true;
    }
}
