public class AIStudent extends Student {

    public AIStudent(String name) {
        super(name, 6, 7, 7, 5, 3);
    }

    // Deals double damage
    private void machineLearning(Character enemy) throws Exception {
        if (!enemy.isDead()) {
            if (currentKP >= maxKP) {
                enemy.decreaseHP((2 * (100 * getAttack()) / (100 + enemy.getDefence())));
                increaseXP(4);
                currentKP = 0;
                if (enemy.isDead())
                    increaseXP(4);

                return;
            }

            throw new Exception("Insufficient KP!");
        }

        throw new Exception("Enemy is already dead!");
    }

    // increases HP by defense stat
    private void naturalLanguageProcessing() throws Exception {
        if (currentKP >= maxKP) {
            increaseHP(getDefence());
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
        System.out.println("3: Machine Learning (Deals double damage)");
        System.out.println("4: Natural Language Proccessing (increases HP by " + getDefence() + ")");
        int choice = gameHelper.readIntegerFromCmd(1, 4);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else if (choice == 3) {
            machineLearning(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else {
            naturalLanguageProcessing();
        }
    }
}