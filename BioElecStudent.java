import java.util.Random;

public class BioElecStudent extends Student {
    public BioElecStudent(String name) {
        super(name, 10, 5, 5, 5, 9);
    }

    Random random = new Random();

    // Makes an enemy hit a random teammate
    private void mindHack(Character enemy) throws Exception {
        if (!enemy.isDead()) {
            if (currentKP >= maxKP) {
                Character enemyTeammate;
                do {
                    int rand1 = random.nextInt(enemy.getTeam().getTeamSize());
                    enemyTeammate = enemy.getTeam().getMembers()[rand1];
                } while (enemyTeammate.isDead() && enemyTeammate == enemy);
                
                if (enemy instanceof Monster) {
                    Monster enemyMonster = (Monster) enemy;
                    enemyMonster.strike(enemyTeammate);
                }

                if (enemyTeammate.isDead())
                    increaseXP(4);

                increaseXP(4);
                currentKP = 0;
                return;
            }
            throw new Exception("Insufficient KP!");
        }

        throw new Exception("Enemy is already dead!");
    }

    // Heals up to maximum HP
    private void nanoBots() throws Exception {
        if (currentKP >= maxKP) {
            increaseHP(getMaxHP());
            increaseXP(4);
            currentKP = 0;
            return;
        }

        throw new Exception("Insufficient KP!");
    }

    public void attack(Team enemyTeam) throws Exception {
        System.out.println("Choose an attack:");
        System.out.println("1: Java Programming (Attacks enemy)");
        System.out.println("2: Self Study (Increase HP by 2, increase XP by 6, increase KP by 2)");
        System.out.println("3: Mindhack (Makes an enemy hit a random teammate)");
        System.out.println("4: Nano Bots (increases HP to full)");
        int choice = gameHelper.readIntegerFromCmd(1, 4);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else if (choice == 3) {
            mindHack(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else {
            nanoBots();
        }
    }
}