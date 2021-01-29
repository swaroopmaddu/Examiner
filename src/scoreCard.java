import java.util.Scanner;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Image;
import java.awt.Dimension;
import java.io.InputStream;
import java.io.FileNotFoundException;
import java.net.URI;
import java.awt.Desktop;
import java.io.FileOutputStream;
import java.io.File;
import java.awt.Cursor;
import javax.swing.JButton;
import java.util.StringTokenizer;
import javax.swing.JScrollPane;
import java.awt.Container;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import java.awt.Label;
import javax.swing.BorderFactory;
import java.net.URLEncoder;
import java.util.Date;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.TextField;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


@SuppressWarnings("serial")
public class scoreCard extends JFrame implements ActionListener, Runnable {
    TextField idno_val;
    TextField pwd_val;
    String univ_id;
    String total_score;
    private static String request;
    int score;
    int skip;
    String optns;
    String user_id;
    String all_sec_score;
    Font small;
    Font medium;
    Font large;
    Font enlarged;
    Font medium_b;
    Font large_b;
    String decrypt;
    String decryptscore;
    
    static {
        scoreCard.request = "";
    }
    
    public scoreCard(final String userId, final String sec_score, final int score, final int skip, final String optns, final String all_sec_score) {
        this.idno_val = new TextField();
        this.pwd_val = new TextField();
        this.univ_id = null;
        this.all_sec_score = "";
        this.decrypt = "";
        this.all_sec_score = all_sec_score;
        this.total_score = sec_score;
        this.score = score;
        this.skip = skip;
        this.optns = optns;
        this.user_id = userId;
        final Thread thrd = new Thread(this);
        thrd.start();
    }
    
    public void buildProfile() {
        final JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder(new LineBorder(new Color(3, 131, 135), 2), this.user_id, 4, 2, null, null));
        panel.setBounds(225, 86, 259, 164);
        this.getContentPane().add(panel);
        panel.setLayout(null);
        panel.setFont(this.small);
        this.getContentPane().setLayout(null);
        final JLabel label_1 = new JLabel("Upload the generated .txt");
        label_1.setBounds(20, 17, 200, 28);
        panel.add(label_1);
        final JLabel label_2 = new JLabel("<html>Total Questions :<span style='color:#038387;font-weight:bold;'>  " + examhere.ques_count);
        label_2.setBounds(20, 46, 200, 28);
        panel.add(label_2);
        final JLabel label = new JLabel("<html>Answered Questions :<span style='color:#038387;font-weight:bold;'> " + examhere.answered_count);
        label.setBounds(20, 64, 200, 28);
        panel.add(label);
        final JLabel label_3 = new JLabel("<html>Unnswered Questions :<span style='color:#038387;font-weight:bold;'>  " + (examhere.ques_count - examhere.answered_count));
        label_3.setBounds(20, 85, 200, 28);
        panel.add(label_3);
        final JLabel label_4 = new JLabel("File created on your Desktop.");
        label_4.setBounds(20, 105, 300, 28);
        panel.add(label_4);
        final JLabel label_5 = new JLabel("<html><span style='color:red;font-weight:bold;'>Editing file will disqualify you.");
        label_5.setBounds(20, 125, 300, 28);
        panel.add(label_5);
    }
    
    @Override
    public void run() {
        this.scrCard();
    }
    
    public void scrCard() {
        this.buildProfile();
        try {
            final InputStream font_in = Quiz.class.getResourceAsStream("source/segoeui.ttf");
            final Font font = Font.createFont(0, font_in);
            this.small = font.deriveFont(0, 9.0f);
            this.medium = font.deriveFont(0, 12.0f);
            this.large = font.deriveFont(0, 18.0f);
            this.enlarged = font.deriveFont(1, 50.0f);
            this.medium_b = font.deriveFont(1, 12.0f);
            this.large_b = font.deriveFont(1, 18.0f);
        }
        catch (Exception fe) {
            JOptionPane.showMessageDialog(this, "Font ERROR:" + fe);
        }
        final Dimension wh = Toolkit.getDefaultToolkit().getScreenSize();
        this.getContentPane().setLayout(null);
        this.setBounds((int)wh.getWidth() / 2 - 275, (int)wh.getHeight() / 2 - 220, 550, 470);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setAlwaysOnTop(false);
        final Date date = new Date();
        final String time = URLEncoder.encode(date.toString());
        final Container f = this.getContentPane();
        f.setBackground(new Color(250, 250, 250));
        final JPanel profile = new JPanel();
        profile.setLayout(null);
        profile.setBounds(0, 0, 530, 450);
        profile.setBackground(new Color(245, 245, 245));
        profile.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 15));
        f.add(profile);
        final Label jl = new Label("Exam Status", 1);
        jl.setBounds(220, 20, 150, 25);
        jl.setFont(this.large_b);
        jl.setForeground(new Color(231, 76, 60));
        profile.add(jl);
        final JLabel sid = new JLabel(this.user_id, 0);
        sid.setBounds(50, 250, 100, 50);
        sid.setFont(this.large_b);
        sid.setForeground(new Color(231, 76, 60));
        profile.add(sid);
        final JLabel smarks = new JLabel("", 0);
        smarks.setBounds(120, 280, 350, 45);
        smarks.setFont(this.medium_b);
        smarks.setForeground(new Color(231, 76, 60));
        profile.add(smarks);
        final JLabel yours = new JLabel("", 0);
        yours.setBounds(25, 220, 150, 25);
        yours.setFont(this.medium);
        profile.add(yours);
        final JLabel outof = new JLabel("", 0);
        outof.setBounds(15, 240, 160, 40);
        outof.setFont(this.medium);
        profile.add(outof);
        final Image iconpath = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("source/user.png"));
        final JLabel p_icon = new JLabel(new ImageIcon(iconpath));
        p_icon.setBounds(20, 50, 200, 200);
        profile.add(p_icon);
        final JPanel result_panel = new JPanel();
        result_panel.setLayout(new BoxLayout(result_panel, 3));
        final JScrollPane jsp = new JScrollPane(result_panel, 20, 30);
        jsp.setBorder(BorderFactory.createLineBorder(new Color(230, 230, 230), 1));
        jsp.setBounds(200, 0, 345, 445);
        f.add(jsp);
        final StringTokenizer stk = new StringTokenizer(this.total_score, "@");
        final JLabel footer = new JLabel("<html><center><h2 style='font-weight:bold;'>&copy; Swaroop Maddu");
        footer.setBounds(150, 300, 300, 100);
        footer.setForeground(new Color(231, 76, 60));
        profile.add(footer);
        final JButton ok = new JButton("<html><h4 style='color:white;font-weight:bold;'>OK");
        ok.setCursor(new Cursor(12));
        ok.setActionCommand("OK");
        ok.setBounds(220, 400, 170, 25);
        ok.setBackground(new Color(231, 76, 60));
        ok.addActionListener(this);
        profile.add(ok);
        final String dsktpPath = System.getProperty("user.home");
        File Che1 = null;
        File save_marks = null;
        FileOutputStream fos = null;
        FileOutputStream fos2 = null;
        final Settings exam = new Settings();
        try {
            save_marks = new File(String.valueOf(dsktpPath) + "/Desktop/" + this.user_id + "_" + Settings.subject + ".txt");
            Che1 = new File(String.valueOf(dsktpPath) + "/.System602/" + this.user_id + "_" + Settings.subject + ".txt");
            fos2 = new FileOutputStream(Che1);
            fos = new FileOutputStream(save_marks);
            save_marks.setExecutable(true);
            save_marks.setReadable(true);
            save_marks.setWritable(false);
            try {
                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().browse(new URI("https://swaroopmaddu.github.io/examiner"));
                }
            }
            catch (Exception dsktp) {
                System.out.println("ERROR:" + dsktp);
            }
        }
        catch (FileNotFoundException ex) {}
        final StringBuffer marks_data = new StringBuffer();
        int i = 1;
        if (i == 1) {
            final String su = stk.nextToken();
            this.setVisible(true);
            smarks.setText("Thank you for participating in Teckzite'19");
            smarks.setForeground(new Color(231, 76, 60));
            String stuid = new String();
            stuid = "";
            if (this.user_id.charAt(0) == 'T') {
                stuid = String.valueOf(stuid) + "9";
            }
            if (this.user_id.charAt(1) == 'Z') {
                stuid = String.valueOf(stuid) + "8";
            }
            for (i = 2; i < this.user_id.length(); ++i) {
                String cha = new String();
                cha = "";
                cha = String.valueOf(cha) + this.user_id.charAt(i);
                int num = Integer.parseInt(cha);
                num = 9 - num;
                stuid = String.valueOf(stuid) + Integer.toString(num);
            }
            marks_data.append(String.valueOf(stuid) + this.user_id + "\nTIME STAMP:" + date.toString() + "\n");
        }
        marks_data.append(String.valueOf(Settings.subcode) + "\n" + this.encryptData(this.optns) + "\n");
        try {
            if (save_marks.exists()) {
                fos.write(marks_data.toString().getBytes());
                fos.close();
            }
            if (save_marks.exists()) {
                fos2.write(marks_data.toString().getBytes());
                fos2.close();
            }
        }
        catch (Exception fe2) {
            JOptionPane.showMessageDialog(this, "ERROR:Results Saving Error");
        }
    }
    
    public List<Integer> encryptData(final String plaintext) {
        final List<Integer> num = new ArrayList<Integer>();
        final int[] key = { 87, 101, 51, 116, 101, 52, 109, 55, 101, 52, 109, 64, 84, 90, 49, 57, 48, 120, 104, 101, 120, 87, 101, 51, 116, 101, 52, 109, 55, 101, 52, 109, 64, 84, 90, 49, 57 };
        for (int i = 0; i < plaintext.length(); ++i) {
            int c = plaintext.charAt(i);
            c ^= key[i % key.length];
            num.add(c);
        }
        return num;
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();
        if (command.equals("OK")) {
            System.exit(0);
        }
    }
    
    public int sendTestDetails(final int score, final int skip, final String optns, final String user_id) {
        final String id = user_id;
        final String sec_score = this.all_sec_score;
        final String marks = new StringBuilder(String.valueOf(score)).toString();
        final String unAns = new StringBuilder(String.valueOf(skip)).toString();
        final String options = optns;
        final String urlParameters = "ID=" + id + "&sec_score=" + sec_score + "&Marks=" + marks + "&options=" + options + "&unAns=" + unAns;
        scoreCard.request = "http://10.11.4.99/submit.php";
        try {
            final URL url = new URL(scoreCard.request);
            final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            connection.setRequestProperty("charset", "utf-8");
            connection.setRequestProperty("Content-Length", new StringBuilder().append(Integer.toString(urlParameters.getBytes().length)).toString());
            connection.setUseCaches(false);
            final DataOutputStream wr = new DataOutputStream(connection.getOutputStream());
            wr.writeBytes(urlParameters);
            wr.flush();
            wr.close();
            final Scanner sc = new Scanner(connection.getInputStream());
            while (sc.hasNextLine()) {
                JOptionPane.showMessageDialog(null, sc.nextLine());
            }
            sc.close();
            connection.disconnect();
            return 1;
        }
        catch (Exception te) {
            System.out.println("ERROR:" + te);
            return 0;
        }
    }
}
