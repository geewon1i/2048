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

        // 计算基本分数和空瓦片数量
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grids[i][j] == 0) {
                    emptyTiles++;
                } else {
                    score += grids[i][j] * (Math.log(grids[i][j]) / Math.log(2) - 1);
                }
            }
        }

        // 计算相邻瓦片差异的平滑性
        double smoothness = 0.0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE - 1; j++) {
                if (grids[i][j] != 0 && grids[i][j + 1] != 0) {
                    smoothness -= Math.abs(Math.log(grids[i][j]) / Math.log(2) - Math.log(grids[i][j + 1]) / Math.log(2));
                }
                if (grids[j][i] != 0 && grids[j + 1][i] != 0) {
                    smoothness -= Math.abs(Math.log(grids[j][i]) / Math.log(2) - Math.log(grids[j + 1][i]) / Math.log(2));
                }
            }
        }

        // 计算单调性
        double monotonicity = 0.0;
        for (int i = 0; i < SIZE; i++) {
            double currentRowMonotonicity = 0.0;
            double currentColMonotonicity = 0.0;
            for (int j = 0; j < SIZE - 1; j++) {
                currentRowMonotonicity += Math.log(grids[i][j] + 1) / Math.log(2) - Math.log(grids[i][j + 1] + 1) / Math.log(2);
                currentColMonotonicity += Math.log(grids[j][i] + 1) / Math.log(2) - Math.log(grids[j + 1][i] + 1) / Math.log(2);
            }
            monotonicity += Math.abs(currentRowMonotonicity) + Math.abs(currentColMonotonicity);
        }

        // 计算角落瓦片的奖励
        double cornerBonus = 0.0;
        int[] cornerTiles = {
                grids[0][0],
                grids[0][SIZE - 1],
                grids[SIZE - 1][0],
                grids[SIZE - 1][SIZE - 1]
        };
        for (int tile : cornerTiles) {
            cornerBonus += Math.log(tile + 1) / Math.log(2);
        }

        // 计算聚集度
        double clusteredness = 0.0;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grids[i][j] != 0) {
                    if (i < SIZE - 1 && grids[i + 1][j] != 0) {
                        clusteredness -= Math.abs(Math.log(grids[i][j]) / Math.log(2) - Math.log(grids[i + 1][j]) / Math.log(2));
                    }
                    if (j < SIZE - 1 && grids[i][j + 1] != 0) {
                        clusteredness -= Math.abs(Math.log(grids[i][j]) / Math.log(2) - Math.log(grids[i][j + 1]) / Math.log(2));
                    }
                }
            }
        }

        // 综合考虑这些因素的权重
        score += emptyTiles * 50;            // 空瓦片数量的权重
        score += smoothness * 0.1;           // 平滑性的权重
        score -= monotonicity * 10.0;        // 单调性的权重（负值表示单调性越高得分越高）
        score += cornerBonus * 100;          // 角落瓦片的奖励权重
        score += clusteredness * 0.5;        // 聚集度的权重

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
