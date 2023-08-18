package game;

public class TicTacToeCore {
private int currentPlayerNum;
	private boolean isGameOver = false;
	private int[][] endStage;
	private int currentTurn = 1;
	
	public TicTacToeCore(int currentPlayerNum) {
		this.currentPlayerNum = currentPlayerNum;
	}

	public void changeTurn() {
		currentPlayerNum = (currentPlayerNum == 1) ? 2 : 1;
	}

	public int getCurrentPlayerNum() {
		return currentPlayerNum;
	}

	public void setCurrentPlayerNum(int currentPlayerNum) {
		this.currentPlayerNum = currentPlayerNum;
	}	

	public int[][] getEndStage() {
		return endStage;
	}

	/**
	 * 
	 * @param currentStage
	 * @return -99: 게임종료됨, 1: 플레이어 1 승리, 2: 플레이어 2 승리, 0: 진행중, 99: 비김(draw)
	 */
	public int inputCurrentStage(int[][] currentStage) {
		// 게임이 끝났다면 더 이상 진행하는 의미가 없으므로 판단 중단
		if(isGameOver) {
			return -99;
		}		
		
		
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
				isGameOver = true;
				endStage = currentStage;
				return 2;
			} else if(isThisPlayerWin(1, rowStr, colStr, diagStr1, diagStr2)) {
				isGameOver = true;
				endStage = currentStage;
				return 1;
			} else if(currentTurn == 9) {
				return 99;
			} else {
				continue;
			}
		}
		currentTurn++;
		return 0;
	}
	
	private boolean isThisPlayerWin(int playerNum, String rowFrag, String colFrag, String diagFrag1, String diagFrag2) {
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
		this.isGameOver = false;
		this.currentPlayerNum = currentPlayerNum;
		this.endStage = null;
		this.currentTurn = 1;
	}
}
