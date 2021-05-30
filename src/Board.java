import java.util.ArrayList;
import java.util.List;

public class Board {
     List<Tile> tiles;
     public Board()
     {
        tiles=new ArrayList<>();
     }
     public void add(Tile t)
     {
         tiles.add(t);
     }


    public void Print() {
        Tile TPrev=tiles.get(0);
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
