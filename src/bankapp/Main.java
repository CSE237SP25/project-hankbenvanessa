package bankapp;

public class Main {
	public static void main(String[] args) {
		Menu m = new Menu();
		
		boolean shouldStop = false;
		while(!shouldStop) {
			m.accountDisplayOptions();
			int menuResponse = 0;
			boolean isResponseProper = false;
			while(!isResponseProper) {
				try {
					menuResponse = m.getUserInputInt();
					isResponseProper = true;
				}
				catch (Exception e) {
				}
			}
			m.processUserAccountMenuChoice(menuResponse);
		}
	}
}
