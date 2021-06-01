import java.util.ArrayList;
import java.util.List;

public class Board {
    ArrayList<Tile> tiles;

    public Board() {
        tiles = new ArrayList<>();
    }

    public void add(Tile t) {
        tiles.add(t);
    }

    public void ReplacePos(Tile p,Position position) {
        for (Tile T : tiles) {
            if (T.pos.x == position.x & T.pos.y == position.y) {
                if (p.pos.ReplacePos(T)) {
                    int ind = tiles.indexOf(p);
                    tiles.set(tiles.indexOf(T), p);
                    tiles.set(ind, T);
                }
            }
        }
    }

    public void Print() {
        Tile TPrev = tiles.get(0);
        System.out.println(TPrev.pos.y);
        for (int i = 0; i < tiles.size(); i++) {
            Tile T = tiles.get(i);
            if (T.pos.y != TPrev.pos.y) {
                System.out.println("");

            }

            System.out.print(T.c);
            TPrev = T;


        }
    }

    public Tile getTile(Position pos) {
        for (Tile T : tiles) {
            if (T.pos.x == pos.x & T.pos.y == pos.y) {
                return T;
            }
        }
        return null;
    }
}