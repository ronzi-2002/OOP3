public class Wall extends Tile{
    private boolean Movable=false;

    public Wall(Position pos)
        {
            super('#',pos);
        }
    public boolean isMovable() {
        return Movable;
    }

}
