import java.util.Comparator;

public class Character {
    private String name;
    private int baseHP;
    private int baseAtk;
    private int baseDef;
    private int baseSpd;
    private int level = 1;
    private int currentXP;
    private Team team;
    private int currentHP;

    public Character(String name, int baseHP, int baseAtk, int baseDef, int baseSpd) {
        this.name = name;
        this.baseHP = baseHP;
        this.baseAtk = baseAtk;
        this.baseDef = baseDef;
        this.baseSpd = baseSpd;
        currentHP = getMaxHP();
        currentXP = 0;
    }

    public String getName() {
        return name;
    }

    public int getMaxHP() {
        return (int) Math.round(baseHP * Math.pow(level, 1.2));
    }

    public int getAttack() {
        return (int) Math.round(baseAtk * Math.pow(level, 1.2));
    }

    public int getDefence() {
        return (int) Math.round(baseDef * Math.pow(level, 1.2));
    }

    public int getSpeed() {
        return (int) Math.round(baseSpd * Math.pow(level, 1.2));
    }

    public int getTargetXP() {
        return (int) Math.round(10 * Math.pow(level, 1.5));
    }

    public int getHP() {
        return currentHP;
    }

    public int getXP() {
        return currentXP;
    }

    public int getLevel() {
        return level;
    }

    public void increaseHP(int amount) {
        if (currentHP + amount < getMaxHP()) {
            currentHP += amount;
            return;
        }

        currentHP = getMaxHP();
    }

    public void decreaseHP(int amount) {
        if (currentHP - amount > 0) {
            currentHP -= amount;
            return;
        }

        currentHP = 0;
    }

    public void increaseXP(int amount) {
        if (currentXP + amount < getTargetXP()) {
            currentXP += amount;
            return;
        }

        int excess = currentXP + amount - getTargetXP();

        level += 1;
        currentXP = 0;
        if (this instanceof Student) {
            System.out.println("\n" + getName() + " has just levelled up and is now level " + getLevel() + "!");
        }
        
        if (!isDead()) {
            currentHP = getMaxHP();
        }
        
        increaseXP(excess);
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Team getTeam() {
        return team;
    }

    public static Comparator<Character> speedComparator = new Comparator<Character>() {

        public int compare(Character c1, Character c2) {
            int speed1 = c1.getSpeed();
            int speed2 = c2.getSpeed();

            return speed2 - speed1;
        }
    };

    public static Comparator<Character> hpComparator = new Comparator<Character>() {

        public int compare(Character c1, Character c2) {
            int hp1 = c1.getHP();
            int hp2 = c2.getHP();

            return hp2 - hp1;
        }
    };

    public boolean isDead()
    {
        return getHP() <= 0;
    }

    public void printStats(int num, boolean numbool, boolean level, boolean kp, boolean hp, boolean attack, boolean defence, boolean speed)
    {
        if (isDead())
            return;

        System.out.println();

        if (numbool)
        {
            if (num == 0)
            {
                System.out.println(getName() + "-");
            } else {
                System.out.println(num + ": " + getName() + "-");
            }
        }

        if (level)
            System.out.println("Level: " + getLevel() + " (" + currentXP + "/" + getTargetXP() + "XP)");

        if (kp)
        {
            if (this instanceof Student)
            {
                Student s = (Student) this;

                System.out.println("KP: " + s.currentKP + "/" + s.maxKP);
            }
        }

        if (hp)
            System.out.println("Health: " + getHP() + "/" + getMaxHP());

        if (attack)
            System.out.println("Attack: " + getAttack());

        if (defence)
            System.out.println("Defense: " + getDefence());

        if (speed)
            System.out.println("Speed: " + getSpeed());


        System.out.println();
        try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
		}
    }
}