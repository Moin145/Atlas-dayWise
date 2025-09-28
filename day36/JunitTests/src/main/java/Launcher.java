import java.awt.Desktop;
import java.net.URI;

public class Launcher {
    public static void main(String[] args) {
        try {
            String email = "moin.refaii@gmail.com";

            String teamsLink = TeamsLinkProvider.getTeamsChatLink(email);

            System.out.println("Launching Teams chat for: " + email);
            System.out.println("Deep link: " + teamsLink);


            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(teamsLink));
            } else {
                System.err.println("Desktop is not supported, cannot launch browser.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}