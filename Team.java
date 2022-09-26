import java.util.ArrayList;

public abstract class Team {

	//Overridden in Guild
	int MaxSize = 5;
	String name;
	ArrayList<Character> members = new ArrayList<Character>(0);

	public Team(String teamName) {
		this.name = teamName;
	}

	public String getName() {
		return name;
	}

	public Character[] getMembers() {
		return members.toArray(new Character[members.size()]);
	}

	public int addMember(Character member) {
		if (members.contains(member))
			return -1;
		if (members.size() >= MaxSize)
			return -2;

		members.add(member);
		member.setTeam(this);
		return (members.size());
	}

	public int getTeamSize()
	{
		return getMembers().length;
	}

	public boolean containsCharacter(Character character)
	{
		for (Character member : members) {
			if (member == character)
				return true;
		}
		return false;
	}

	public abstract void move(Character member, Team enemyTeam) throws Exception;
}