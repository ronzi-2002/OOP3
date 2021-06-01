import java.util.LinkedList;
import java.util.List;

public class GameManager {
//    public class TicksCount{
//        private Integer Tickscount;
//        private TicksCount(){
//            Tickscount=0;
//        }
//
//        public int getTickscount() {
//            if(Tickscount==null) new TicksCount();
//            return Tickscount;
//        }
//
//        public void setTickscount(int tickscount) {
//            Tickscount = tickscount;
//        }
//    }
    public int TicksCount=0;
    private Board gameBoard;
    private Player player;
    private List<Enemy> enemies=new LinkedList<Enemy>();
    int level;
    String path;
    private boolean GameOn=true;
    public GameManager(String path)
    {
        this.path=path;
        level=0;
    }
    public void nextLevel()
    {
        Board b = new Board();
        this.gameBoard=b;
        this.level++;

        new GameInitializer(path+"\\level"+level+".txt", gameBoard, player,this);
    }
    public boolean isGameOn() {
        return GameOn;
    }

    public void onPlayerDeath(){
        System.out.println("You lost");
        player.Dead();
        this.GameOn=false;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(Enemy e ) {
        this.enemies.add(e);
    }

    public void onEnemyDeath(Enemy e) {
        Empty empty = new Empty(e.pos);
        enemies.remove(e);
        this.gameBoard.tiles.add(this.gameBoard.tiles.indexOf(e), empty);
        this.gameBoard.tiles.remove(e);
        if (this.enemies.size() == 0) {
            try {
                this.nextLevel();
            } catch (Exception e1) {
                GameOn = false;
            }
        }
    }


    @Override
    public String toString() {
        return String.format("%s\n%s", gameBoard, player.describe());
    }

    public void MoveAllEnemies() {
        for (Enemy e:enemies) {
            Position Move= e.Move(player);
            if(this.getGameBoard().getTile(Move).accept(e))
                this.getGameBoard().ReplacePos(e,Move);
        }
    }
}
