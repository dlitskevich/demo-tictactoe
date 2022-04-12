package lib;

public class TicTacToe {
    private int[][] field;
    private int turn;
    private Boolean finished;

    public TicTacToe() {
        field = new int[3][3];
        resetGame();
    }

    public Boolean getFinished() {
        return finished;
    }

    public void resetGame() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                field[row][col] = 0;
            }
        }
        turn = 0;
        finished = false;
    }

    public void move(int cell) {
        if (!finished && field[cell / 3][cell % 3] == 0) {
            field[cell / 3][cell % 3] = getPlayer();
            if (checkWinner() == 0 && turn != 9) {
                turn++;
            }
        }
        print();

    }

    private void finish() {
        finished = true;
    }

    public int getPlayer() {
        return turn % 2 + 1;
    }

    public int getTurn() {
        return turn;
    }

    private int checkWinner() {
        if (finished || turn < 4) {
            return 0;
        }
        int[] diagonal = new int[3];
        int[] antiDiagonal = new int[3];

        for (int row = 0; row < 3; row++) {
            int[] column = new int[3];
            for (int col = 0; col < 3; col++) {
                column[col] = field[col][row];
            }
            CheckRowWin(field[row]);
            CheckRowWin(column);
            if (finished) {
                return getPlayer();
            }
            diagonal[row] = field[row][row];
            antiDiagonal[row] = field[row][2 - row];

        }
        CheckRowWin(diagonal);
        CheckRowWin(antiDiagonal);
        if (finished) {
            return getPlayer();
        }
        return 0;
    }

    private void CheckRowWin(int[] array) {
        int product = 1;
        for (int i = 0; i < array.length; i++) {
            product *= array[i];
        }
        if (product == 1 || product == 8) {
            finish();
        }
    }

    public void print() {
        System.out.println("Turn: " + turn + " Field: ");
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                System.out.printf("%d   ", field[row][column]);
            }
            System.out.println();
        }
    }
}
