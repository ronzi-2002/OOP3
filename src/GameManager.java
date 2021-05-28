import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class GameManager {
    Board board;//todo this is only for testing the loading
    public static void main(String[]args){
        readAllLines(args[0]+"\\level1.txt").Print();


    }
    public void LevelIntializer()
    {
        //readAllLines()
    }
    public static Board readAllLines(String path) {
        List<String> lines = Collections.emptyList();
        Board board=new Board();
        try {
            lines = Files.readAllLines(Paths.get(path));
            int j=0;
            for(String line:lines)
            {
                for (int i=0;i<line.length();i++)
                {
                    char c=line.charAt(i);
                    Position p=new Position(i,j);
                    if(c=='#')
                        board.add(new Wall(p));
                    else if(c=='.')
                        board.add(new Empty(p));
                   // else if(c=='@')
                   //     board.add(new Player(p));
                    //else
                        //board.add(new Enemy(p));
                    else
                        board.add(new Empty(p));//todo temporary
                }
                j++;
            }
        } catch (IOException e) {
            System.out.println(e.getMessage() + "\n" +
                    e.getStackTrace());
        }
        return board;

    }

}
