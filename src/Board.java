import java.util.ArrayList;
import java.util.List;

public class Board {
    ArrayList<Tile> tiles;
     public Board()
     {
        tiles=new ArrayList<>();
     }
     public void add(Tile t)
     {
         tiles.add(t);
     }

    public boolean ReplacePos(Tile p,int x ,int y){
        for (Tile T:tiles) {
            if(T.pos.x==x & T.pos.y==y ) {
                if (p.pos.ReplacePos(T)) {
                    int ind = tiles.indexOf(p);
                    tiles.set(tiles.indexOf(T), p);
                    tiles.set(ind,T);
                }
            }
        }
        return false;
    }
    public void Print() {
        Tile TPrev=tiles.get(0);
        System.out.println(TPrev.pos.y);
        for (int i = 0; i < tiles.size(); i++) {
            Tile T=tiles.get(i);
            if(T.pos.y!=TPrev.pos.y) {
                System.out.println("");

            }

            System.out.print(T.c);
            TPrev=T;


        }
    }
}
