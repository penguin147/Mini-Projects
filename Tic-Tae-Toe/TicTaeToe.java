import java.awt.*;

import java.awt.event.*;

import java.util.ArrayList;

import javax.swing.*;

import java.util.Iterator;

import java.util.*;

import java.util.Random;

// X는 -1 O는 1

public class TicTaeToe extends JFrame implements ActionListener{

 

	JPanel window = new JPanel();

	JPanel game = new JPanel();

	JPanel lobby = new JPanel();

	JLabel condition = new JLabel("  첫번째 게임입니다!  ");

	

	int[][] tableInside = new int[3][3];//틱택토 보드를 선언한다.

	Random r = new Random();

	char turn = 'O';

	JButton[][] button = new JButton[3][3];

	int[] empty = new int[8];
	int[][] tableNum = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
	int[][] tmp = new int[3][3];
	
	int checkNum;
 

	void reset_game()

	{

		for(int i = 0; i < 3; i++)

		{

			for(int j = 0; j < 3; j++)

			{

				button[i][j].setText(" ");

				tableInside[i][j] = 0;

				tmp[i][j] = 0;

			}

		}
		checkNum = 0;

	}

 

	public void actionPerformed(ActionEvent e)

	{

		for(int i = 0; i < 3; i++)

		{

			for(int j = 0; j < 3; j++)

			{

				if(e.getSource() == button[i][j] && tableInside[i][j] == 0)

				{
					if(turn == 'O')
					{
					button[i][j].setText("O");

					tableInside[i][j]++;
					drawCheck();

					check();
					turn = 'X';
					}
					else
					{
					button[i][j].setText("X");

					tableInside[i][j]--;

 

					drawCheck();

					check();
					turn = 'O';
					}
 

 

 

				}

			}

		}

	}
 

	void drawCheck()

	{

		int num = 0;

		for(int i = 0; i < 3; i++)

		{

			for(int j = 0; j < 3; j++)

			{

				if(tableInside[i][j] != 0)

				{

					num++;

				}

			}

		}

		if(num == 9)

		{

			condition.setText("  게임의 결과는 무승부입니다.  ");

			GoToLobby();

		}

 

 

	}

 

	void check()

	{

	

		int[] checkTable = new int[8];

		int num = 0;

 

		for(int i = 0; i < 3; i++)

		{

			for(int j = 0; j < 3; j++)

			{

				checkTable[i] += tableInside[i][j];

			}

		}

 

		for(int i = 3; i < 6; i++)

		{

			for(int j = 0; j < 3; j++)

			{

				checkTable[i] += tableInside[j][i-3];

			}

		}

 

		for(int i = 0; i < 3; i++)

		{

			checkTable[6] += tableInside[i][i];

		}

 

		for(int i = 0; i < 3; i++)

		{

			checkTable[7] += tableInside[i][2-i];

		}//checkTable 초기화

 

 

		checkNum = 0;//체크넘버 초기화

		for(int i = 0; i < 8; i++)

		{

			if(checkTable[i] == 3)

			{

				condition.setText("  게임의 결과는 플레이어의 승리입니다!  ");

				GoToLobby();
			}

			else if(checkTable[i] == -3)

			{

				condition.setText("  게임의 결과는 컴퓨터의 승리입니다!  ");

				GoToLobby();

			}

			else if(checkTable[i] == -2)

			{

				if(checkNum <= 10)

				{

					checkNum = 10;//컴퓨터가 착수하면 게임이 끝남

					num = i;
				}
			}

			else if(checkTable[i] == 2)

			{

				if(checkNum <= 5)

				{

					checkNum = 5;//사람이 착수하면 게임이 끝남

					num = i;

				}

			}

			else

			{

				if(checkNum == 0)

				{

					checkNum = 0;//게임종료에 관여하지 못하는 수를 둠
				}
			}
		}
	}

 

 

	void initTable()

	{

		for(int i = 0; i < 3; i++)

		{

			for(int j = 0; j < 3; j++)

			{

				tableInside[i][j] = 0;

			}

		}

	}

 

	public void newGame()

	{

		reset_game();

		window.removeAll();

		window.add(game);

		repaint();

		revalidate();

	}

	

	public void GoToLobby()

	{

		window.removeAll();

		window.add(lobby);

		repaint();

	}

	

	TicTaeToe()

	{

		setSize(300,300);

		setVisible(true);

 

		JButton newGame = new JButton("게임 시작");

		JButton Exit = new JButton("종료");

	

		

		initTable();

		

		window.setLayout(new BorderLayout());

		

		game.setLayout(new GridLayout(3,3));

		for(int i = 0; i < 3; i++)

		{

			for(int j = 0; j < 3; j++)

			{

				button[i][j] = new JButton();

				game.add(button[i][j]);

				button[i][j].addActionListener(this);

			}

		}

		

		lobby.setLayout(new FlowLayout());

		

		lobby.add(newGame);

		lobby.add(Exit);

		lobby.add(condition);

		newGame.addActionListener(e->{

			newGame();

			

		});

		

		Exit.addActionListener(e->{

			System.exit(1);

		});

		

		window.add(lobby);

		

		add(window);

			


	}

	public static void main(String[] args) 

	{

		TicTaeToe a = new TicTaeToe();

	}

 

}