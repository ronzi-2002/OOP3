package Backend;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class TileFactory {
    private List<Supplier<Player>> playersList;
    public Map<Character, Supplier<Enemy>> enemiesMap;//todo change back to private and add a getter
    private Player selected;

    public TileFactory(){
        playersList = initPlayers();
        enemiesMap = initEnemies();
    }

    private Map<Character, Supplier<Enemy>> initEnemies() {
        List<Supplier<Enemy>> enemies = Arrays.asList(
                () -> new Monster(null,'s',"Lannister Solider", new Health(80), 8, 3,25, 3),
                () -> new Monster(null,'k',"Lannister Knight", new Health(200), 14, 8, 50,   4),
                () -> new Monster(null,'q',"Queen's Guard", new Health(400), 20, 15, 100,  5),
                () -> new Monster(null,'M',"The Mountain", new Health(1000), 60, 25,  500, 6),
                () -> new Monster(null,'C',"Queen Cersei", new Health(100), 10, 10,1000, 1),
                () -> new Trap('B', new Health(1) ,"Bonus Backend.Trap",null ,1, 1, 250,1 ,  1),
                () -> new Trap('Q', new Health(250) ,"Queen's Backend.Trap", null,250, 50,  100, 10,3),

                () -> new Monster(null,'z', "Wright", new Health(600), 30, 15,100, 3),
                () -> new Monster(null,'b', "Bear-Wright", new Health(1000), 75, 30, 250,  4),
                () -> new Monster(null,'g', "Giant-Wright",new Health(1500), 100, 40,500,   5),
                () -> new Monster(null,'w', "White Walker", new Health(2000), 150, 50, 1000, 6),
                () -> new Monster(null,'K', "Night's King", new Health(5000), 300, 150, 5000, 8),
                () -> new Trap('D', new Health(500),"Death Backend.Trap", null, 100, 20, 250, 1, 10)
        );

        return enemies.stream().collect(Collectors.toMap(s -> s.get().getC(), Function.identity()));
    }

    private List<Supplier<Player>> initPlayers() {
        return Arrays.asList(
                () -> new Warrior(null,"Jon Snow", new Health(300), 30, 4, 3),
                () -> new Warrior(null,"The Hound", new Health(400), 20, 6, 5),
                () -> new Mage(null,"Melisandre", new Health(100), 5, 1, 300, 30, 15, 5, 6),
                () -> new Mage(null,"Thoros of Myr", new Health(250), 25, 4, 150, 20, 20, 3, 4),
                () -> new Rouge(null,"Arya Stark", new Health(150), 40, 2, 20),
                () -> new Rouge(null,"Bronn", new Health(250), 35, 3, 50)
        );
    }

    public List<Player> listPlayers(){

        return playersList.stream().map(Supplier::get).collect(Collectors.toList());
    }

//    public Backend.Enemy produceEnemy(char tile, Backend.Position position, MessageCallback messageCallback, EnemyDeathCallback enemyDeathCallback) {
//        ...
//    }
//
//    public Backend.Player producePlayer(int idx){
//		...
//    }
//
//    public Backend.Empty produceEmpty(Backend.Position position){
//        ...
//    }
//
//    public Backend.Wall produceWall(Backend.Position position){
//        ...
//    }

}