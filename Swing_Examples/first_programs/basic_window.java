import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class basic_window extends JFrame {

    public basic_window() {
        initUI();
    }

    public final void initUI() {
        JPanel panel = new JPanel();
        getContentPane().add(panel);
        panel.setLayout(null);

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(50, 60, 80, 30);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                System.exit(0);
            }
        });

        panel.add(quitButton);
        setTitle("Quit Button");
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    /*
     *public static void main(String[] args) {
     *    SwingUtilities.invokeLater(new Runnable() {
     *        public void run() {
     *            basic_window ex = new basic_window();
     *            ex.setVisible(true);
     *        }
     *    });
     *}
     */
}
