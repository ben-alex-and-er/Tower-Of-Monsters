import java.util.Random;

public class Minion extends Character implements Monster {
    Student enemyStu;

    public Minion(String name) {
        super(name, 4, 4, 4, 5);
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

        ArrayIndexOutOfBoundException(enemy);
    }

    public void SyntaxError(Character enemy) {
        enemy.decreaseHP(((100 * this.getAttack()) / (100 + this.getDefence())));
        this.increaseXP(3);
        enemy.increaseXP(3);
        if (enemy instanceof Student) {
            enemyStu = (Student) enemy;
            enemyStu.increaseKP(3);
        }

        if (enemy.isDead()) {
            increaseXP(4);
        }

    }

    public void NullPointerException() {
        increaseHP(getDefence());
        increaseXP(3);
    }

    public void ArrayIndexOutOfBoundException(Character enemy) {
        enemy.decreaseHP(2 * ((100 * this.getAttack()) / (100 + this.getDefence())));
        increaseXP(3);
        enemy.increaseXP(3);
        if (enemy instanceof Student) {
            enemyStu = (Student) enemy;
            enemyStu.increaseKP(3);
        }
        if (enemy.isDead()) {
            increaseXP(4);
        }
    }

    private int totalWeighting() {
        return SEWeighting +
                NPEWeighting +
                AIOOBEWeighting;
    }

}
