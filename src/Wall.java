public class Wall extends Tile{


    public Wall(Position pos)
        {
            super('#',pos);

        }

    @Override
    public boolean accept(Unit unit) {
        return unit.Visit(this);

    }
}
