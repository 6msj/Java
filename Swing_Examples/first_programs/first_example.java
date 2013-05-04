import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class first_example extends JFrame {

    public first_example() {
       setTitle("Simple example");
       setSize(300, 200);
       setLocationRelativeTo(null);
       setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                first_example ex = new first_example();
                ex.setVisible(true);
            }
        });
    }
}
