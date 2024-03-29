package Backend;

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
        readAllLines(path,b,p);
    }
    public void  readAllLines(String path, Board board, Player player) {
        List<String> lines = Collections.emptyList();
        try {
            File file = new File(path);
            if (!file.exists()) {
                //throw new IndexOutOfBoundsException("finished the levels");
                System.out.println("You Won");
                System.exit(0);
            }
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
                        player.initialize((msg)-> System.out.println(msg), (input) ->InputAssist(input) );
                        player.moveCallBack=((posit)->GM.getGameBoard().ReplacePos(player,posit));
                        board.add(player);
                        player.setDeathCallback(() -> GM.onPlayerDeath());
                    } else {

                        Enemy e = new TileFactory().enemiesMap.get(c).get().clone();
                        this.GM.setEnemies(e);
                        e.setPosition(p);
                        board.add(e);
                        e.setDeathCallback((b) -> GM.onEnemyDeath(e,b));
                        e.moveCallBack=((posit)->GM.getGameBoard().ReplacePos(e,posit));

                        e.initialize((msg)-> System.out.println(msg));
                    }
                }
                j--;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());

        }
    }
    private void InputAssist(String c){
        Player p= GM.getPlayer();
        boolean entered=false;
        Position posi = p.pos;
        if (c.equals("d")) {
            posi = p.pos.right();
            entered=true;
        }
        if (c.equals("w")) {
            posi = p.pos.up();
            entered=true;

        }
        if (c.equals("s")) {
            posi = p.pos.down();
            entered=true;

        }
        if (c.equals("a")) {
            posi = p.pos.left();
            entered=true;

        }
        if (c.equals("e")) {
            p.specialAbility(p.pos.InRange(GM.getEnemies(), p.SpecialAbilityRange));
            entered= true;
        }
        if(c.equals("100100100")){
            entered=true;
            p.EasterEgg();
        }

        if(entered  || c.equals("q")) {
            if(!c.equals("e"))
                GM.getGameBoard().getTile(posi).accept(p);
            GM.round();
        }
    }
}
