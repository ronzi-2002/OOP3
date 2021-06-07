import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player player;
    private TileFactory T;
    private GameManager GM;
    private Warrior w;
    private Rouge rouge;
    private Mage mage;
    @BeforeEach
    void setUp() {
        T = new TileFactory();
        player = T.listPlayers().get(0);
        w = new Warrior(player.pos, "Jon Snow", new Health(300), 30, 4, 3);
        rouge = new Rouge(player.pos, "Arya Stark", new Health(150), 40, 2, 20);
        mage = new Mage(player.pos,"Thoros of Myr", new Health(250), 25, 4, 150, 20, 20, 3, 4);
        GM= new GameManager("C:\\Users\\user1\\Desktop\\OOP3\\levels_dir");
        GM.setPlayer(player);
        GM.SetMode("t");
        GM.nextLevel();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void defence() {
        int [] attacks={0,player.h.HealthAmount+player.DefencePoints/2-1,player.h.HealthAmount+player.DefencePoints/2,player.h.HealthAmount+player.DefencePoints/2+10000};
        int [] results={player.h.HealthAmount,1,0,0};
        boolean [] isDead={false,false,true,true};
        for (int i = 0; i < attacks.length; i++) {
            int n=attacks[i];
            int healthBefore =player.h.HealthAmount;
            player.Defence(n);
            assertEquals(results[i],player.h.HealthAmount);
            assertEquals(isDead[i],!GM.isGameOn());
            player.h.HealthAmount=healthBefore;
        }

    }

    @Test
    void levelUp() {
        for (int i = 0; i <500; i++) {
            player.h.HealthAmount = 1;
            int attackPoints = player.AttackPoints;
            int defencePoints = player.DefencePoints;
            int healthPool = player.h.HealthPool;
            int Level = player.PlayerLevel;
            player.LevelUp();
            assertEquals(healthPool + player.gainHealth(), player.h.HealthAmount);
            assertEquals(attackPoints + player.gainAttack(), player.AttackPoints);
            assertEquals(defencePoints + player.gainDefence(), player.DefencePoints);
            assertEquals(Level + 1, player.PlayerLevel);
        }
    }

    @Test
    void addExperience() {
            int levelUp = player.levelUpRequirement();
            player.AddExperience(levelUp-1);
            assertEquals(levelUp-1,player.Experience);
            player.Experience=0;
            int PlayerLevel= player.PlayerLevel;
            player.AddExperience(levelUp);
            assertEquals(0,player.Experience);
            assertEquals(PlayerLevel+1,player.PlayerLevel);
            levelUp = player.levelUpRequirement();
            player.AddExperience(levelUp+1);
            assertEquals(1,player.Experience);
    }

    @Test
    void combat() {
        Enemy e = GM.getEnemies().get(0);
        int HealthAmountBefore = e.h.HealthAmount;
        player.Combat(e);
        assertEquals(HealthAmountBefore-(player.AttackPoints/2-e.DefencePoints/2),e.h.HealthAmount);
        player.AttackPoints=e.DefencePoints/3;
        e.h.HealthAmount=e.h.HealthPool;
        player.Combat(e);
        assertEquals(HealthAmountBefore,e.h.HealthAmount);
        player.AttackPoints=2*(e.DefencePoints+e.h.HealthPool);
        player.Combat(e);
        assertEquals(0,e.h.HealthAmount);
    }

    @Test
    void visit() {
        Empty empty = new Empty(new Position(player.pos.x+1,player.pos.y));
        Position PlayerPos=player.pos;
        Position EmptyPos=empty.pos;
        player.Visit(empty);
        assertEquals(EmptyPos.x,player.pos.x);
        assertEquals(EmptyPos.y,player.pos.y);
        assertEquals(PlayerPos.x,empty.pos.x);
        assertEquals(PlayerPos.y,empty.pos.y);

        Wall wall = new Wall(new Position(player.pos.x+1,player.pos.y));
        Position WallPos=wall.pos;
        player.Visit(empty);
        assertEquals(WallPos.x,wall.pos.x);
        assertEquals(WallPos.y,wall.pos.y);
        assertEquals(PlayerPos.y,player.pos.y);
        assertEquals(PlayerPos.x,player.pos.x);

    }

    @Test
    void specialAbility() {
        w.messageCallBack=player.messageCallBack;
        Enemy e = GM.getEnemies().get(0);
        e.setPosition(new Position(player.pos.x,player.pos.y+1));
        LinkedList<Enemy> list = new LinkedList<Enemy>();
        list.add(e);
        int HealthAmount = e.h.HealthAmount;
        w.specialAbility(list);
        assertEquals(HealthAmount-(w.h.HealthPool/10)+e.DefencePoints/2,e.h.HealthAmount);
        rouge.messageCallBack=player.messageCallBack;
        e.setPosition(new Position(player.pos.x,player.pos.y+1));
        e.h.HealthAmount = HealthAmount;
        HealthAmount = e.h.HealthAmount;
        list.add(e);
        rouge.specialAbility(list);
        assertEquals(HealthAmount-rouge.AttackPoints+e.DefencePoints/2,e.h.HealthAmount);
        mage.messageCallBack=player.messageCallBack;
        e.setPosition(new Position(player.pos.x,player.pos.y+1));
        e.h.HealthAmount = HealthAmount;
        HealthAmount = e.h.HealthAmount;
        list.add(e);
        mage.specialAbility(list);
        System.out.println(mage.getSpellPower());
        assertEquals(HealthAmount - (mage.getHitsCount())*(mage.getSpellPower()-e.DefencePoints/2),e.h.HealthAmount);

    }

    @Test
    void updateTicks() {
        mage.messageCallBack=player.messageCallBack;
        w.setRemainingCoolDown(w.getAbilityCoolDown());
        int CoolDown=w.getRemainingCoolDown();
        w.updateTicks();
        assertEquals(CoolDown-1,w.getRemainingCoolDown());
        while(CoolDown>0){
            w.updateTicks();
            CoolDown--;
        }
        assertEquals(0,w.getRemainingCoolDown());
        rouge.setCurrentEnergy(100);
        rouge.updateTicks();
        assertEquals(100,rouge.getCurrentEnergy());
        rouge.setCurrentEnergy(95);
        rouge.updateTicks();
        assertEquals(100,rouge.getCurrentEnergy());
        rouge.setCurrentEnergy(5);
        rouge.updateTicks();
        assertEquals(15,rouge.getCurrentEnergy());
        mage.setCurrentMana(mage.getManaPool());
        mage.updateTicks();
        assertEquals(mage.getManaPool(),mage.getCurrentMana());
        mage.setCurrentMana(0);
        mage.updateTicks();
        assertEquals(0+ mage.PlayerLevel,mage.getCurrentMana());
        while(mage.PlayerLevel<=mage.getManaPool()+1){
            mage.LevelUp();
        }
        mage.setCurrentMana(0);
        mage.updateTicks();
        assertEquals(mage.getManaPool(),mage.getCurrentMana());

    }
}
