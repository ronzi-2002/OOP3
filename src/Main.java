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
          String moves= new Scanner(System.in).nextLine();
          String [] arr=moves.split(" ");
            for (int i = 0; i < arr.length && GM.isGameOn(); i++) {
               String c=arr[i];
                Position posi = null;
                boolean entered=false;
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
                    entered = true;
                }
                if(entered) {
                    if (GM.getGameBoard().getTile(posi).accept(p))
                        GM.getGameBoard().ReplacePos(p, posi);
                    GM.round();
                }
            }
                System.out.println(p.describe());
                GM.getGameBoard().Print();
        }
        System.out.println();
        System.out.println("Game over");
    }
    public void PlayerMovement(String c){

    }
}

