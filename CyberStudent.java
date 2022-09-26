public class CyberStudent extends Student {

    public CyberStudent(String name) {
        super(name, 7, 7, 5, 6, 6);
    }

    // Attacks all enemies
    private void cyberAttack(Team enemyteam) throws Exception {
        if (currentKP >= maxKP) {
            // student attacks all members of enemy team
            for (Character enemy : enemyteam.getMembers()) {
                if (!enemy.isDead()) {
                    enemy.decreaseHP((100 * getAttack()) / (100 + enemy.getDefence()));
                    if (enemy.isDead())
                        increaseXP(4);
                }
            }

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
        System.out.println("3: Cyber Attack (Attacks all enemies)");
        int choice = gameHelper.readIntegerFromCmd(1, 3);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else {
            cyberAttack(enemyTeam);
        }
    }
}
