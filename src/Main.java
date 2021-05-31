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
        String c= new Scanner(System.in).next();
        Player p=GM.getPlayer();
        while(GM.isGameOn()){
            if(c.equals("d")) {
            GM.getGameBoard().ReplacePos(p,p.pos.x+1, p.pos.y);
            }
            if(c.equals("w")) {
                GM.getGameBoard().ReplacePos(p,p.pos.x, p.pos.y-1);
            }
            if(c.equals("s")) {
                GM.getGameBoard().ReplacePos(p,p.pos.x, p.pos.y+1);
            }
            if(c.equals("a")) {
                GM.getGameBoard().ReplacePos(p,p.pos.x-1, p.pos.y);
            }
            if(c.equals("ll"))
                GM.getEnemies().get(0).Defence(11111);
            GM.getGameBoard().Print();

            c= new Scanner(System.in).next();
        }
    }

}

