import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class grid_layout_example extends JFrame {
    public grid_layout_example() {
        //Initialize code here
        initUI();
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                grid_layout_example ex = new grid_layout_example();
                ex.setVisible(true);
            }
        });
    }

}
