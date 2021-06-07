import Backend.*;
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
        int [] attacks={0,player.getH().getHealthAmount()+player.getDefencePoints()/2-1,player.getH().getHealthAmount()+player.getDefencePoints()/2,player.getH().getHealthAmount()+player.getDefencePoints()/2+10000};
        int [] results={player.getH().getHealthAmount(),1,0,0};
        boolean [] isDead={false,false,true,true};
        for (int i = 0; i < attacks.length; i++) {
            int n=attacks[i];
            int healthBefore =player.getH().getHealthAmount();
            player.Defence(n);
            assertEquals(results[i],player.getH().getHealthAmount());
            assertEquals(isDead[i],!GM.isGameOn());
            player.getH().setHealthAmount(healthBefore);
        }

    }

    @Test
    void levelUp() {
        for (int i = 0; i <500; i++) {
            player.getH().setHealthAmount(1) ;
            int attackPoints = player.getAttackPoints();
            int defencePoints = player.getDefencePoints();
            int healthPool = player.getH().getHealthPool();
            int Level = player.getLevel();
            player.LevelUp();
            assertEquals(healthPool + player.gainHealth(), player.getH().getHealthAmount());
            assertEquals(attackPoints + player.gainAttack(), player.getAttackPoints());
            assertEquals(defencePoints + player.gainDefence(), player.getDefencePoints());
            assertEquals(Level + 1, player.getLevel());
        }
    }

    @Test
    void addExperience() {
            int levelUp = player.levelUpRequirement();
            player.AddExperience(levelUp-1);
            assertEquals(levelUp-1,player.getExperience());
            player.setExperience(0);
            int PlayerLevel= player.getLevel();
            player.AddExperience(levelUp);
            assertEquals(0,player.getExperience());
            assertEquals(PlayerLevel+1,player.getLevel());
            levelUp = player.levelUpRequirement();
            player.AddExperience(levelUp+1);
            assertEquals(1,player.getExperience());
    }

    @Test
    void combat() {
        Enemy e = GM.getEnemies().get(0);
        int HealthAmountBefore = e.getH().getHealthAmount();
        player.Combat(e);
        assertEquals(HealthAmountBefore-(player.getAttackPoints()/2-e.getDefencePoints()/2),e.getH().getHealthAmount());
        player.setAttackPoints(e.getDefencePoints()/3);
        e.getH().setHealthAmount(e.getH().getHealthPool());
        player.Combat(e);
        assertEquals(HealthAmountBefore,e.getH().getHealthAmount());
        player.setAttackPoints(2*(e.getDefencePoints()+e.getH().getHealthPool()));
        player.Combat(e);
        assertEquals(0,e.getH().getHealthAmount());
    }

    @Test
    void visit() {
        Empty empty = new Empty(new Position(player.pos.getX()+1,player.pos.getY()));
        Position PlayerPos=player.pos;
        Position EmptyPos=empty.pos;
        player.Visit(empty);
        assertEquals(EmptyPos.getX(),player.pos.getX());
        assertEquals(EmptyPos.getY(),player.pos.getY());
        assertEquals(PlayerPos.getX(),empty.pos.getX());
        assertEquals(PlayerPos.getY(),empty.pos.getY());

        Wall wall = new Wall(new Position(player.pos.getX()+1,player.pos.getY()));
        Position WallPos=wall.pos;
        player.Visit(empty);
        assertEquals(WallPos.getX(),wall.pos.getX());
        assertEquals(WallPos.getY(),wall.pos.getY());
        assertEquals(PlayerPos.getY(),player.pos.getY());
        assertEquals(PlayerPos.getX(),player.pos.getX());

    }

    @Test
    void specialAbility() {
        w.messageCallBack=player.messageCallBack;
        Enemy e = GM.getEnemies().get(0);
        e.setPosition(new Position(player.pos.getX(),player.pos.getY()+1));
        LinkedList<Enemy> list = new LinkedList<Enemy>();
        list.add(e);
        int HealthAmount = e.getH().getHealthAmount();
        w.specialAbility(list);
        assertEquals(HealthAmount-(w.getH().getHealthPool()/10)+e.getDefencePoints()/2,e.getH().getHealthAmount());
        rouge.messageCallBack=player.messageCallBack;
        e.setPosition(new Position(player.pos.getX(),player.pos.getY()+1));
        e.getH().setHealthAmount(HealthAmount);
        HealthAmount = e.getH().getHealthAmount();
        list.add(e);
        rouge.specialAbility(list);
        assertEquals(HealthAmount-rouge.getAttackPoints()+e.getDefencePoints()/2,e.getH().getHealthAmount());
        mage.messageCallBack=player.messageCallBack;
        e.setPosition(new Position(player.pos.getX(),player.pos.getY()+1));
        e.getH().setHealthAmount(HealthAmount);
        HealthAmount = e.getH().getHealthAmount();
        list.add(e);
        mage.specialAbility(list);
        System.out.println(mage.getSpellPower());
        assertEquals(HealthAmount - (mage.getHitsCount())*(mage.getSpellPower()-e.getDefencePoints()/2),e.getH().getHealthAmount());

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
        assertEquals( mage.getLevel(),mage.getCurrentMana());
        while(mage.getLevel()<=mage.getManaPool()+1){
            mage.LevelUp();
        }
        mage.setCurrentMana(0);
        mage.updateTicks();
        assertEquals(mage.getManaPool(),mage.getCurrentMana());

    }
}
