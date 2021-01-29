import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JLabel;


class clock implements Runnable
{
    JLabel counter;
    JButton submit2;
    int quiz_time;
    int sec;
    int temp_qtime;
    int flag;
    int tred;
    
    public clock(final JLabel clock_label, final JButton submit) {
        this.quiz_time = Settings.time;
        this.sec = 60;
        this.temp_qtime = this.quiz_time;
        this.tred = (int)(this.quiz_time * 0.1);
        this.flag = examhere.flag;
        this.counter = clock_label;
        this.submit2 = submit;
        new Thread(this).start();
    }
    
    @Override
    public void run() {
        while (true) {
            final int tsec = this.sec % 60;
            String sec00;
            if (tsec < 10) {
                sec00 = "0" + tsec;
            }
            else {
                sec00 = new StringBuilder().append(tsec).toString();
            }
            String hour00;
            if (this.quiz_time < 10) {
                hour00 = "0" + this.quiz_time;
            }
            else {
                hour00 = new StringBuilder().append(this.quiz_time).toString();
            }
            this.counter.setText(String.valueOf(hour00) + ":" + sec00);
            final int ttemp = Integer.parseInt(hour00);
            if (ttemp < 5) {
                this.counter.setForeground(Color.RED);
                this.counter.setFont(new Font("Serif", 1, 25));
            }
            else if (ttemp <= this.tred) {
                this.counter.setForeground(Color.RED);
            }
            else {
                this.counter.setForeground(new Color(231, 76, 60));
            }
            if (this.sec == 60) {
                --this.quiz_time;
            }
            --this.sec;
            try {
                Thread.sleep(1000L);
                if (this.sec != 0) {
                    continue;
                }
                if (this.quiz_time == 0 && examhere.flag == 0) {
                    this.submit2.doClick();
                }
                this.sec = 60;
            }
            catch (InterruptedException ie) {
                System.out.println("Clock Interrupted");
            }
        }
    }
}
