import java.awt.*; 
import javax.swing.*;



public class Driver {

	/**
	 * @param args
	 */
	
	public void display() {
		JFrame jfrm = new JFrame("Hospital");
		jfrm.setSize(1000,600);
		jfrm.setLayout(new FlowLayout());
		jfrm.setVisible(true);
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Driver obj = new Driver();
				obj.display();
			}
		});

	}

}