public class MechStudent extends Student {

    public MechStudent(String name) {
        super(name, 5, 7, 7, 6, 3);
    }

    // Damages ignoring defense
    private void armourPiercingRobot(Character enemy) throws Exception {
        if (!enemy.isDead()) {
            if (currentKP >= maxKP) {
                enemy.decreaseHP(getAttack());
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

    // Heals for amount damaged
    public void leeching(Character enemy) throws Exception {
        if (!enemy.isDead()) {
            if (currentKP >= maxKP) {
                enemy.decreaseHP((100 * getAttack()) / (100 + enemy.getDefence()));
                increaseHP((100 * getAttack()) / (100 + enemy.getDefence()));
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

    public void attack(Team enemyTeam) throws Exception {

        System.out.println("Choose an attack:");
        System.out.println("1: Java Programming (Attacks enemy)");
        System.out.println("2: Self Study (Increase HP by 2, increase XP by 6, increase KP by 2)");
        System.out.println("3: Armour Piercing Robot (Damages an enemy ignoring defence)");
        System.out.println("4: Leeching (Damages an enemy and heals for amount damaged)");
        int choice = gameHelper.readIntegerFromCmd(1, 4);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else if (choice == 3) {
            armourPiercingRobot(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else {
            leeching(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        }
    }
}
