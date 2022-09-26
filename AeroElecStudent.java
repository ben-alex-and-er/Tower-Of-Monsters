public class AeroElecStudent extends Student {
    GameHelper gameHelper = new GameHelper();

    public AeroElecStudent(String name) {
        super(name, 5, 7, 7, 6, 9);
    }

    // Attacks 3 random enemies
    private void drone(Character enemy) throws Exception {
        if (currentKP >= maxKP) {
            // student attacks 3 members of enemy team
            Character[] enemyArray = enemy.getTeam().getMembers();
            for (int i = 0; i < 3; i++) {
                Character oneEnemy = enemyArray[i];
                if (!oneEnemy.isDead()) {
                    oneEnemy.decreaseHP((100 * getAttack()) / (100 + oneEnemy.getDefence()));

                    if (oneEnemy.isDead())
                        increaseXP(4);
                }
            }

            increaseXP(4);
            currentKP = 0;
            return;
        }

        throw new Exception("Insufficient KP!");
    }

    // Levels up the character (might need to be nerfed)
    private void instantLevelling() throws Exception {
        if (currentKP >= maxKP) {
            increaseXP(getTargetXP());
            currentKP = 0;
            return;
        }

        throw new Exception("Insufficient KP!");
    }

    public void attack(Team enemyTeam) throws Exception {
        System.out.println("Choose an attack:");
        System.out.println("1: Java Programming (Attacks enemy)");
        System.out.println("2: Self Study (Increase HP by 2, increase XP by 6, increase KP by 2)");
        System.out.println("3: Drone (Attacks 3 random enemies)");
        System.out.println("4: Instant Levelling (Levels up)");
        int choice = gameHelper.readIntegerFromCmd(1, 4);

        if (choice == 1) {
            javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else if (choice == 2) {
            selfStudy();
        } else if (choice == 3) {
            drone(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
        } else {
            instantLevelling();
        }
    }
}