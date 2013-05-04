import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.SwingUtilities;
import javax.swing.border.EtchedBorder;

public class checkbox extends JFrame {

    private JLabel statusbar;

    public checkbox() {
        initUI();
    }

    public final void initUI() {
        JMenuBar menubar = new JMenuBar();
        JMenu file = new JMenu("File");
        file.setMnemonic(KeyEvent.VK_F);

        JMenu view = new JMenu("View");
        view.setMnemonic(KeyEvent.VK_V);

        JCheckBoxMenuItem sbar = new JCheckBoxMenuItem("Show Statusbar");
        sbar.setState(true);

        sbar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                if (statusbar.isVisible()) {
                    statusbar.setVisible(false);
                } else {
                    statusbar.setVisible(true);
                }
            }
        });

        view.add(sbar);

        menubar.add(file);
        menubar.add(view);

        setJMenuBar(menubar);

        statusbar = new JLabel(" Statusbar");
        statusbar.setBorder(BorderFactory.createEtchedBorder(
                    EtchedBorder.RAISED));
        add(statusbar, BorderLayout.SOUTH);

        setTitle("JCheckBoxMenuItem");
        setSize(360, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main (String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                checkbox ex = new checkbox();
                ex.setVisible(true);
            }
        });
    }
}
