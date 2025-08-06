import java.util.Scanner;

public class TicTacToeBoard {
    static char[][] board = new char[3][3];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        initializeBoard();
        char currentPlayer = 'X';
        boolean gameOver = false;

        while (!gameOver) {
            printBoard();
            System.out.println("玩家 " + currentPlayer + " 請輸入行和列 (0-2)：");
            int row = sc.nextInt();
            int col = sc.nextInt();

            if (placeMark(row, col, currentPlayer)) {
                if (checkWin(currentPlayer)) {
                    printBoard();
                    System.out.println("玩家 " + currentPlayer + " 獲勝！");
                    gameOver = true;
                } else if (isBoardFull()) {
                    printBoard();
                    System.out.println("平手！");
                    gameOver = true;
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                }
            } else {
                System.out.println("此位置無效，請重新輸入！");
            }
        }
        sc.close();
    }

    // 初始化棋盤
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // 印出棋盤
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // 放置棋子
    public static boolean placeMark(int row, int col, char player) {
        if (row >= 0 && row < 3 && col >= 0 && col < 3) {
            if (board[row][col] == ' ') {
                board[row][col] = player;
                return true;
            }
        }
        return false;
    }

    // 檢查勝利條件
    public static boolean checkWin(char player) {
        // 檢查行
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }
        // 檢查列
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }
        // 檢查對角線
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) return true;
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) return true;

        return false;
    }

    // 判斷棋盤是否已滿
    public static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}