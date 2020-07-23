

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

public class UI {
	Scanner scan = new Scanner(System.in);
	public int num;
	public int num_2;
	public String id;
	public String password;
	public int index;
	
	public void start(MakeQuestion maker,QuestionManager qmanager,GameManager gmanager) {
		this.open(maker, qmanager, gmanager);
		switch(this.num) {
		case 1:
			this.login(maker, qmanager, gmanager);
			
			break;
		case 2:
			System.out.println("********회원가입**********");
			this.make_account();
			System.out.println("");
			System.out.println("");
			
			this.start(maker, qmanager, gmanager);
			break;
			
		case 3:
			System.out.println("고생했어용");
			break;
		}
		
	}
	
	
	public void open(MakeQuestion maker,QuestionManager qmanager,GameManager gmanager) {
		
		System.out.println("덧셈게임에 오신것을 환영합니다!");
		System.out.println("원하는 메뉴의 숫자를 입력해 주세요");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("3. 종료");
		
		num = scan.nextInt();
	}
	
	
	
	public void login(MakeQuestion maker,QuestionManager qmanager,GameManager gmanager) {
		boolean check =false;
		System.out.print("아이디: ");
		this.id = scan.next();
		System.out.print("비밀번호: ");
		do
		{
		this.password = scan.next();
		
		for(int i=0;i<this.password.length();i++)//비밀번호 int로 받았는지 확인용
		{
			if(Character.isDigit(this.password.charAt(i))== true)
				check = false;
			else
				check = true;
		}
		if(check)
			System.out.println("비밀번호는 숫자로 입력하시오!");
		}while(check);
		
		Connection con = null; //connection null 초기화
		Statement stmt =null; //proparedStatement 초기화
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Drive에 등록
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plus_game?serverTimezone=UTC","root","admin1234"); //URL과 아디이 패스워드를 담은 객체 생성
			String sql = "SELECT count(user_name) FROM user where  user_id='";
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql+this.id+"';");
			while(rs.next())
			{
				int find_id = rs.getInt("count(user_name)");
				if(find_id ==0)
				{
					System.out.println("아이디 존재하지 않음! 아이디 다시 입력해 주세요");
					this.login(maker, qmanager, gmanager);
				}
			}
			}catch (SQLException e) {

            System.out.println("SQL Error : " + e.getMessage());

        } catch (ClassNotFoundException e1) {

            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");

        } finally {

            //사용순서와 반대로 close 함
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
	
		con = null; //connection null 초기화
		stmt =null; //proparedStatement 초기화
		rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Drive에 등록
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plus_game?serverTimezone=UTC","root","admin1234"); //URL과 아디이 패스워드를 담은 객체 생성
			String sql = "SELECT * FROM user where  user_id='";
			stmt = con.createStatement();

			rs = stmt.executeQuery(sql+this.id+"';");
			while(rs.next())
			{
				
				index =rs.getInt("user_index");
				String name = rs.getString("user_name");
				String pw = rs.getString("user_password");
				
			if(this.password.equals(pw))
			{
				System.out.println("로그인 성공!");
				System.out.println(name +"님 환영합니다.");
				System.out.println("");
				System.out.println("");
				this.after_login(maker, qmanager, gmanager);
			}
			else
				{
				System.out.println("로그인 실패!!");
				System.out.println("");
				System.out.println("");
				this.start(maker, qmanager, gmanager);
				}
			}
			}catch (SQLException e) {

            System.out.println("SQL Error : " + e.getMessage());

        } catch (ClassNotFoundException e1) {

            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");

        } finally {

            //사용순서와 반대로 close 함
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
		
	}
	
	
	
	public void after_login(MakeQuestion maker,QuestionManager qmanager,GameManager gmanager) {

		System.out.println("원하는 메뉴의 숫자를 입력해 주세요");
		System.out.println("1. 게임시작");
		System.out.println("2. 로그아웃");
		System.out.println("3. 점수조회");
		System.out.println("4. 종료");

		num_2 = scan.nextInt();
		
		switch(num_2)
		{
		case 1:
			this.game(maker, qmanager, gmanager);
			System.out.println("");
			System.out.println("");
			this.after_login(maker, qmanager, gmanager);
			break;
			
		case 2:
			System.out.println("로그아웃 성공!");
			System.out.println("");
			System.out.println("");
			this.start(maker, qmanager, gmanager);
			break;
			
		case 3:

			Connection con = null; //connection null 초기화
			Statement stmt =null; //proparedStatement 초기화
			ResultSet rs = null;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver"); //Drive에 등록
				con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plus_game?serverTimezone=UTC","root","admin1234"); //URL과 아디이 패스워드를 담은 객체 생성
				String sql = "SELECT * FROM score where player_index=";
				stmt = con.createStatement();

				rs = stmt.executeQuery(sql+ this.index +";");
				while(rs.next())
				{ 
					String result = rs.getString("game_result");
					String date = rs.getString("game_date");
					System.out.println("게임 시간: " +date);
					System.out.println("게임 결과: " +result);
					System.out.println("");
				}
				}catch (SQLException e) {

	            System.out.println("SQL Error : " + e.getMessage());

	        } catch (ClassNotFoundException e1) {

	            System.out.println("[JDBC Connector Driver 오류 : " + e1.getMessage() + "]");

	        } finally {

	            //사용순서와 반대로 close 함
	            if (rs != null) {
	                try {
	                    rs.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	            if (stmt != null) {
	                try {
	                    stmt.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }

	            if (con != null) {
	                try {
	                    con.close();
	                } catch (SQLException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
			
			
			this.after_login(maker, qmanager, gmanager);
			break;
			
		case 4:
			System.out.println("고생했어용");
			break;
		}
	}
	
	
	

	public void game(MakeQuestion maker,QuestionManager qmanager,GameManager gmanager)
	{
		SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
		Calendar time = Calendar.getInstance();
		String format_time1 = format1.format(time.getTime());
		boolean check;
		maker.makeQuestion(qmanager);
		qmanager.printAllQuestion();
		gmanager.startGame(qmanager);
		gmanager.printResult();
		
		check = gmanager.checkRetry();
		gmanager.retry(check,qmanager);
		Connection con = null; //connection null 초기화
		PreparedStatement st =null; //proparedStatement 초기화
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Drive에 등록
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plus_game?serverTimezone=UTC","root","admin1234"); //URL과 아디이 패스워드를 담은 객체 생성
			String sql = "insert into score values (?,?,?)"; //insert (데이터를 넣을) 테이블과 내용물 형성
			st = con.prepareStatement(sql);
			st.setInt(1,gmanager.result); //테이블 생성 시 만들었던 메뉴의 순서에 맞게 넣어줍니다.
			st.setTimestamp(2, java.sql.Timestamp.valueOf(format_time1));//문제 푼 시간
			st.setInt(3, this.index);// 푼 사람
			st.executeUpdate();
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("오류가 발생하였습니다.");
			
		}finally {
			if(st!=null)try {st.close();}catch(SQLException sqle) {} //close 순서가 중요합니다. 
			if(con!=null)try {con.close();}catch(SQLException sqle) {}
			
		}
		
	}
	
	
	
	
	public void make_account() {
		boolean check =false;
		String name;
		String id;
		String pw;
		System.out.print("당신의 이름은? ");
		name = scan.next();
		System.out.print("ID: ");
		id = scan.next();
		System.out.print("PassWord: ");

		do
		{
			pw = scan.next();
		
		for(int i=0;i<pw.length();i++)//비밀번호 int로 받았는지 확인용
		{
			if(Character.isDigit(pw.charAt(i))== true)
				check = false;
			else
				check = true;
		}
		if(check)
			System.out.println("비밀번호는 숫자로 입력하시오!");
		}while(check);
		
		
		Connection con = null; //connection null 초기화
		PreparedStatement st =null; //proparedStatement 초기화
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver"); //Drive에 등록
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/plus_game?serverTimezone=UTC","root","admin1234"); //URL과 아디이 패스워드를 담은 객체 생성
			String sql = "insert into user values (?,?,?,?)"; //insert (데이터를 넣을) 테이블과 내용물 형성
			st = con.prepareStatement(sql);
			st.setString(1, null);
			st.setString(2, name); //테이블 생성 시 만들었던 메뉴의 순서에 맞게 넣어줍니다.
			st.setString(3, id);
			st.setString(4, pw);
			st.executeUpdate();
			System.out.println("회원가입되었습니다");
			
			
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("오류가 발생하였습니다.");
			
		}finally {
			if(st!=null)try {st.close();}catch(SQLException sqle) {} //close 순서가 중요합니다. 
			if(con!=null)try {con.close();}catch(SQLException sqle) {}
			
		}
	}
	
	
}
