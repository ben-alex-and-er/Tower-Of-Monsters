import java.util.Random;

public class Student extends Character {
    int maxKP;
    int currentKP;

    Random rand = new Random();
    GameHelper gameHelper = new GameHelper();

    public Student(String name, int baseHP, int baseAtk, int baseDef, int baseSpd, int maxKP) {
        super(name, baseHP, baseAtk, baseDef, baseSpd);
        this.maxKP = maxKP;
        currentKP = 0;
    }

    public void increaseKP(int amount) {
        if (currentKP + amount < maxKP) {
            currentKP += amount;
            return;
        }

        currentKP = maxKP;
    }

    public void strike(Team enemyTeam) throws Exception {
        printStats(0, true, true, true, true, true, true, true);

        // choose which attack to use
        if (currentKP < maxKP) {
            System.out.println("Choose an attack:");
            System.out.println("1: Java Programming (Attacks enemy)");
            System.out.println("2: Self Study (Increase HP by 2, increase XP by 6, increase KP by 2)");
            int choice = gameHelper.readIntegerFromCmd(1, 2);

            if (choice == 1) {
                javaProgramming(enemyTeam.getMembers()[chooseEnemyToHit(enemyTeam) - 1]);
            } else {
                selfStudy();
            }

            return;
        }

        // 50 50 between specials
        if (this instanceof AIStudent) {
            AIStudent stuAI = (AIStudent) this;
            stuAI.attack(enemyTeam);
        } else if (this instanceof CSStudent) {
            CSStudent stuCS = (CSStudent) this;
            stuCS.attack(enemyTeam);
        } else if (this instanceof CyberStudent) {
            CyberStudent stuCyber = (CyberStudent) this;
            stuCyber.attack(enemyTeam);
        } else if (this instanceof SEStudent) {
            SEStudent stuSE = (SEStudent) this;
            stuSE.attack(enemyTeam);
        } else if (this instanceof BioElecStudent) {
            BioElecStudent stuBE = (BioElecStudent) this;
            stuBE.attack(enemyTeam);
        } else if (this instanceof AeroElecStudent) {
            AeroElecStudent stuAero = (AeroElecStudent) this;
            stuAero.attack(enemyTeam);
        } else if (this instanceof MechStudent) {
            MechStudent stuMech = (MechStudent) this;
            stuMech.attack(enemyTeam);
        } else if (this instanceof ElectricalStudent) {
            ElectricalStudent stuElec = (ElectricalStudent) this;
            stuElec.attack(enemyTeam);
        } else {
            throw new Exception();
        }
    }

    public void javaProgramming(Character enemy) throws Exception {
        if (!enemy.isDead()) {
            increaseXP(3);
            increaseKP(1);
            enemy.decreaseHP((100 * getAttack()) / (100 + enemy.getDefence()));
            enemy.increaseXP(2);
            if (enemy instanceof Student) {
                Student enemyStu = (Student) enemy;
                enemyStu.increaseKP(3);
            }

            if (enemy.isDead())
                increaseKP(4);

            return;
        }

        throw new Exception("Enemy is already dead!");
    }

    public void selfStudy() {
        increaseHP(2);
        increaseXP(6);
        increaseKP(2);
    }

    public int chooseEnemyToHit(Team enemyTeam) {
        System.out.println("\nChoose an enemy to hit:");
        for (int i = 0; i < enemyTeam.getTeamSize(); i++) {
            enemyTeam.getMembers()[i].printStats((i + 1), true, false, false, true, true, true, true);
        }
        return gameHelper.readIntegerFromCmd(1, enemyTeam.getTeamSize());
    }

}
