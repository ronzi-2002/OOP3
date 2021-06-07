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
        trap = new Trap('B', new Health(1), "Bonus Trap", new Position(5, 5), 1, 1, 250, 1, 1);
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
        Empty empty = new Empty(new Position(monster.pos.x + 1, monster.pos.y));
        Position MonsterPos = monster.pos;
        Position EmptyPos = empty.pos;
        monster.Visit(empty);
        assertEquals(EmptyPos.x, monster.pos.x);
        assertEquals(EmptyPos.y, monster.pos.y);
        assertEquals(MonsterPos.x, empty.pos.x);
        assertEquals(MonsterPos.y, empty.pos.y);
        Wall wall = new Wall(new Position(monster.pos.x + 1, monster.pos.y));
        Position WallPos = wall.pos;
        monster.Visit(empty);
        assertEquals(WallPos.x, wall.pos.x);
        assertEquals(WallPos.y, wall.pos.y);
        assertEquals(MonsterPos.y, monster.pos.y);
        assertEquals(monster.pos.x, monster.pos.x);

    }

    @Test
    void move() {
        player.pos.x = monster.pos.x + 1;
        player.pos.y = monster.pos.y + 1;
        assertEquals(1.0, monster.Move(player).Range(player.pos));

    }

    @Test
    void combat() {
        player.pos.x=monster.pos.x+1;
        player.pos.y=monster.pos.y;

        int HealthAmountBefore = player.h.HealthAmount;
        monster.Visit(player);
        assertEquals(HealthAmountBefore - (monster.AttackPoints / 2 - player.DefencePoints / 2), player.h.HealthAmount);
        monster.AttackPoints = player.DefencePoints / 3;
        player.h.HealthAmount = player.h.HealthPool;
        monster.Visit(player);
        assertEquals(HealthAmountBefore, player.h.HealthAmount);
        monster.AttackPoints = 2 * (player.DefencePoints + player.h.HealthPool);
        monster.Visit(player);
        assertEquals(0, player.h.HealthAmount);

        player.pos.x=trap.pos.x+1;
        player.pos.y=trap.pos.y;

        player.h.HealthAmount = HealthAmountBefore;
        trap.Visit(player);
        assertEquals(HealthAmountBefore , player.h.HealthAmount);
        trap.AttackPoints = player.DefencePoints / 3;
        player.h.HealthAmount = player.h.HealthPool;
        trap.Visit(player);
        assertEquals(HealthAmountBefore, player.h.HealthAmount);
        trap.AttackPoints = 2 * (player.DefencePoints + player.h.HealthPool);
        trap.Visit(player);
        assertEquals(0, player.h.HealthAmount);
    }

    @Test
    void updateTicks() {
        trap.visible = true;
        trap.updateTicks();
        assertEquals(true, trap.visible);
        trap.updateTicks();
        assertEquals(false, trap.visible);
        trap.updateTicks();
        assertEquals(true, trap.visible);
    }
}