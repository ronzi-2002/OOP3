import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        //Player[] players = {new Warrior(null, "Jon Snow", 30, 4, new Health(300), 3)};
        TileFactory T=new TileFactory();
        for (int i = 0; i < T.listPlayers().size(); i++) {
            System.out.println(i+1+".  "+ T.listPlayers().get(i).describe());
        }
        GameManager GM = new GameManager(args[0]);
        GM.SetMode("g");
        boolean VaildValue = false;
        while (!VaildValue) {
            try {
                //int index = Integer.parseInt(System.console().readLine());//todo check if vaild number
                int index = new Scanner(System.in).nextInt();
                Player p = new TileFactory().listPlayers().get(index-1);
                VaildValue = true;
                Board b = new Board();
                GM.setPlayer(p);
                GM.setGameBoard(b);
                GM.nextLevel();
                //new GameInitializer(args[0] + "\\level1.txt", b, p,GM);
            } catch (Exception e) {
                System.out.println("Not a vaild number \n enter again");
            }
        }
        GM.getGameBoard().Print();
        Player p=GM.getPlayer();
        while(GM.isGameOn()) {
            String moves = new Scanner(System.in).nextLine();
            if (moves.equals("Tal Barami")) {
                p.messageCallBack.Print("u are close... try to think what they do in the ancient jewdaisim to convert string to int");
            }else if (moves.equals("292")) {
                p.messageCallBack.Print("u are close but think like a computer wink wink ");
            } else {
                String[] arr = moves.split(" ");
                for (int i = 0; i < arr.length && GM.isGameOn(); i++) {
                    String c = arr[i];
                    p.inputProvider.getAction(c);
                }
            }
        GM.getGameBoard().Print();
        System.out.println(p.describe());
        }
        System.out.println();
        System.out.println("Game over");
    }
//    public static void print(Board b)
//    {
//
//    }

}

