package Backend;

public class Empty extends Tile{
    public Empty(Position pos)
    {
        super('.',pos);
    }
    @Override
    public boolean accept(Unit unit) {
        return unit.Visit(this);

    }
}
