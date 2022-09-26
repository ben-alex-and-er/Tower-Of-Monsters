public class StudentTeam extends Team {

    private Guild guild;

    public StudentTeam(String teamName, Guild guild) {
        super(teamName);
        this.guild = guild;
        MaxSize = guild.getTeamMaxSize();
    }

    public void move(Character member, Team enemyTeam) throws Exception {
        ((Student) member).strike(enemyTeam);
    }

    public Guild getGuild()
    {
        return guild;
    }
}