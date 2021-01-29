import java.awt.event.WindowEvent;
import java.awt.Toolkit;
import java.awt.Dimension;
import javax.swing.JFrame;
import java.awt.event.WindowFocusListener;
import java.awt.event.WindowAdapter;

public class WListener extends WindowAdapter implements WindowFocusListener
{
    JFrame jfm;
    Dimension wh;
    
    public WListener(final JFrame jfm) {
        this.wh = Toolkit.getDefaultToolkit().getScreenSize();
        this.jfm = jfm;
    }
    
    @Override
    public void windowGainedFocus(final WindowEvent we) {
        this.change(we);
    }
    
    @Override
    public void windowLostFocus(final WindowEvent we) {
        this.change(we);
    }
    
    @Override
    public void windowDeactivated(final WindowEvent we) {
        this.change(we);
    }
    
    @Override
    public void windowActivated(final WindowEvent we) {
        this.change(we);
    }
    
    @Override
    public void windowDeiconified(final WindowEvent we) {
        this.change(we);
    }
    
    @Override
    public void windowIconified(final WindowEvent we) {
        this.change(we);
    }
    
    @Override
    public void windowClosed(final WindowEvent we) {
        examhere.submit2.doClick();
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void windowClosing(final WindowEvent we) {
        examhere.submit2.doClick();
        try {
            Thread.sleep(2000L);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void windowOpened(final WindowEvent we) {
        this.change(we);
    }
    
    public void change(final WindowEvent we) {
        try {
            this.jfm.setExtendedState(6);
            this.jfm.setSize((int)this.wh.getWidth(), (int)this.wh.getHeight());
            this.jfm.setFocusable(true);
            this.jfm.setAlwaysOnTop(true);
            we.getWindow().toFront();
            we.getWindow().requestFocus();
            we.getWindow().setAlwaysOnTop(false);
            we.getWindow().setAlwaysOnTop(true);
        }
        catch (Exception ex) {}
    }
}
