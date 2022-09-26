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
            SyntaxError(enemy);
            return;
        }
        randomNumber -= SEWeighting;

        if (randomNumber < NPEWeighting) {
            NullPointerException();
            return;
        }
        randomNumber -= NPEWeighting;

        if (randomNumber < AIOOBEWeighting) {
            ArrayIndexOutOfBoundException(enemy);
            return;
        }
        randomNumber -= AIOOBEWeighting;

        if (randomNumber < NTWeighting) {
            NoneTermination();
            return;
        }

        ConcurrentModificationException(enemy);
    }

    public void SyntaxError(Character enemy) {
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

    public void NullPointerException() {
        increaseHP(this.getDefence());
        increaseXP(3);
    }

    public void ArrayIndexOutOfBoundException(Character enemy) {
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
    private void NoneTermination() {
        for (Character character : getTeam().getMembers()) {
            if (character.isDead())
                character.increaseHP(character.getMaxHP());
        }
        increaseXP(3);
    }

    private void ConcurrentModificationException(Character enemy) {
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
