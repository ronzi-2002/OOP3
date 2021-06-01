import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Player[] players = {new Warrior(null, "Jon Snow", 30, 4, new Health(300), 3)};
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].describe());
        }
        GameManager GM = new GameManager(args[0]);
        boolean VaildValue = false;

        while (!VaildValue) {
            try {
                //int index = Integer.parseInt(System.console().readLine());//todo check if vaild number
                int index = new Scanner(System.in).nextInt();
                Player p = players[index];
                VaildValue = true;
                Board b = new Board();
                GM.setPlayer(p);
                GM.setGameBoard(b);
                GM.nextLevel();
                //new GameInitializer(args[0] + "\\level1.txt", b, p,GM);
            } catch (
                    Exception e) {
                System.out.println("Not a vaild number \n enter again");
            }
        }
        GM.getGameBoard().Print();
        Player p=GM.getPlayer();
        while(GM.isGameOn()){
          String c= new Scanner(System.in).next();
            if(c.equals("d")) {
                if(GM.getGameBoard().getTile(p.pos.right()).accept(p))
                    GM.getGameBoard().ReplacePos(p,p.pos.right());
            }
            if(c.equals("w")) {
                if(GM.getGameBoard().getTile(p.pos.up()).accept(p))
                    GM.getGameBoard().ReplacePos(p,p.pos.up());
            }
            if(c.equals("s")) {
                if(GM.getGameBoard().getTile(p.pos.down()).accept(p))
                   GM.getGameBoard().ReplacePos(p,p.pos.down());
            }
            if(c.equals("a")) {
                if(GM.getGameBoard().getTile(p.pos.left()).accept(p))
                    GM.getGameBoard().ReplacePos(p,p.pos.left());
            }
            if(c.equals("ll"))
                GM.getEnemies().get(0).Defence(11111);
            GM.MoveAllEnemies();
            GM.getGameBoard().Print();
        }
        System.out.println();
        System.out.println("Game over");
    }

}

