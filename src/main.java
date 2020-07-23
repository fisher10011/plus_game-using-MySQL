import java.sql.*;
import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scan = new Scanner(System.in);
		MakeQuestion maker = new MakeQuestion();
		QuestionManager qmanager = new QuestionManager();
		GameManager gmanager = new GameManager();
		UI ui =new UI();
		
		ui.start(maker, qmanager, gmanager);
	}

}
