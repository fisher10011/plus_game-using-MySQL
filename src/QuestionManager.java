

public class QuestionManager {

	public int[] answer =new int[10];
	public int[] num1 =new int[10];
	public int[] num2 =new int[10];
	

	public void printAllQuestion() {
		for(int i=0;i<10;i++)
		{
			System.out.println("문제 "+ (i+1)+"번: " + num1[i] + "+" + num2[i]);
		}
		
	}

	
}
