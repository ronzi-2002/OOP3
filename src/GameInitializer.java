import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class GameInitializer {
    Board board;//todo this is only for testing the loading
   //public static void main(String[]args){
   //    readAllLines(args[0]+"\\level1.txt").Print();


   //}
    public HashMap<Character,Enemy>enemies;
    public GameInitializer(String path,Board b,Player p)
    {
        this.enemies=new HashMap<Character,Enemy>();
        this.enemies.put('s',new Monster('s',null,"Lannister Solider",8,3,new Health(80),3,25));
        this.enemies.put('k',new Monster('k',null,"Lannister Knight",14,8,new Health(200),4,50));
        this.enemies.put('q',new Monster('q',null,"Lannister Knight",14,8,new Health(200),4,50));
        this.enemies.put('Q',new Trap('Q',null,"Lannister Knight",14,8,new Health(200),4,50,44));
        this.enemies.put('B',new Trap('B',null,"Lannister Knight",14,8,new Health(200),4,50, 7));
        readAllLines(path,b,p);
    }
    public void  readAllLines(String path, Board board, Player player) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
            int j = 0;
            for (String line : lines) {
                for (int i = 0; i < line.length(); i++) {
                    char c = line.charAt(i);
                    Position p = new Position(i, j);
                    if (c == '#')
                        board.add(new Wall(p));
                    else if (c == '.')
                        board.add(new Empty(p));
                    else if (c == '@') {
                        player.setPosition(p);
                        board.add(player);
                    } else {
                        Enemy clone =this.enemies.get(c).clone();
                        clone.setPosition(p);
                        board.add(clone);
                    }
                }
                j++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
    }
}
