import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class driver {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //basic_window ex = new basic_window();
                tooltip ex = new tooltip();
                ex.setVisible(true);
            }
        });
    }
} 
