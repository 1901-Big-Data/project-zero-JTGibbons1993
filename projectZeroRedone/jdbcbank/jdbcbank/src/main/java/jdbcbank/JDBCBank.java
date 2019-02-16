package jdbcbank;

public class JDBCBank {

	public static void main(String[] args) {
		Database.connect();
		while(true) {
			Menu.showMenu();
			Menu.doTask();
		}
	}

}
