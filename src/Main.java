import java.util.Scanner;

public class Main {
    public static  void main(String[]args)
    {
        Player [] players={new Warrior(null,"Jon Snow",30,4,new Health(300),3)};
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].describe());
        }
        GameManager GM=new GameManager();
        boolean VaildValue=false;

        while(!VaildValue) {
            try {
                //int index = Integer.parseInt(System.console().readLine());//todo check if vaild number
                int index= new Scanner(System.in).nextInt();
                Player p = players[index];
                VaildValue=true;
                Board b=new Board();
                GM.setPlayer(p);
                new GameInitializer(args[0]+"\\level1.txt",b,p);
                GM.setGameBoard(b);
            } catch(Exception e)
            {
                System.out.println("Not a vaild number \n enter again");
            }
        }
        GM.getGameBoard().Print();

    }
}
