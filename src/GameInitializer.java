import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GameInitializer {
    Board board;//todo this is only for testing the loading
    GameManager GM;
   //public static void main(String[]args){
   //    readAllLines(args[0]+"\\level1.txt").Print();


   //}
    public HashMap<Character,Enemy>enemies;
    public GameInitializer(String path,Board b,Player p,GameManager GM)
    {
        this.GM=GM;
        this.enemies=new HashMap<Character,Enemy>();
        Health h=new Health(80);
        new Monster('s',null,"Lannister Solider",8000000,3,h,3,25);
        this.enemies.put('s',new Monster('s',null,"Lannister Solider",8,3,h,3,25));
        this.enemies.put('k',new Monster('k',null,"Lannister Knight",14,8,h,4,50));
        this.enemies.put('q',new Monster('q',null," Knight",14,8,h,4,50));
        this.enemies.put('M',new Monster('M',null," Knight",14,8,h,4,50));
        this.enemies.put('C',new Monster('C',null," Knight",14,8,h,4,50));
        this.enemies.put('Q',new Trap('Q',null,"Lannister ",14,8,h,4,50,44));
        this.enemies.put('B',new Trap('B',null,"a",14,8,h,4,50, 7));
        readAllLines(path,b,p);
    }
    public void  readAllLines(String path, Board board, Player player) {
        List<String> lines = Collections.emptyList();
        try {
            lines = Files.readAllLines(Paths.get(path));
            LinkedList<Enemy> enemies= new LinkedList<Enemy>();
            int j = lines.size();
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
                        player.setDeathCallback(() -> GM.onPlayerDeath());
                    } else {
                        Enemy e =this.enemies.get(c).clone();
                        this.GM.setEnemies(e);
                        e.setPosition(p);
                        board.add(e);
                        e.setDeathCallback(() -> GM.onEnemyDeath(e));
                    }
                }
                j--;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
    }
}
