

import java.util.Scanner;

public class GameManager{

	private int[] user_answer = new int[10];
	private int[] wrong = new int[10];
	public int result = 0;
	int wrong_num;
	
	public void startGame(QuestionManager manager) {
		wrong_num=0;
		boolean check = false;
		String a;
		System.out.println("");
		System.out.println("이제 시작해볼까요!");
		System.out.println("");
		Scanner scan = new Scanner(System.in);
		
		for(int i=0;i<10;i++)
		{
			System.out.print("문제 "+ (i+1)+"번: " + manager.num1[i] + "+" + manager.num2[i]+ "=");

			do
			{
			a = scan.next();
			
			for(int y=0;y<a.length();y++)//비밀번호 int로 받았는지 확인용
			{
				if(Character.isDigit(a.charAt(y))== true)
					check = false;
				else
					check = true;
			}
			if(check)
				System.out.println("답은 숫자로 입력하시오!");
			}while(check);
			
			
			user_answer[i] = Integer.parseInt(a);
		}
		for(int i=0;i<10;i++)
		{
			if(manager.answer[i] == user_answer[i])
				result++;
			else
				wrong[wrong_num++]=i;
		}
	}
	
	public void retry(boolean check,QuestionManager manager)
	{
		if(check==true)
		{
			System.out.println("");
		System.out.println("오답 문제들 한번 더  풀어보실래요? [Yes/No]");
		Scanner scan = new Scanner(System.in);
		char retry = scan.next().charAt(0);
		if(retry=='y')
		{
			for(int i=0;i<wrong_num;i++)
			{
				System.out.print("문제 " +(wrong[i]+1)+ "번: "+ manager.num1[wrong[i]]+ "+"+ manager.num2[wrong[i]]+ "= ");
				user_answer[wrong[i]] = scan.nextInt();
				if(user_answer[wrong[i]]==manager.answer[wrong[i]])
					result++;
			}
			System.out.println("최종 결과는 "+ result + "개 맞추셨습니다");
		}
		else
			System.out.println("수고했어용");
		}
		else
			System.out.println("만점이에요!! 똑똑하군요");
	}
	
	public void printResult() {
			System.out.println("맞춘갯수는 "+ result + "개 입니다");
	}
	
	public boolean checkRetry() {
		if(result ==10)
			return false;
		else
			return true;
		
	}

}
