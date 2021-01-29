import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.InputStream;
import java.awt.Cursor;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import java.awt.Toolkit;
import javax.swing.BorderFactory;
import java.awt.Color;
import javax.swing.JScrollPane;
import java.awt.Font;
import java.awt.Dimension;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Container;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


@SuppressWarnings("serial")
class examhere extends JFrame implements ActionListener {
    public static int ques_count;
    int no_sec;
    public static String q_options;
    Container f;
    JButton[][] jb;
    JTextField[] inputs;
    char[] ans;
    JButton next;
    JButton prev;
    JButton submit;
    static JButton submit2;
    JLabel jl;
    StringBuffer sb;
    String options2;
    String userId;
    JPanel exam;
    JLabel ans_qus_count;
    public static int flag;
    int sec_index;
    static int answered_count;
    JLabel sec_dsp;
    int[] sec_qno;
    JLabel[] studentinfo;
    Dimension wh;
    Font small;
    Font medium;
    Font big;
    Font large;
    Font small_b;
    Font medium_b;
    Font big_b;
    Font large_b;
    JPanel confirm_p;
    JButton yesb;
    JButton nob;
    JScrollPane jsp;
    
    static {
        examhere.ques_count = Settings.Qcount;
        examhere.q_options = Settings.QAns;
        examhere.submit2 = new JButton("SUBMIT2");
        examhere.flag = 0;
        examhere.answered_count = 0;
    }
    
    public void confirmDialog() {
        this.confirm_p.setBounds(891, 579, 400, 98);
        this.confirm_p.setBackground(new Color(240, 240, 240));
        this.confirm_p.setLayout(null);
        final String confirmation = "Sure you want to Submit?";
        (this.jl = new JLabel(confirmation)).setFont(new Font("Tahoma", 1, 11));
        this.jl.setForeground(new Color(231, 76, 60));
        this.jl.setBounds(20, 4, 350, 50);
        this.confirm_p.add(this.jl);
        (this.yesb = new JButton("Yes")).setFont(new Font("Tahoma", 1, 11));
        this.yesb.setForeground(Color.WHITE);
        this.yesb.addActionListener(this);
        (this.nob = new JButton("No")).setFont(new Font("Tahoma", 1, 11));
        this.nob.setForeground(Color.WHITE);
        this.nob.addActionListener(this);
        this.yesb.setBounds(20, 65, 60, 25);
        this.nob.setBounds(85, 65, 60, 25);
        this.confirm_p.add(this.yesb);
        this.confirm_p.add(this.nob);
        this.confirm_p.setBorder(BorderFactory.createLineBorder(new Color(100, 100, 100)));
        this.f.add(this.confirm_p);
    }
    
    public examhere(final String idno) {
        this.no_sec = 1;
        this.f = this.getContentPane();
        this.jb = new JButton[examhere.ques_count][9];
        this.inputs = new JTextField[examhere.ques_count];
        this.ans = new char[examhere.ques_count];
        this.next = new JButton("NEXT");
        this.prev = new JButton("PREV");
        this.submit = new JButton("SUBMIT");
        this.sb = new StringBuffer();
        this.userId = new String();
        this.exam = new JPanel();
        this.ans_qus_count = new JLabel("Answered: 0/" + examhere.ques_count);
        this.sec_index = 0;
        this.sec_dsp = new JLabel("sec number: " + this.sec_index);
        this.sec_qno = new int[] { 0, examhere.ques_count };
        this.studentinfo = new JLabel[3];
        this.wh = Toolkit.getDefaultToolkit().getScreenSize();
        this.confirm_p = new JPanel();
        this.jsp = new JScrollPane(this.exam, 22, 30);
        this.confirmDialog();
        this.confirm_p.setVisible(false);
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
        this.getContentPane().setLayout(null);
        this.setBounds(0, 0, (int)this.wh.getWidth(), (int)this.wh.getHeight());
        this.exam.setBackground(Color.WHITE);
        this.exam.setLayout(new BoxLayout(this.exam, 3));
        final Image lpath = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource("source/rgu.png"));
        final JLabel rgu_logo = new JLabel(new ImageIcon(lpath));
        rgu_logo.setBounds((int)this.wh.getWidth() / 2 - 18, 20, 50, 50);
        this.f.add(rgu_logo);
        this.userId = idno;
        this.studentinfo[0] = new JLabel(this.userId);
        this.studentinfo[1] = new JLabel(Settings.subject, 0);
        final String date = String.format("%1$s %2$tB %2$te, %2$tY", "On : ", new Date());
        this.studentinfo[2] = new JLabel(date);
        final JLabel copy_rght = new JLabel("<html><h3><b>&copy; Swaroop Maddu</b></html>");
        copy_rght.setForeground(new Color(231, 76, 60));
        copy_rght.setBounds((int)this.wh.getWidth() / 2 - 100, (int)this.wh.getHeight() - 80, 230, 25);
        copy_rght.setFont(this.medium_b);
        this.f.add(copy_rght);
        this.studentinfo[0].setBounds(50, 25, 550, 30);
        this.studentinfo[1].setBounds((int)this.wh.getWidth() / 2 - 250, 65, 500, 30);
        this.studentinfo[2].setBounds(50, 60, 250, 30);
        this.studentinfo[0].setFont(this.large_b);
        this.studentinfo[1].setFont(this.large_b);
        this.studentinfo[2].setFont(this.medium_b);
        this.studentinfo[0].setForeground(new Color(231, 76, 60));
        this.studentinfo[1].setForeground(new Color(231, 76, 60));
        this.studentinfo[2].setForeground(new Color(231, 76, 60));
        this.f.add(this.studentinfo[0]);
        this.f.add(this.studentinfo[1]);
        this.f.add(this.studentinfo[2]);
        this.next.setBounds(160, (int)this.wh.getHeight() - 80, 100, 25);
        this.prev.setBounds(50, (int)this.wh.getHeight() - 80, 100, 25);
        this.submit.setBounds((int)this.wh.getWidth() - 150, (int)this.wh.getHeight() - 80, 100, 25);
        this.submit.addActionListener(this);
        this.submit.setBackground(new Color(231, 76, 60));
        this.submit.setForeground(Color.WHITE);
        this.submit.setCursor(new Cursor(12));
        this.next.addActionListener(this);
        this.next.setBackground(new Color(45, 55, 155));
        this.next.setForeground(Color.WHITE);
        this.next.setCursor(new Cursor(12));
        this.prev.addActionListener(this);
        this.prev.setBackground(new Color(45, 55, 155));
        this.prev.setForeground(Color.WHITE);
        this.prev.setCursor(new Cursor(12));
        examhere.submit2.addActionListener(this);
        this.f.add(this.submit);
        this.nextSection();
        this.jsp.getVerticalScrollBar().setUnitIncrement(32);
        this.jsp.setBounds(50, 100, (int)this.wh.getWidth() - 100, (int)this.wh.getHeight() - 200);
        this.f.add(this.jsp);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
        this.setAlwaysOnTop(true);
        this.setUndecorated(true);
        this.setVisible(true);
        this.sec_dsp.setBounds(300, (int)this.wh.getHeight() - 80, 180, 25);
        this.sec_dsp.setFont(this.big_b);
        this.ans_qus_count.setHorizontalAlignment(2);
        this.ans_qus_count.setForeground(new Color(231, 76, 60));
        this.ans_qus_count.setBounds((int)this.wh.getWidth() - 450, (int)this.wh.getHeight() - 80, 180, 25);
        this.ans_qus_count.setFont(new Font("SansSerif", 1, 14));
        this.f.add(this.ans_qus_count);
        final JLabel timer = new JLabel("00:00");
        timer.setFont(this.large_b);
        timer.setBounds((int)this.wh.getWidth() - 100, 50, 100, 25);
        this.f.add(timer);
        new clock(timer, examhere.submit2);
    }
    
    public void prevSection() {
        if (this.sec_index > 1) {
            --this.sec_index;
            final int qfrom = this.sec_index - 1;
            final int qto = this.sec_index;
            if (qfrom >= 0) {
                this.displaySec(qfrom, qto);
            }
            this.next.setEnabled(true);
            if (qfrom == 0) {
                this.prev.setEnabled(false);
            }
        }
    }
    
    public void nextSection() {
        if (this.sec_index < this.no_sec) {
            final int qfrom = this.sec_index;
            final int qto = this.sec_index + 1;
            ++this.sec_index;
            this.displaySec(qfrom, qto);
            this.prev.setEnabled(true);
            if (qto == this.no_sec) {
                this.next.setEnabled(false);
            }
        }
    }
    
    
    public void displaySec(final int from, final int to) {
        this.jsp.getVerticalScrollBar().setValue(0);
        this.sec_dsp.setText("Section: " + this.sec_index + "/" + this.no_sec);
        this.sb.delete(0, this.sb.length());
        for (int k = 0; k < examhere.ques_count; ++k) {
            if (this.ans[k] == '\0') {
                this.sb.append("*");
            }
            else {
                this.sb.append(this.ans[k]);
            }
        }
        final String temp_options = this.sb.toString();
        final String options = "ABCDEFGHI";
        this.exam.removeAll();
        final JPanel[] jimp = new JPanel[examhere.ques_count + 1];
        final JPanel[] jps = new JPanel[examhere.ques_count + 1];
        for (int i = this.sec_qno[from]; i < examhere.ques_count + 1 && i < this.sec_qno[to]; ++i) {
            final String s = "images/" + Settings.subcode + "/" + (i + 1) + Settings.ext;
            final Image path = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(s));
            final JLabel l = new JLabel(new ImageIcon(path));
            (jimp[i] = new JPanel()).setLayout(new FlowLayout(0));
            jimp[i].add(l);
            this.exam.add(jimp[i]);
            (jps[i] = new JPanel()).setLayout(new FlowLayout(0, 25, 20));
            for (int j = 0; j < Integer.parseInt(new StringBuilder(String.valueOf(examhere.q_options.charAt(i))).toString()); ++j) {
                final char op = options.charAt(j);
                (this.jb[i][j] = new JButton(" " + op + " ")).setSize(100, 35);
                this.jb[i][j].setActionCommand(String.valueOf(Integer.toString(i)) + "#" + Integer.toString(j));
                this.jb[i][j].addActionListener(this);
                if (temp_options.charAt(i) == op) {
                    this.jb[i][j].setBackground(new Color(107, 11, 2));
                }
                jps[i].add(this.jb[i][j]);
            }
            jps[i].setSize(400, 75);
            jimp[i].setBackground(Color.WHITE);
            jps[i].setBackground(Color.WHITE);
            this.exam.add(jps[i]);
            this.exam.updateUI();
        }
    }
    
    @Override
    public void actionPerformed(final ActionEvent e) {
        final String command = e.getActionCommand();
        this.sb.delete(0, this.sb.length());
        if (command.equals("SUBMIT")) {
            final String updated_count = "<html>You have answered: " + examhere.answered_count + "<br>Sure you want to confirm?";
            this.jl.setText(updated_count);
            this.confirm_p.setVisible(false);
            this.confirm_p.setVisible(true);
        }
        else if (command.equals("Yes")) {
            for (int k = 0; k < examhere.ques_count; ++k) {
                if (this.ans[k] == '\0') {
                    this.sb.append("*");
                }
                else {
                    this.sb.append(this.ans[k]);
                }
            }
            examhere.flag = 1;
            this.options2 = this.sb.toString();
            try {
                this.evaluateAns(this.sb.toString(), this.userId);
                this.setVisible(false);
            }
            catch (Exception e2) {
                System.out.println("ERROR :" + e2);
                e2.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error :" + e2);
            }
        }
        else if (command.equals("No")) {
            this.confirm_p.setVisible(false);
        }
        else if (command.equals("SUBMIT2")) {
            this.sb.delete(0, this.sb.length());
            for (int k = 0; k < examhere.ques_count; ++k) {
                if (this.ans[k] == '\0') {
                    this.sb.append("*");
                }
                else {
                    this.sb.append(this.ans[k]);
                }
            }
            try {
                this.evaluateAns(this.sb.toString(), this.userId);
                this.setVisible(false);
            }
            catch (Exception e3) {
                System.out.println("ERROR" + e3);
            }
        }
        else if (command.equals("NEXT")) {
            this.nextSection();
        }
        else if (command.equals("PREV")) {
            this.prevSection();
        }
        else {
            final String[] a = command.split("#");
            final int qin = Integer.parseInt(a[0]);
            final int opin = Integer.parseInt(a[1]);
            final Color c = this.jb[qin][opin].getBackground();
            if (this.ans[qin] == '\0') {
                this.jb[qin][opin].setBackground(new Color(107, 11, 2));
                this.ans_qus_count.setText("Answered: " + ++examhere.answered_count + "/" + examhere.ques_count);
                this.ans_qus_count.setForeground(new Color(231, 76, 60));
                this.ans_qus_count.setFont(new Font("Serif", 1, 14));
            }
            else {
                switch (this.ans[qin]) {
                    case 'A': {
                        this.jb[qin][0].setBackground(c);
                        break;
                    }
                    case 'B': {
                        this.jb[qin][1].setBackground(c);
                        break;
                    }
                    case 'C': {
                        this.jb[qin][2].setBackground(c);
                        break;
                    }
                    case 'D': {
                        this.jb[qin][3].setBackground(c);
                        break;
                    }
                    case 'E': {
                        this.jb[qin][4].setBackground(c);
                        break;
                    }
                    case 'F': {
                        this.jb[qin][5].setBackground(c);
                        break;
                    }
                    case 'G': {
                        this.jb[qin][6].setBackground(c);
                        break;
                    }
                    case 'H': {
                        this.jb[qin][7].setBackground(c);
                        break;
                    }
                    case 'I': {
                        this.jb[qin][8].setBackground(c);
                        break;
                    }
                    default: {
                        JOptionPane.showMessageDialog(this, this.ans[qin]);
                        break;
                    }
                }
                this.jb[qin][opin].setBackground(new Color(107, 11, 2));
            }
            this.ans[qin] = this.jb[qin][opin].getText().charAt(1);
        }
    }
    
    private void evaluateAns(final String user_options, final String user_id) throws Exception {
        examhere.flag = 1;
        final String org_ans = "";
        final String user_ans = user_options;
        int sec_id = 0;
        int score = 0;
        int skip = 0;
        int attempted = 0;
        int total_s = 0;
        final StringBuffer sec_marks = new StringBuffer();
        final StringBuffer all_sec_score = new StringBuffer();
        while (sec_id < this.no_sec) {
            score = 0;
            skip = 0;
            attempted = 0;
            final int lastQus = this.sec_qno[sec_id + 1];
            for (int i = this.sec_qno[sec_id]; i < lastQus; ++i) {
                if (user_ans.length() > i && org_ans.length() > i) {
                    if (user_ans.charAt(i) != '*') {
                        if (org_ans.charAt(i) == user_ans.charAt(i)) {
                            ++score;
                            ++total_s;
                        }
                        ++attempted;
                    }
                    else {
                        ++skip;
                    }
                }
            }
            all_sec_score.append(" " + score);
            final int qcount = lastQus - this.sec_qno[sec_id];
            sec_marks.append("@Score:" + score + "/" + qcount + " (Answered:" + attempted + ")");
            ++sec_id;
        }
        all_sec_score.append("@");
        sec_marks.append("@");
        final String temp_marks = sec_marks.toString();
        sec_marks.delete(0, sec_marks.length());
        sec_marks.append("@" + total_s);
        sec_marks.append(temp_marks);
        this.setVisible(false);
        final scoreCard sc = new scoreCard(this.userId, sec_marks.toString(), total_s, skip, user_ans, all_sec_score.toString());
    }
}
