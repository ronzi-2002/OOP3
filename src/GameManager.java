import java.util.List;

public class GameManager {
    private Board gameBoard;
    private Player player;
    private List<Enemy> enemies;


    public void onPlayerDeath(){

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

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
    }

    public void onEnemyDeath(Enemy e){

    }

    @Override
    public String toString() {
        return String.format("%s\n%s", gameBoard, player.describe());
    }
}
