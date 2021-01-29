import javax.swing.UIManager;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.Image;
import java.awt.Container;
import java.awt.Dimension;
import java.io.InputStream;
import java.awt.Color;
import java.awt.Label;
import javax.swing.ImageIcon;
import java.awt.Toolkit;
import javax.swing.JOptionPane;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.TextField;
import java.awt.event.KeyListener;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


@SuppressWarnings("serial")
class Login extends JFrame implements ActionListener, KeyListener {
    TextField idno_val;
    TextField pwd_val;
    String univ_id;
    JLabel footer;
    JLabel errorMsg;
    JButton sign_in;
    public static Font pFont;
    public static Font bFont;
    public static Font titleFont;
    Font small;
    Font medium;
    Font big;
    Font large;
    Font small_b;
    Font medium_b;
    Font big_b;
    Font large_b;
    boolean res;
    String uid;
    String pwd;
    
    static {
        Login.pFont = null;
        Login.bFont = null;
        Login.titleFont = null;
    }
    
    public Login() {
        this.idno_val = new TextField();
        this.pwd_val = new TextField();
        this.univ_id = null;
        this.footer = new JLabel();
        this.res = false;
        try {
            final InputStream font_in = Quiz.class.getResourceAsStream("source/segoeui.ttf");
            final Font font = Font.createFont(0, font_in);
            this.small = font.deriveFont(0, 9.0f);
            this.medium = font.deriveFont(0, 12.0f);
            this.big = font.deriveFont(0, 14.0f);
            this.large = font.deriveFont(0, 18.0f);
            this.small_b = font.deriveFont(1, 9.0f);
            this.medium_b = font.deriveFont(1, 12.0f);
            this.big_b = font.deriveFont(1, 14.0f);
            this.large_b = font.deriveFont(1, 18.0f);
        }
        catch (Exception fe) {
            JOptionPane.showMessageDialog(this, "Font ERROR:" + fe);
        }
        final Dimension wh = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLayout(null);
        this.setBounds((int)wh.getWidth() / 2 - 200, (int)wh.getHeight() / 2 - 100, 400, 200);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        final Container f = this.getContentPane();
        f.setLayout(null);
        final Image iconpath = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("source/icon.png"));
        final JLabel img = new JLabel(new ImageIcon(iconpath));
        img.setBounds(130, 15, 180, 50);
        f.add(img);
        final Label idno = new Label("Teckzite ID", 2);
        idno.setForeground(new Color(231, 76, 60));
        idno.setBounds(50, 65, 100, 33);
        this.idno_val.setBounds(160, 70, 175, 22);
        idno.setFont(this.medium_b);
        this.idno_val.setFont(this.medium_b);
        f.add(idno);
        f.add(this.idno_val);
        final Label pwd = new Label("Password", 2);
        pwd.setForeground(new Color(231, 76, 60));
        pwd.setBounds(50, 100, 100, 33);
        pwd.setFont(this.medium_b);
        this.pwd_val.setEchoChar('*');
        this.pwd_val.setFont(this.medium_b);
        this.pwd_val.setBounds(160, 105, 175, 22);
        f.add(pwd);
        f.add(this.pwd_val);
        (this.errorMsg = new JLabel("", 0)).setForeground(Color.RED);
        this.errorMsg.setBounds(80, 5, 250, 15);
        this.errorMsg.setFont(this.medium);
        f.add(this.errorMsg);
        (this.footer = new JLabel("<html><h4>&copy; Swaroop Maddu </h4></html>", 2)).setBounds(130, 175, 220, 25);
        this.footer.setForeground(new Color(231, 76, 60));
        this.footer.setFont(new Font("Segoe UI", 1, 9));
        f.add(this.footer);
        (this.sign_in = new JButton("LOGIN")).setBounds(160, 140, 100, 22);
        this.sign_in.setBackground(new Color(231, 76, 60));
        this.sign_in.setForeground(Color.WHITE);
        this.sign_in.addActionListener(this);
        this.idno_val.addKeyListener(this);
        this.pwd_val.addKeyListener(this);
        this.add(this.sign_in);
        this.setVisible(true);
    }
    
    @Override
    public void keyTyped(final KeyEvent ke) {
        if (ke.getKeyChar() == '\n') {
            this.sign_in.doClick();
        }
    }
    
    @Override
    public void keyPressed(final KeyEvent ke) {
    }
    
    @Override
    public void keyReleased(final KeyEvent ke) {
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();
        if (command.equals("LOGIN")) {
            this.uid = this.idno_val.getText();
            this.pwd = this.pwd_val.getText();
            if (!this.uid.equals("") && !this.pwd.equals("") && this.uid.length() > 4 && this.pwd.length() >= 1) {
                this.passwdcheck(this.uid, this.pwd);
            }
            else {
                this.errorMsg.setText("Invalid User Credentials.!");
                this.errorMsg.setFont(new Font("Serif", 1, 12));
                this.errorMsg.setForeground(Color.RED);
            }
        }
    }
    
    public void passwdcheck(final String uid, final String pwd) {
        try {
            final InputStream f = Quiz.class.getResourceAsStream("source/password.txt");
            final InputStreamReader input_file = new InputStreamReader(f);
            final BufferedReader reader_file = new BufferedReader(input_file);
            String line;
            while ((line = reader_file.readLine()) != null && !this.res) {
                this.res = line.contains(String.valueOf(uid) + "-" + pwd);
                if (this.res) {
                    this.filecheck();
                    return;
                }
            }
            if (!this.res) {
                this.errorMsg.setText("Invalid User Credentials.!");
                this.errorMsg.setFont(new Font("Serif", 1, 14));
                this.errorMsg.setForeground(Color.RED);
            }
        }
        catch (Exception ex) {}
    }
    
    
    public void filecheck() {
        final String dsktpPath = System.getProperty("user.home");
        final File Che = new File(String.valueOf(dsktpPath) + "/Desktop/" + this.uid + "_" + Settings.subject + ".txt");
        final File Che2 = new File(String.valueOf(dsktpPath) + "/.System602/" + this.uid + "_" + Settings.subject + ".txt");
        if (Che2.exists() || Che.exists()) {
            this.res = false;
            this.errorMsg.setText("Exam Already Finished!");
            this.errorMsg.setFont(new Font("Serif", 1, 12));
            this.errorMsg.setForeground(Color.RED);
        }
        else {
            this.setVisible(false);
            this.exampasswd();
        }
    }
    
    public void exampasswd() {
        String exam_pwd;
        Settings exa;
        do {
            UIManager.put("OptionPane.messageForeground", new Color(231, 76, 60));
            UIManager.put("Button.background", new Color(231, 76, 60));
            UIManager.put("Button.foreground", new Color(255, 255, 255));
            exam_pwd = JOptionPane.showInputDialog(null, "Enter Exam Password:", "Exam Password", -1);
            exa = new Settings();
        } while (!exam_pwd.equals(exa.examPwd));
        final examhere eh = new examhere(this.idno_val.getText().toUpperCase());
        final WListener w = new WListener(eh);
        eh.addWindowListener(w);
        eh.addWindowFocusListener(w);
    }
}
