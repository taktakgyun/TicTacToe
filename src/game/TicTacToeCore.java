package game;

public class TicTacToeCore {
	enum STATE{
		DRAW(99), WIN_P1(1),WIN_P2(2), PLAY(0);
		
		private final int value;
	    STATE(int value) { this.value = value; }
	    public int getValue() { return value; }
	}

	private int currentPlayerNum;
	private int currentTurn = 1;
		
	public TicTacToeCore(int currentPlayerNum) {
		this.resetTurn();
		this.setCurrentPlayerNum(currentPlayerNum);
	}

	public void changeTurn() {
		currentPlayerNum = (currentPlayerNum == 1) ? 2 : 1;
		this.countTurn();
	}

	public int getCurrentPlayerNum() {
		return currentPlayerNum;
	}

	public void setCurrentPlayerNum(int currentPlayerNum) {
		this.currentPlayerNum = currentPlayerNum;
	}	
	
	public void countTurn() {
		this.currentTurn++;
	}
	public void resetTurn() {
		this.currentTurn=0;
	}
	public boolean isFullMap() {
		return (this.currentTurn==9);
	}

	/**
	 * 
	 * @param currentStage
	 * @return GAME_OVER(-99): 게임종료됨, WIN_P1(1): 플레이어 1 승리, WIN_P2(2): 플레이어 2 승리, PLAY(0): 진행중, DRAW(99): 비김(draw)
	 */
	public STATE inputCurrentStage(int[][] currentStage) {
		
		for(int i = 0; i < currentStage.length; i++) {
			String rowStr = "";
			String colStr = "";
			String diagStr1 = "";
			String diagStr2 = "";
			for(int j = 0; j < currentStage[i].length; j++) {
				rowStr += (currentStage[i][j] + "");
				colStr += (currentStage[j][i] + "");
			}
			for(int j = 0; j < currentStage.length; j++) {
				diagStr1 += currentStage[j][j];
				diagStr2 += currentStage[j][2 - j];
			}			
			
			// 가로 판단			
			
			if(isThisPlayerWin(2, rowStr, colStr, diagStr1, diagStr2)) {
				return STATE.WIN_P2;
			} else if(isThisPlayerWin(1, rowStr, colStr, diagStr1, diagStr2)) {
				return STATE.WIN_P1;
			} else if(this.isFullMap()) {
				return STATE.DRAW;
			} else {
				continue;
			}
		}
		return STATE.PLAY;
	}
	
	private static boolean isThisPlayerWin(int playerNum, String rowFrag, String colFrag, String diagFrag1, String diagFrag2) {
		String p = String.valueOf(playerNum);
		boolean result = false;
		String[] arr = {rowFrag, colFrag, diagFrag1, diagFrag2};
		for(int i = 0; i < arr.length; i++) {
			result = !arr[i].contains("0") && arr[i].equals(p + p + p);
			if (result)	return result;
		}
		return result;
	}
	
	public void resetGame(int currentPlayerNum) {
		this.currentPlayerNum = currentPlayerNum;
		this.resetTurn();
	}
}
