package game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import game.TicTacToeCore.STATE;

public class TicTacToeView extends JFrame {
	private static final long serialVersionUID = 1L;
	
	JLabel title = new JLabel("TicTacToe | ");
	JLabel dispCurrentPlayer = new JLabel("Player 0");
	private int score1 = 0;
	private int score2 = 0;
	JLabel scoreLabel = new JLabel(" | " + score1 + " : " + score2);
	JButton startNewGame = new JButton("새 게임 시작");
	
	JPanel titleBar = new JPanel(); // 
	JPanel nineRoom = new JPanel(); // 게임  플레이 장소
	
	private int START_PLAYER = 1;
	private boolean isGameEnd = false;
	TicTacToeCore ttt = new TicTacToeCore(START_PLAYER);
	
	public TicTacToeView(){
        super("TicTacToe");
        this.setSize(400,300);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        this.setWindow();
        this.setActionOfNineRoom();
        this.setActionOfStartNewGame();
        this.setVisible(true);       

	}
	
	public void setWindow() {
		// 타이틀(상단) 세팅
		titleBar.add(title);
		titleBar.add(dispCurrentPlayer);
		titleBar.add(scoreLabel);
		titleBar.add(startNewGame);
		
		dispCurrentPlayer.setText("Player " + START_PLAYER);
		add(titleBar, BorderLayout.NORTH);
		
		// 게임 플레이 장소 세팅
		nineRoom.setLayout(new GridLayout(3,3));
		for(int i = 0; i < 9; i++) {
			JButton tempButton = new JButton("");
			tempButton.setFont(new Font("Impact", Font.PLAIN, 22));
			nineRoom.add(tempButton);
		}
		
		add(nineRoom, BorderLayout.CENTER);
	}
	
	public void setActionOfStartNewGame() {
		startNewGame.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ttt.resetGame(START_PLAYER);
				isGameEnd = false;
				for(int i = 0; i < nineRoom.getComponents().length; i++) {
					((JButton)nineRoom.getComponent(i)).setText("");
				}
			
			}
		});
		
	}
	public void setActionOfNineRoom() {
		MouseListener ml = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				JButton tempButton = (JButton)e.getComponent();
				if(isGameEnd) {
					return;
				}
				if(tempButton.getText().equals("O") || tempButton.getText().equals("X")) {
					JOptionPane.showMessageDialog(nineRoom, "이미 둔 곳입니다.");
					return;
				}
				else if(ttt.getCurrentPlayerNum() == 1) {
					tempButton.setText("O");
					dispCurrentPlayer.setText("Player " + 2);
				} else {
					tempButton.setText("X");
					dispCurrentPlayer.setText("Player " + 1);
				}
				ttt.changeTurn();
				System.out.print("(" + e.getX() + ", " + e.getY() + ") ");
				
				int[][] ticArr = new int[3][3];
				for(int i = 0; i < ticArr.length; i++) {
					for(int j = 0; j < ticArr[i].length; j++) {
						String pl = ((JButton)nineRoom.getComponent(j + i * 3)).getText();
						if(pl.equals("O"))	ticArr[i][j] = 1;
						else if(pl.equals("X"))	ticArr[i][j] = 2;
						else	ticArr[i][j] = 0;
					}					
				}
				STATE result = ttt.inputCurrentStage(ticArr);
				System.out.println("result: " + result);
				if(result == STATE.WIN_P1 || result == STATE.WIN_P2) {
					JOptionPane.showMessageDialog(nineRoom, "플레이어 " + result + "의 승리입니다.");
					if(result == STATE.WIN_P1) {
						score1++;
					} else {
						score2++;
					}
					scoreLabel.setText(" | " + score1 + " : " + score2);
					isGameEnd = true;
				} else if (result == STATE.DRAW) {
					JOptionPane.showMessageDialog(nineRoom, "비겼습니다.");
					isGameEnd = true;
				}
				
			}
		};
		
		for(Component c : nineRoom.getComponents()) {
			c.addMouseListener(ml);
		}
	}
}
