package view;

import model.GridNumber;

public class AI {
    GridNumber gridNumber;
    int SIZE;
    private int[][] grids;
    public AI(GridNumber gridNumber){
        this.gridNumber=gridNumber;
        SIZE=gridNumber.getX_COUNT();
        grids =new int[SIZE][SIZE];
        for (int i=0;i<SIZE;++i)
            for (int j=0;j<SIZE;++j)
                grids[i][j]=gridNumber.getNumber(i,j);
    }
   public double evaluate() {
        int [][]map=new int[SIZE][SIZE];
        double score = 0.0;
        int emptyTiles = 0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grids[i][j] == 0) {
                    emptyTiles++;
                } else {
                    score += grids[i][j] * (Math.log(grids[i][j]) / Math.log(2) - 1);
                }
            }
        }
        int diff=0;
        for (int i = 0; i < SIZE; i++)
            for (int j = 1; j < SIZE; j++)
                diff+=Math.abs(grids[i][j]-grids[i][j-1]);
        for (int i = 0; i < SIZE; i++)
            for (int j = 1; j < SIZE; j++)
                diff+=Math.abs(grids[j][i]-grids[j-1][i]);
        for (int i = 0; i < SIZE; i++)
            for (int j = 1; j < SIZE; j++)
                if(Math.min(grids[j][i],grids[j-1][i])!=0)diff+=20*Math.max(grids[j][i],grids[j-1][i])/Math.min(grids[j][i],grids[j-1][i]);
        for (int i = 0; i < SIZE; i++)
            for (int j = 1; j < SIZE; j++)
                if(Math.min(grids[i][j],grids[i][j-1])!=0)diff+=20*Math.max(grids[i][j],grids[i][j-1])/Math.min(grids[i][j-1],grids[i][j-1]);
        score += emptyTiles *50;
        score-=30*diff;
        int corner=grids[0][0]+grids[SIZE-1][SIZE-1]+grids[SIZE-1][0]+grids[0][SIZE-1];
        score+=100*corner;
        for (int i=0;i<SIZE;++i)
            for (int j=0;j<SIZE;++j)
                score+=Math.pow(2,map[i][j])*grids[i][j];
        return score;
    }
    public int [][]copyBoard(){
        int [][]t=new int[SIZE][SIZE];
        for(int i=0;i<SIZE;++i)
            for (int j=0;j<SIZE;++j)
                t[i][j]=grids[i][j];
        return t;
    }
    boolean isGameOver(){
        int[][] t=copyBoard();
        moveDown();
        if(gridEquals(grids, t))return true;
        moveRight();
        if(gridEquals(grids, t))return true;
        moveLeft();
        if(gridEquals(grids, t))return true;
        moveUp();
        if(gridEquals(grids, t))return true;
        return false;
    }

    public boolean gridEquals(int [][]a,int [][] b) {
        for (int i=0;i<SIZE;++i)
            for (int j=0;j<SIZE;++j)
                if(a[i][j]!=b[i][j])return false;
        return true;
    }

    public double expectimax(int depth, boolean isMax) {

        if (depth == 0 || isGameOver()) {
            return evaluate();
        }

        if (isMax) {
            double maxEval = Double.NEGATIVE_INFINITY;
            int[][] originalBoard = copyBoard();
            moveLeft();
            maxEval = Math.max(maxEval, expectimax(depth - 1, false));
            grids = originalBoard;
            moveRight();
            maxEval = Math.max(maxEval, expectimax(depth - 1, false));
            grids = originalBoard;
            moveUp();
            maxEval = Math.max(maxEval, expectimax(depth - 1, false));
            grids = originalBoard;
            moveDown();
            maxEval = Math.max(maxEval, expectimax(depth - 1, false));
            grids = originalBoard;
            return maxEval;
        } else {
            double totalEval = 0.0;
            int count = 0;
            int[][] originalBoard = copyBoard();
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (grids[i][j] == 0) {
                        grids[i][j] = 2;
                        totalEval += 0.5 * expectimax(depth - 1, true);
                        grids[i][j] = 4;
                        totalEval += 0.5 * expectimax(depth - 1, true);
                        grids[i][j] = 0;
                        count++;
                    }
                }
            }
            return count > 0 ? totalEval / count : evaluate();
        }
    }
    public int moveRight() {
        int ans=0;
        for (int i=0;i<SIZE;++i){
            int cur=SIZE-1;//current index
            for (int j=cur-1;j>=0;--j){
                if(grids[i][j]==0)continue;
                if(grids[i][cur]==0){
                    grids[i][cur]=grids[i][j];
                    grids[i][j]=0;
                    continue;
                }
                if(grids[i][j]==grids[i][cur]){
                    ans+=grids[j][i]*2;
                    grids[i][cur--]*=2;
                    grids[i][j]=0;
                }
                else {
                    int t=grids[i][j];
                    grids[i][j]=0;
                    grids[i][--cur]=t;
                }
            }
        }
        return ans;
    }
    public int moveLeft(){
        int ans=0;
        for (int i=0;i<SIZE;++i){
            int cur=0;//current index
            for (int j=cur+1;j<SIZE;++j){
                if(grids[i][j]==0)continue;
                if(grids[i][cur]==0){
                    grids[i][cur]=grids[i][j];
                    grids[i][j]=0;
                    continue;
                }
                if(grids[i][j]==grids[i][cur]){
                    ans+=grids[i][j]*2;
                    grids[i][cur++]*=2;
                    grids[i][j]=0;
                }
                else {
                    int t=grids[i][j];
                    grids[i][j]=0;
                    grids[i][++cur]=t;
                }
            }
        }
        return ans;
    }
    public int moveUp(){
        int ans=0;
        for (int i=0;i<SIZE;++i){
            int cur=0;
            for(int j=1;j<SIZE;++j){
                if (grids[j][i]==0)continue;
                if(grids[cur][i]==0){
                    grids[cur][i]=grids[j][i];
                    grids[j][i]=0;
                    continue;
                }
                if(grids[j][i]==grids[cur][i]){
                    ans+=grids[j][i]*2;
                    grids[cur++][i]*=2;
                    grids[j][i]=0;
                }
                else {
                    int t=grids[j][i];
                    grids[j][i]=0;
                    grids[++cur][i]=t;
                }
            }
        }
        return ans;
    }
    public int moveDown(){
        int ans=0;
        for (int i=0;i<SIZE;++i){
            int cur=SIZE-1;
            for(int j=cur-1;j>=0;--j){
                if (grids[j][i]==0)continue;
                if(grids[cur][i]==0){
                    grids[cur][i]=grids[j][i];
                    grids[j][i]=0;
                    continue;
                }
                if(grids[j][i]==grids[cur][i]){
                    ans+=grids[j][i]*2;
                    grids[cur--][i]*=2;
                    grids[j][i]=0;
                }
                else {
                    int t=grids[j][i];
                    grids[j][i]=0;
                    grids[--cur][i]=t;
                }
            }
        }
        return ans;
    }
    public char findBestMove() {
        double bestScore = Double.NEGATIVE_INFINITY;
        char bestMove = 'w';
        int[][] originalBoard = copyBoard();
        moveUp();
        double score = expectimax(8, false);
        if (score > bestScore&& !isGameOver()) {
            bestScore = score;
        }
        grids = originalBoard;
        moveDown();
        score = expectimax(8, false);
        if (score > bestScore&& !isGameOver()) {
            bestScore = score;
            bestMove = 's';
        }
        grids = originalBoard;
        moveRight();
        score = expectimax(8, false);
        if (score > bestScore && !isGameOver()) {
            bestScore = score;
            bestMove = 'd';
        }
        grids = originalBoard;
        moveLeft();
        score = expectimax(8, false);
        if (score > bestScore && !isGameOver()) {
            bestScore = score;
            bestMove = 'a';
        }
        return bestMove;
    }
}
