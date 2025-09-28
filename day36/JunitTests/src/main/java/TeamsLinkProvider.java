public class TeamsLinkProvider {

    public static String getTeamsChatLink(String email) {
        return "https://teams.microsoft.com/l/chat/0/0?users=" + email;
    }
}