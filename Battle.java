import java.util.ArrayList;
import java.util.Collections;

public class Battle {
    GameHelper gameHelper = new GameHelper();

    public Team fight(StudentTeam team1, MonsterTeam team2) {
        Team winnningTeam = null;
        try {
            ArrayList<Character> characterList = getCharacterList(team1, team2);
            int roundNum = 1;
            Team enemyTeam;

            Rounds: for (int r = 0; r < 30; r++) {

                gameHelper.clearConsole();

                //Print Round number
                System.out.println();
                for (int i = 0; i < 10; i++) {
                    System.out.print('/');
                    gameHelper.timeGap(10);
                }
                System.out.print("Round " + roundNum + ":");
                for (int i = 0; i < 10; i++) {
                    System.out.print('/');
                    gameHelper.timeGap(10);
                }
                System.out.println();

                gameHelper.timeGap(500);

                // move
                Move: for (int n = 0; n < team1.getTeamSize() + team2.getTeamSize(); n++) {
                    if (characterList.get(n).getTeam() == team1) {
                        enemyTeam = team2;
                    } else {
                        enemyTeam = team1;
                    }

                    try {
                        this.move(characterList.get(n), enemyTeam, team1, team2);
                    }
                    // catch exception
                    catch (Exception e) {
                    }

                    if (allDead(team1) || allDead(team2))
                        break Move;
                }

                // Says the stats of the characters
                printBothTeamStats(team1, team2);

                // if theres a winning team i e all characters are dead
                if (allDead(team1)) {
                    winnningTeam = team2;
                    break Rounds;
                } else if (allDead(team2)) {
                    winnningTeam = team1;
                    break Rounds;
                }

                gameHelper.timeGap(1000);

                roundNum++;
            }

            if (winnningTeam != null) {
                System.out.println(winnningTeam.getName() + " won the battle!");
            } else {
                System.out.println("It was a draw");
            }
        } catch (Exception e) {
        }

        return winnningTeam;
    }

    private void move(Character member, Team enemyTeam, StudentTeam team1, MonsterTeam team2) throws Exception {
        if (!member.isDead()) {
            // attack
            if (enemyTeam == team1) {
                team2.move(member, enemyTeam);
                return;
            }

            team1.move(member, enemyTeam);
            return;
        }

        throw new Exception("Character is dead!");
    }

    private ArrayList<Character> getCharacterList(Team team1, Team team2) {
        // sort characters into order of speed
        ArrayList<Character> characterList = new ArrayList<Character>();

        for (Character character : team1.getMembers()) {
            characterList.add(character);
        }
        for (Character character : team2.getMembers()) {
            characterList.add(character);
        }

        Collections.sort(characterList, Character.speedComparator);

        return characterList;
    }

    private void printBothTeamStats(Team team1, Team team2) {
        printTeamStats(team1);
        gameHelper.timeGap(1000);
        printTeamStats(team2);
    }

    private void printTeamStats(Team team) {
        System.out.println(team.getName() + ":");
        for (int a = 0; a < team.getTeamSize(); a++) {
            
            Character charStat = team.getMembers()[a];
            System.out.print("Character " + (a + 1) + ":" + " " + charStat.getName() + "-");
            if (!charStat.isDead()) {
                charStat.printStats(0, false, false, false, true, false, false, false);
            } else {
                System.out.println("\nDEAD!\n");
                gameHelper.timeGap(200);
            }
        }
    }

    private boolean allDead(Team team) {
        for (Character character : team.getMembers()) {
            if (!character.isDead())
                return false;
        }
        return true;
    }
}