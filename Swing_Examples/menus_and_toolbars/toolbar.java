import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;


public class toolbar extends JFrame {
    public toolbar() {
        initUI();
    }

    public final void initUI() {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        menubar.add(file);
        setJMenuBar(menubar);

        JToolBar toolbar = new JToolBar();

        ImageIcon icon = new ImageIcon(getClass().getResource("exit.png"));

        JButton exitButton = new JButton(icon);
        toolbar.add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        add(toolbar, BorderLayout.NORTH);

        setTitle("Simple toolbar");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                toolbar ex = new toolbar();
                ex.setVisible(true);
            }
        });
    }

}
