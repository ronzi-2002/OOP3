package Tests;

import Backend.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EnemyTest {
    private Monster monster;
    private Trap trap;
    private Player player;
    private TileFactory T;
    private GameManager GM;

    @BeforeEach
    void setUp() {
        T = new TileFactory();
        player = T.listPlayers().get(0);
        GM = new GameManager("C:\\Users\\user1\\Desktop\\OOP3\\levels_dir");
        GM.setPlayer(player);
        GM.SetMode("t");
        GM.nextLevel();
        monster = new Monster(new Position(1, 1), 'C', "Queen Cersei", new Health(100), 10, 10, 1000, 2);
        trap = new Trap('B', new Health(1), "Bonus Backend.Trap", new Position(5, 5), 1, 1, 250, 1, 1);
        trap.setDeathCallback((b) -> GM.onEnemyDeath(trap, b));
        monster.setDeathCallback((b) -> GM.onEnemyDeath(monster, b));
        trap.moveCallBack = ((posit) -> GM.getGameBoard().ReplacePos(trap, posit));
        monster.moveCallBack = ((posit) -> GM.getGameBoard().ReplacePos(monster, posit));
        trap.initialize((msg) -> System.out.println(msg));
        monster.initialize((msg) -> System.out.println(msg));

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void visit() {
        Empty empty = new Empty(new Position(monster.pos.getX() + 1, monster.pos.getY()));
        Position MonsterPos = monster.pos;
        Position EmptyPos = empty.pos;
        monster.Visit(empty);
        assertEquals(EmptyPos.getX(), monster.pos.getX());
        assertEquals(EmptyPos.getY(), monster.pos.getY());
        assertEquals(MonsterPos.getX(), empty.pos.getX());
        assertEquals(MonsterPos.getY(), empty.pos.getY());
        Wall wall = new Wall(new Position(monster.pos.getX() + 1, monster.pos.getY()));
        Position WallPos = wall.pos;
        monster.Visit(empty);
        assertEquals(WallPos.getX(), wall.pos.getX());
        assertEquals(WallPos.getY(), wall.pos.getY());
        assertEquals(MonsterPos.getY(), monster.pos.getY());
        assertEquals(monster.pos.getX(), monster.pos.getX());

    }

    @Test
    void move() {
        player.pos.setX(monster.pos.getX() + 1)  ;
        player.pos.setY(monster.pos.getY() + 1);
        assertEquals(1.0, monster.Move(player).Range(player.pos));

    }

    @Test
    void combat() {
        player.pos.setX(monster.pos.getX()+1);
        player.pos.setY(monster.pos.getY());

        int HealthAmountBefore = player.getH().getHealthAmount();
        monster.Visit(player);
        assertEquals(HealthAmountBefore - (monster.getAttackPoints()/ 2 - player.getDefencePoints()/ 2), player.getH().getHealthAmount());
        monster.setAttackPoints(player.getDefencePoints()/ 3);
        player.getH().setHealthAmount(player.getH().getHealthPool());
        monster.Visit(player);
        assertEquals(HealthAmountBefore, player.getH().getHealthAmount());
        monster.setAttackPoints( 2 * (player.getDefencePoints()+ player.getH().getHealthPool()));
        monster.Visit(player);
        assertEquals(0, player.getH().getHealthAmount());

        player.pos.setX(trap.pos.getX()+1);
        player.pos.setY(trap.pos.getY());

        player.getH().setHealthAmount(HealthAmountBefore) ;
        trap.Visit(player);
        assertEquals(HealthAmountBefore , player.getH().getHealthAmount());
        trap.setAttackPoints( player.getDefencePoints()/ 3);
        player.getH().setHealthAmount(player.getH().getHealthPool());
        trap.Visit(player);
        assertEquals(HealthAmountBefore, player.getH().getHealthAmount());
        trap.setAttackPoints( 2 * (player.getDefencePoints()+ player.getH().getHealthPool()));
        trap.Visit(player);
        assertEquals(0, player.getH().getHealthAmount());
    }

    @Test
    void updateTicks() {
        trap.setVisible(true);
        trap.updateTicks();
        assertEquals(true, trap.isVisible());
        trap.updateTicks();
        assertEquals(false,  trap.isVisible());
        trap.updateTicks();
        assertEquals(true,  trap.isVisible());
    }
}