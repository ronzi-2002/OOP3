import java.util.LinkedList;
import java.util.List;

public class GameManager {
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
                System.out.println("ron is clown");
                GameOn = false;
            }
        }
    }


    @Override
    public String toString() {
        return String.format("%s\n%s", gameBoard, player.describe());
    }
}
