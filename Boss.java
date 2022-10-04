import java.util.Random;

public class Boss extends Character implements Monster {

    int NTWeighting = 2;
    int CMEWeighting = 2;

    public Boss(String name) {
        super(name, 8, 7, 8, 7);
    }

    Random rand = new Random();

    public void strike(Character enemy) {
        int randomNumber = rand.nextInt(totalWeighting());

        if (randomNumber < SEWeighting) {
            syntaxError(enemy);
            return;
        }
        randomNumber -= SEWeighting;

        if (randomNumber < NPEWeighting) {
            nullPointerException();
            return;
        }
        randomNumber -= NPEWeighting;

        if (randomNumber < AIOOBEWeighting) {
            arrayIndexOutOfBoundException(enemy);
            return;
        }
        randomNumber -= AIOOBEWeighting;

        if (randomNumber < NTWeighting) {
            noneTermination();
            return;
        }

        concurrentModificationException(enemy);
    }

    public void syntaxError(Character enemy) {
        enemy.decreaseHP(((100 * this.getAttack()) / (100 + this.getDefence())));
        increaseXP(3);
        enemy.increaseXP(3);

        if (enemy instanceof Student) {
            Student enemyStu = (Student) enemy;
            enemyStu.increaseKP(3);
        }

        if (enemy.isDead())
            increaseXP(4);
    }

    public void nullPointerException() {
        increaseHP(this.getDefence());
        increaseXP(3);
    }

    public void arrayIndexOutOfBoundException(Character enemy) {
        enemy.decreaseHP(2 * ((100 * this.getAttack()) / (100 + this.getDefence())));
        this.increaseXP(3);
        enemy.increaseXP(3);

        if (enemy instanceof Student) {
            Student enemyStu = (Student) enemy;
            enemyStu.increaseKP(3);
        }

        if (enemy.isDead())
            increaseXP(4);
    }

    // Revives all dead teammates
    private void noneTermination() {
        for (Character character : getTeam().getMembers()) {
            if (character.isDead())
                character.increaseHP(character.getMaxHP());
        }
        increaseXP(3);
    }

    private void concurrentModificationException(Character enemy) {
        for (Character oneEnemy : enemy.getTeam().getMembers()) {
            if (!oneEnemy.isDead()) {
                oneEnemy.decreaseHP((100 * getAttack()) / (100 + oneEnemy.getDefence()));
                oneEnemy.increaseXP(3);

                if (oneEnemy instanceof Student) {
                    Student enemyStu = (Student) oneEnemy;
                    enemyStu.increaseKP(3);
                }

                if (oneEnemy.isDead())
                    increaseXP(4);

                increaseXP(3);
            }
        }
    }

    private int totalWeighting() {
        return SEWeighting +
                NPEWeighting +
                AIOOBEWeighting +
                NTWeighting +
                CMEWeighting;
    }
}
