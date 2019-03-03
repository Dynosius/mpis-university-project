package gui;
	
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Tu negdje pri inicijalizaciji treba pozvati klasu Servis kako bi se pokrenuo cijeli program
 */

public class Main extends Application {
	@Override
	public void start(Stage prozor) {
		
		Splash_screen pocetni = new Splash_screen();
		pocetni.postavi(prozor);
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
