import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class TowerOfMonsters {

	GameHelper gameHelper = new GameHelper();

	public void runGame(String filename, Guild guild) {
		try {
			int floor = 0;
			File myFile = new File(filename);
			Scanner myReader = new Scanner(myFile);
			StudentTeam playerTeam = (StudentTeam) guild.getTeam();

			Floor: while (myReader.hasNextLine()) {
				floor++;
				String data = myReader.nextLine();

				
				MonsterTeam monsterTeam = new MonsterTeam("Floor " + String.valueOf(floor) + " Monsters");

				System.out.print("//////////");
				gameHelper.timeGap(10);

				System.out.print("Floor " + floor);
				gameHelper.timeGap(10);
				
				System.out.println("//////////\n");
				gameHelper.timeGap(10);

				if (floor % 6 == 0) {
					for (int h = 0; h < 10; h++) {
						System.out.print("/");
						gameHelper.timeGap(10);
					}

					gameHelper.timeGap(200);

					System.out.print("BOSS ");

					gameHelper.timeGap(200);

					System.out.print("BATTLE!");

					for (int h = 0; h < 9; h++) {
						System.out.print("/");
						gameHelper.timeGap(10);
					}
					System.out.println("/");
				}

				// add each character to a team
				String[] lineArray = data.split(";");

				for (String monster : lineArray) {
					monsterToTeam(monster, monsterTeam);
				}


				// battle

				Battle battle = new Battle();
				Team fightWin = null;

				while (fightWin == null) {
					fightWin = battle.fight(playerTeam, monsterTeam);

					// find if all guild is dead
					if ((guild.allMembersDead())) {
						System.out.println("All the guild members are dead!");
						System.out.println("GAME OVER!");
						break Floor;
					}
					if (fightWin == monsterTeam) {
						System.out.println("You need to choose a new team!");
						playerTeam = (StudentTeam) guild.getTeam();
					}

				}
				System.out.println();

				if (guild.numAlive() > guild.getTeamMaxSize()) {
					if (guild.oneOfFirstNDead()) {
						System.out.println("At least one of your members is dead!");
					}

					System.out.println("Would you like to choose a new team?");
					System.out.println("1: Yes");
					System.out.println("2: No");
					int newTeamChoice = gameHelper.readIntegerFromCmd(1, 2);
					if (newTeamChoice == 1) {
						playerTeam = (StudentTeam) guild.getTeam();
					}
				} else {
					playerTeam = (StudentTeam) guild.getTeam();
				}

			}
			System.out.println("You managed to reach floor " + floor);
			myReader.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void monsterToTeam(String monsterArray, Team team) {
		try {
			// Get info from file
			String[] monsterName = monsterArray.split("\\(");
			String[] monsterType = monsterName[1].split(",");
			String[] monsterlevel = monsterType[1].split("\\)");
			int monsterLevel = Integer.parseInt(monsterlevel[0]);

			if (monsterType[0].equals("Minion")) {
				Minion monster = new Minion(monsterName[0]);
				//Level up until actual level
				for (int i = 1; i < monsterLevel; i++) {
					monster.increaseXP(monster.getTargetXP());
				}
				team.addMember(monster);
			} else {
				Boss monster = new Boss(monsterName[0]);
				//Level up until actual level
				for (int i = 1; i < monsterLevel; i++) {
					monster.increaseXP(monster.getTargetXP());
				}
				team.addMember(monster);
			}

		} catch (Exception e) {
			System.err.println("Error reading file");
		}
	}

	public static void main(String[] args) {
		Guild guild = new Guild();

		// create new team
		guild.addMember(new AIStudent("AI Student"));
		guild.addMember(new AeroElecStudent("Aero Student"));
		guild.addMember(new BioElecStudent("Bio Student"));
		guild.addMember(new CSStudent("CS Student"));
		guild.addMember(new CyberStudent("Cyber Student"));
		guild.addMember(new MechStudent("Mech Student"));
		guild.addMember(new SEStudent("SE Student"));
		guild.addMember(new ElectricalStudent("Electrical Student"));

		System.out.println("Entering the Tower of Monsters!");

		// run tower
		TowerOfMonsters tower = new TowerOfMonsters();
		String file;
		try {
			file = args[0];
		} catch (Exception e) {
			file = "easy.txt";
		}
		tower.runGame(file, guild);
	}
}