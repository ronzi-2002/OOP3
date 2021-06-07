package Backend;

public abstract class Tile {
    protected char c;
    public Position pos;
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

    public void setC(char c) {
        this.c = c;
    }

    public Position getPos() {
        return pos;
    }

    public void setPos(Position pos) {
        this.pos = pos;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    //public boolean isMovable() {
       // return Movable;
    //}
}
