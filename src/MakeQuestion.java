

public class MakeQuestion {
	
	public void makeQuestion(QuestionManager manager) {
		
		for(int i=0;i<10;i++)
		{
			manager.num1[i] = (int)(Math.random()*(99 - 10 +1))+10;
			manager.num2[i] = (int)(Math.random()*(99 - 10 +1))+10;
		}
		
		for(int i=0; i<10;i++)
		{
			manager.answer[i] = manager.num1[i] + manager.num2[i];
		}
	}

}
