import java.io.InputStream;


public class open {
	public open() {
		getClass().getClassLoader();
//		InputStream input =
//                this.getClass().getClassLoader().getResourceAsStream("usr/local/akashi/lib/db.properties");
		InputStream input = getClass().getClassLoader().getResourceAsStream("folder/test");
		
		if(input == null) {
			System.out.println("null");
		} else {
			System.out.println("asdfasdf");
		}
	}

}
