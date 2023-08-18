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
		this.resetGame(currentPlayerNum);
	}

	public void resetGame(int currentPlayerNum) {
		this.resetTurn();
		this.setCurrentPlayerNum(currentPlayerNum);
	}
	
	// About Player Order
	public int getCurrentPlayerNum() {
		return currentPlayerNum;
	}

	public void setCurrentPlayerNum(int currentPlayerNum) {
		this.currentPlayerNum = currentPlayerNum;
	}	
	
	// About Turn
	public void changeTurn() {
		currentPlayerNum = (currentPlayerNum == 1) ? 2 : 1;
		this.countTurn();
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
	 * 현제 맵상태를 넣으면, 승패를 판정해주는 함수
	 * @param currentStage
	 * @return WIN_P1(1): 플레이어 1 승리, WIN_P2(2): 플레이어 2 승리, PLAY(0): 진행중, DRAW(99): 비김(draw)
	 */
	public STATE inputCurrentStage(int[][] currentStage) {
		// 승패 판정하기
		// 코드를 작성하시오
		
		// 비기는 경우
		if(this.isFullMap()) { //맵이 꽉찼는데도 승패 판정을 못했다.
			return STATE.DRAW;
		}
		
		return STATE.PLAY;
	}
}
