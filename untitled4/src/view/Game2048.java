package view;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game2048 {
    private static final int SIZE = 4;
    private int[][] grid;
    private Random random;

    public Game2048() {
        grid = new int[SIZE][SIZE];
        random = new Random();
        reset();
    }

    public void reset() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = 0;
            }
        }
        addRandomTile();
        addRandomTile();
    }

    private void addRandomTile() {
        List<int[]> emptyTiles = new ArrayList<>();
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    emptyTiles.add(new int[]{i, j});
                }
            }
        }
        if (!emptyTiles.isEmpty()) {
            int[] pos = emptyTiles.get(random.nextInt(emptyTiles.size()));
            grid[pos[0]][pos[1]] = random.nextDouble() < 0.9 ? 2 : 4;
        }
    }

    public boolean move(char direction) {
        int[][] originalBoard = copyBoard();
        switch (direction) {
            case 'w': rotateBoard(3); moveLeft(); rotateBoard(1); break;
            case 's': rotateBoard(1); moveLeft(); rotateBoard(3); break;
            case 'a': moveLeft(); break;
            case 'd': rotateBoard(2); moveLeft(); rotateBoard(2); break;
        }
        if (!gridsEqual(originalBoard, grid)) {
            addRandomTile();
            return true;
        }
        return false;
    }

    private int[][] copyBoard() {
        int[][] newBoard = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                newBoard[i][j] = grid[i][j];
            }
        }
        return newBoard;
    }

    private boolean gridsEqual(int[][] grid1, int[][] grid2) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid1[i][j] != grid2[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isGameOver() {
        int[][] originalBoard = copyBoard();
        for (char direction : new char[]{'w', 'a', 's', 'd'}) {
            if (move(direction)) {
                grid = originalBoard;
                return false;
            }
        }
        return true;
    }

    public void printBoard() {
        for (int[] row : grid) {
            for (int tile : row) {
                System.out.print(tile + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private void rotateBoard(int times) {
        for (int t = 0; t < times; t++) {
            int[][] newBoard = new int[SIZE][SIZE];
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    newBoard[j][SIZE - 1 - i] = grid[i][j];
                }
            }
            grid = newBoard;
        }
    }

    private void moveLeft() {
        for (int i = 0; i < SIZE; i++) {
            slideRowLeft(grid[i]);
        }
    }

    private void slideRowLeft(int[] row) {
        int[] newRow = new int[SIZE];
        int index = 0;

        for (int i = 0; i < SIZE; i++) {
            if (row[i] != 0) {
                if (index > 0 && newRow[index - 1] == row[i]) {
                    newRow[index - 1] *= 2;
                } else {
                    newRow[index++] = row[i];
                }
            }
        }
        System.arraycopy(newRow, 0, row, 0, SIZE);
    }

    public double evaluate() {
        int [][]map=new int[SIZE][SIZE];
        for (int i=0;i<SIZE;++i)
            for (int j=0;j<SIZE;++j)
                map[i][j]=Math.max(i+j-6,0)*6;
        double score = 0.0;
        int emptyTiles = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 0) {
                    emptyTiles++;
                } else {
                    score += grid[i][j] * (Math.log(grid[i][j]) / Math.log(2) - 1);
                }
            }
        }
        int diff=0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 1; j < SIZE; j++)
                diff=Math.abs(grid[i][j]-grid[i][j-1]);
        for (int i = 0; i < SIZE; i++)
            for (int j = 1; j < SIZE; j++)
                diff=Math.abs(grid[j][i]-grid[j-1][i]);
        score += emptyTiles *10;
        score-=20*diff;
        int corner=grid[0][0]+grid[SIZE-1][SIZE-1]+grid[SIZE-1][0]+grid[0][SIZE-1];
        score+=2*corner;
        for (int i=0;i<SIZE;++i)
            for (int j=0;j<SIZE;++j)
                score+=Math.pow(2,map[i][j])*grid[i][j];
        return score;
    }
    public double expectimax(int depth, boolean isMax) {
        if (depth == 0 || isGameOver()) {
            return evaluate();
        }

        if (isMax) {
            double maxEval = Double.NEGATIVE_INFINITY;
            int[][] originalBoard = copyBoard();
            for (char direction : new char[]{'w', 'a', 's', 'd'}) {
                if (move(direction)) {
                    maxEval = Math.max(maxEval, expectimax(depth - 1, false));
                    grid = originalBoard;
                }
            }
            return maxEval;
        } else {
            double totalEval = 0.0;
            int count = 0;
            int[][] originalBoard = copyBoard();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (grid[i][j] == 0) {
                        grid[i][j] = 2;
                        totalEval += 0.9 * expectimax(depth - 1, true);
                        grid[i][j] = 4;
                        totalEval += 0.1 * expectimax(depth - 1, true);
                        grid[i][j] = 0;
                        count++;
                    }
                }
            }
            return count > 0 ? totalEval / count : evaluate();
        }
    }

    public char findBestMove() {
        double bestScore = Double.NEGATIVE_INFINITY;
        char bestMove = 'w';
        int[][] originalBoard = copyBoard();
        for (char direction : new char[]{'w', 'a', 's', 'd'}) {
            if (move(direction)) {
                double score = expectimax(3, false);
                if (score > bestScore) {
                    bestScore = score;
                    bestMove = direction;
                }
                grid = originalBoard;
            }
        }
        return bestMove;
    }
    public int maxNum(){
        int ans=0;
        for (int i=0;i<SIZE;++i)
            for (int j=0;j<SIZE;++j)
                ans=Math.max(ans,grid[i][j]);
        return ans;
    }
}
