public class ElectricalStudent extends Student {

    public ElectricalStudent(String name) {
        super(name, 5, 10, 5, 5, 6);
    }

    // Halves the health of the enemy
    private void electricity(Character enemy) throws Exception {
        if (!enemy.isDead()) {
            if (currentKP >= maxKP) {
                enemy.decreaseHP(enemy.getHP() / 2);
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

    // Attacks all enemies for a quarter of the student's attack (ignoring defense)
    private void staticLink(Team enemyteam) throws Exception {
        if (currentKP >= maxKP) {

            // Student attacks all members of enemy team
            for (Character enemy : enemyteam.getMembers()) {
                if (!enemy.isDead()) {
                    enemy.decreaseHP(getAttack() / 4);
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
        System.out.println("3: Electricity (Halves an enemies HP)");
        System.out.println("4: Static Link (Damages all enemies by " + getAttack() / 4 + ")");
        int choice = gameHelper.readIntegerFromCmd(1, 4);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else if (choice == 3) {
            electricity(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else {
            staticLink(enemyTeam);
        }
    }
}
