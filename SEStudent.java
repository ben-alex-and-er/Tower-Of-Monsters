public class SEStudent extends Student {

    public SEStudent(String name) {
        super(name, 8, 5, 8, 4, 10);
    }

    // All teammates attack an enemy
    private void groupWork(Character enemy) throws Exception {
        if (!enemy.isDead()) {
            if (currentKP >= maxKP) {

                // all alive team members attack enemy
                for (Character teamMate : getTeam().getMembers()) {
                    if (!teamMate.isDead()) {
                        enemy.decreaseHP((100 * teamMate.getAttack()) / (100 + enemy.getDefence()));
                    }
                }

                increaseXP(4);
                currentKP = 0;
                if (enemy.isDead())
                    increaseXP(4);

                return;
            }

            throw new Exception("Insufficient KP!");
        }

        throw new Exception("Enemy already dead!");
    }

    // Heals all teammates by half defence
    private void groupDiscussion() throws Exception {
        if (currentKP >= maxKP) {
            // all alive team members heal half of student's defence points
            for (Character teamMate : getTeam().getMembers()) {
                if (!teamMate.isDead()) {
                    teamMate.increaseHP(getDefence() / 2);
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
        System.out.println("3: Group Work (All teammates attack an enemy)");
        System.out.println("4: Group Discussion (Heals all teammates by " + getDefence() / 2 + ")");
        int choice = gameHelper.readIntegerFromCmd(1, 4);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else if (choice == 3) {
            groupWork(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else {
            groupDiscussion();
        }
    }
}
