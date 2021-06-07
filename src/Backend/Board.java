package Backend;

import java.util.ArrayList;

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
                    if(ind!=-1)
                        tiles.set(ind, T);
                    return;
                }
            }
        }
    }

    public void Print() {
        Tile TPrev = tiles.get(0);
        for (int i = 0; i < tiles.size(); i++) {
            Tile T = tiles.get(i);
            if (T.pos.y != TPrev.pos.y) {
                System.out.println("");

            }
            if(T.visible)
            System.out.print(T.c);
            else
                System.out.print(new Empty(T.pos).c);
            TPrev = T;


        }
        System.out.println();
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