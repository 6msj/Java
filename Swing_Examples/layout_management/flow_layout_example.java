import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.SwingUtilities;

public class flow_layout_example extends JFrame {
    public flow_layout_example() {
        //Initialize code here
        initUI();
    }

    public final void initUI() {
        JPanel panel = new JPanel();

        JTextArea area = new JTextArea("text area");
        area.setPreferredSize(new Dimension(100, 100));

        JButton button = new JButton("button");
        panel.add(button);

        JTree tree = new JTree();
        panel.add(tree);

        panel.add(area);

        add(panel);

        pack();

        setTitle("FlowLayout Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                flow_layout_example ex = new flow_layout_example();
                ex.setVisible(true);
            }
        });
    }
}
