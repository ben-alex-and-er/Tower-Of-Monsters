import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Guild {

    int TeamSize = 5;
    ArrayList<Character> guildMembers = new ArrayList<Character>();
    GameHelper gameHelper = new GameHelper();
    Random rand = new Random();

    public void addMember(Character member) {
        if (guildMembers.contains(member) == false) {
            guildMembers.add(member);
        } else {
            System.out.println("Character already in the guild");
        }
    }

    public ArrayList<Character> getMembers() {
        return guildMembers;
    }

    public Team getTeam() {

        int aliveNum = 0;

        StudentTeam newTeam = new StudentTeam("Your Team", this);
        StudentTeam assignedTeam = new StudentTeam("Your Team", this);

        Collections.sort(guildMembers, Character.hpComparator);

        Character[] guildArray = guildMembers.toArray(new Character[guildMembers.size()]);

        for (Character character : guildArray) {
            if (!character.isDead()) {
                assignedTeam.addMember(character);
                aliveNum++;
            }
        }

        if (aliveNum > TeamSize) {
            while (newTeam.getTeamSize() < TeamSize) {
                System.out.println("\nChoose a player to add to your team (You have " + (TeamSize - newTeam.getTeamSize())
                        + " more characters to add):");

                for (int i = 0; i < guildArray.length; i++) {
                    if (!newTeam.containsCharacter(guildArray[i]))
                        guildArray[i].printStats(i+1, true, true, true, true, true, true, true);
                }
                int playerChoice = gameHelper.readIntegerFromCmd(1, guildArray.length);
                Character choice = guildArray[playerChoice - 1];
                if (newTeam.containsCharacter(choice))
                {
                    System.out.println("Character has already been selected");
                    continue;
                }

                System.out.println(choice.getName() + " has been added to the team!");
                newTeam.addMember(choice);
            }

            return newTeam;
        }

        For: for (Character character : guildArray) {
            if (!character.isDead()) {
                assignedTeam.addMember(character);
            }
            if (assignedTeam.getTeamSize() == TeamSize) {
                break For;
            }
        }

        System.out.println("Assigning you a team of all alive characters:");
        return assignedTeam;
    }

    public boolean allMembersDead()
    {
        for (Character character : guildMembers) {
            if (!character.isDead())
                return false;
        }

        return true;
    }

    public int numAlive()
    {
        int num = 0;
        for (Character character : guildMembers) {
            if (!character.isDead())
                num++;
        }
        return num;
    }

    public int getTeamMaxSize()
    {
        return TeamSize;
    }

    public boolean oneOfFirstNDead()
    {
        for (int i = 0; i < getTeamMaxSize(); i++) {
            if (guildMembers.get(i).isDead())
                return true;
        }
        return false;
    }
}