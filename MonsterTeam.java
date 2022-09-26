import java.util.ArrayList;
import java.util.Collections;

public class MonsterTeam extends Team {
    public MonsterTeam(String teamName) {
        super(teamName);
    }

    public void move(Character member, Team enemyTeam) throws Exception {
        ArrayList<Character> enemyArrayList = new ArrayList<Character>();
        Collections.addAll(enemyArrayList, enemyTeam.getMembers());
        Collections.sort(enemyArrayList, Character.hpComparator);

        for (int i = 4; i >= 0; i--) {
            Character enemy = enemyArrayList.get(i);
            if (!enemy.isDead()) {
                ((Monster) member).strike(enemy);
                break;
            }
        }
    }
}
