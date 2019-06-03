import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.Iterator;
import java.util.*;
import java.util.Random;

// X는 -1 O는 1

public class TicTaeToe extends JFrame implements ActionListener {

	JPanel game = new JPanel();

	int[][] tableInside = new int[3][3];// 틱택토 보드를 선언한다.

	char turn = 'O';

	JButton[][] button = new JButton[3][3];

	TicTaeToe() {
		setSize(300, 300);
		setVisible(true);

		game.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 3; i++)

		{

			for (int j = 0; j < 3; j++)

			{

				button[i][j] = new JButton();

				game.add(button[i][j]);

				button[i][j].addActionListener(this);

			}

		}

		add(game);

	}

	public void actionPerformed(ActionEvent e)

	{

		for (int i = 0; i < 3; i++)

		{

			for (int j = 0; j < 3; j++)

			{

				if (e.getSource() == button[i][j] && tableInside[i][j] == 0)

				{

					if (turn == 'O') {

						button[i][j].setText("O");

						tableInside[i][j]++;
						turn = 'X';

					}

					else {
						button[i][j].setText("X");

						tableInside[i][j]--;

						turn = 'O';

					}
				}

			}

		}

	}

	public static void main(String[] args) {
		TicTaeToe game = new TicTaeToe();
	}

}
