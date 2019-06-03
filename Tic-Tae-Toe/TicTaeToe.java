import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Iterator;
import java.util.*;
import java.util.Random;
// X�� -1 O�� 1
public class TicTaeToe extends JFrame implements ActionListener{

	JPanel window = new JPanel();
	JPanel game = new JPanel();
	JPanel lobby = new JPanel();
	JLabel condition = new JLabel("  ù��° �����Դϴ�!  ");
	
	ImageIcon O = new ImageIcon("e:\\o.png");
	ImageIcon X = new ImageIcon("e:\\x.png");
	
	
	int[][] tableInside = new int[3][3];//ƽ���� ���带 �����Ѵ�.
	Random r = new Random();
	char turn = 'O';
	JButton[][] button = new JButton[3][3];
	int[] empty = new int[8];
	int[][] tableNum = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
	int[][] priority = {{2,1,2},{1,3,1},{2,1,2}};
	int[][] tmp = new int[3][3];

	int priorityNum;
	int checkNum;
	int Com,ComX,ComY;

	void reset_game()
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				button[i][j].setIcon(null);
				tableInside[i][j] = 0;
				tmp[i][j] = 0;
			}
		}
		
		priorityNum = 0;
		checkNum = 0;
		Com = 0;
		ComX = 0;
		ComY = 0;
	}

	public void actionPerformed(ActionEvent e)
	{
		for(int i = 0; i < 3; i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(e.getSource() == button[i][j] && tableInside[i][j] == 0)
				{


					button[i][j].setIcon(O);
					tableInside[i][j]++;

					drawCheck();
					check();



					button[ComY][ComX].setIcon(X);
					tableInside[ComY][ComX]--;

					drawCheck();
					check();



				}
			}
		}
	}

	void whereEmpty()
	{
		int k = 0;
		for(int i = 0; i < 3;i++)
		{
			for(int j = 0; j < 3; j++)
			{
				if(tableInside[i][j] == 0)
					empty[k]++;
				k++;
			}
		}
	}

	void computer()
	{
		switch(Com)
		{
		case 0:
			ComX = 0;
			ComY = 0;
			break;
		case 1:
			ComX = 1;
			ComY = 0;
			break;
		case 2:
			ComX = 2;
			ComY = 0;
			break;
		case 3:
			ComX = 0;
			ComY = 1;
			break;
		case 4:
			ComX = 1;
			ComY = 1;
			break;
		case 5:
			ComX = 2;
			ComY = 1;
			break;
		case 6:
			ComX = 0;
			ComY = 2;
			break;
		case 7:
			ComX = 1;
			ComY = 2;
			break;
		case 8:
			ComX = 2;
			ComY = 2;
			break;
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
			condition.setText("  ������ ����� ���º��Դϴ�.  ");
			GoToLobby();
		}


	}

	void check()
	{
		//int[][] checkTable = new int[8][3];
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
		}//checkTable �ʱ�ȭ


		checkNum = 0;//üũ�ѹ� �ʱ�ȭ
		for(int i = 0; i < 8; i++)
		{
			if(checkTable[i] == 3)
			{
				condition.setText("  ������ ����� �÷��̾��� �¸��Դϴ�!  ");
				GoToLobby();
				
				//System.exit(1);

			}
			else if(checkTable[i] == -3)
			{
				condition.setText("  ������ ����� ��ǻ���� �¸��Դϴ�!  ");
				GoToLobby();
			}
			else if(checkTable[i] == -2)
			{
				if(checkNum <= 10)
				{
					checkNum = 10;//��ǻ�Ͱ� �����ϸ� ������ ����
					num = i;
				}

			}
			else if(checkTable[i] == 2)
			{
				if(checkNum <= 5)
				{
					checkNum = 5;//����� �����ϸ� ������ ����
					num = i;
				}
			}
			else
			{
				if(checkNum == 0)
				{
					checkNum = 0;//�������ῡ �������� ���ϴ� ���� ��




				}
			}

		}

		priorityNum = 0;

		if(checkNum == 10 || checkNum == 5)
		{
			switch(num)
			{
			case 0:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[0][i] == 0)
					{
						Com = i;
					}
				}
				break;
			case 1:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[1][i] == 0)
					{
						Com = i+3;
					}
				}
				break;
			case 2:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[2][i] == 0)
					{
						Com = i+6;
					}
				}
				break;
			case 3:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[i][0] == 0)
					{
						Com = i*3;
					}
				}
				break;
			case 4:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[i][1] == 0)
					{
						Com = i*3 + 1;
					}
				}
				break;
			case 5:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[i][2] == 0)
					{
						Com = i*3 + 2;
					}
				}
				break;
			case 6:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[i][i] == 0)
					{
						Com = i*4;
					}
				}
				break;
			case 7:
				for(int i = 0; i < 3; i++)
				{
					if(tableInside[i][2-i] == 0)
					{
						Com = i*2+2;
					}
				}
				break;
			}
		}
		else
		{
			for(int i = 0; i < 3; i++)
			{
				for(int j = 0; j < 3; j++)
				{
					if(tableInside[i][j] == 0)
					{
						tmp[i][j] = 1;
						if(priorityNum < priority[i][j])
							priorityNum = priority[i][j];
					}

				}
			}


			if(priorityNum == 1)
			{
				while(true)
				{
					Com = 2*r.nextInt(4) +1;
					if(Com == 1)
					{
						if(tableInside[0][1] == 0)
							break;
					}
					if(Com == 3)
					{
						if(tableInside[1][0] == 0)
							break;
					}
					if(Com == 5)
					{
						if(tableInside[1][2] == 0)
							break;
					}
					if(Com == 7)
					{
						if(tableInside[2][1] == 0)
							break;
					}
				}	
			}
			else if(priorityNum == 2)
			{
				while(true)
				{
					Com = 2*r.nextInt(5);
					if(Com == 0)
					{
						if(tableInside[0][0] == 0)
							break;
					}
					if(Com == 2)
					{
						if(tableInside[2][0] == 0)
							break;
					}
					if(Com == 6)
					{
						if(tableInside[2][0] == 0)
							break;
					}
					if(Com == 8)
					{
						if(tableInside[2][2] == 0)
							break;
					}
				}	
			}
			else
			{
				Com = 4;
			}

		}



		computer();


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

		JButton newGame = new JButton("���� ����");
		JButton Exit = new JButton("����");
	
		
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
