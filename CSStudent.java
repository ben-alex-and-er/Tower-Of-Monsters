public class CSStudent extends Student {

    public CSStudent(String name) {
        super(name, 7, 6, 6, 6, 4);
    }

    // Teams up with a teammate and both attack an enemy
    private void pairWorking(Character friend, Character enemy) throws Exception {
        if (!friend.isDead()) {
            if (!enemy.isDead()) {
                if (currentKP >= maxKP) {
                    enemy.decreaseHP((100 * (getAttack() + friend.getAttack())) / (100 + enemy.getDefence()));
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

        throw new Exception("Friend is already dead!");
    }

    // Heals a teammate for amount of defence
    private void support(Character friend) throws Exception {
        if (!friend.isDead()) {
            if (currentKP >= maxKP) {
                friend.increaseHP(getDefence());
                increaseXP(4);
                currentKP = 0;
                return;
            }

            throw new Exception("Insufficient KP!");

        }

        throw new Exception("Friend is already dead!");
    }

    public void attack(Team enemyTeam) throws Exception {

        System.out.println("Choose an attack:");
        System.out.println("1: Java Programming (Attacks enemy)");
        System.out.println("2: Self Study (Increase HP by 2, increase XP by 6, increase KP by 2)");
        System.out.println("3: Pair Working (Teams up with a teammate and both attack an enemy)");
        System.out.println("4: Support (Heals a teammate for " + getDefence() + ")");
        int choice = gameHelper.readIntegerFromCmd(1, 4);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else if (choice == 3) {
            pairWorkingText(enemyTeam);
        } else {
            supportText();
        }
    }

    private void pairWorkingText(Team enemyTeam) throws Exception {
        Character friend;
        do {
            System.out.println("Choose a friend to attack with:");
            for (int i = 0; i < getTeam().getTeamSize(); i++) {
                Character teamMate = getTeam().getMembers()[i];
                if (teamMate != this)
                    teamMate.printStats(i+1, true, false, false, false, true, false, false);
            }
            int friendChoice = gameHelper.readIntegerFromCmd(1, getTeam().getTeamSize());
            friend = getTeam().getMembers()[friendChoice];
        } while (friend != this);

        Character enemyToHit;
        do {
            System.out.println("\nChoose an enemy to hit:");
            for (int i = 0; i < enemyTeam.getTeamSize(); i++) {
                enemyTeam.getMembers()[i].printStats(i+1, true, true, false, true, true, true, true);
            }
            int enemyChoice = gameHelper.readIntegerFromCmd(1, enemyTeam.getTeamSize());
            enemyToHit = enemyTeam.getMembers()[enemyChoice - 1];
        } while (enemyToHit.isDead());

        pairWorking(friend, enemyToHit);
    }

    private void supportText() throws Exception {
        System.out.println();
        System.out.println("Choose a friend to heal:");
        for (int i = 0; i < getTeam().getTeamSize(); i++) {
            getTeam().getMembers()[i].printStats(i+1, true, false, false, true, false, false, false);
        }
        int friendChoice = gameHelper.readIntegerFromCmd(1, getTeam().getTeamSize());

        support(getTeam().getMembers()[friendChoice - 1]);
    }
}
