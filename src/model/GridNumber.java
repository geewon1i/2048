package model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;

public class GridNumber implements Serializable {
    private static final long serialVersionUID = 6113042624164284648L;
    private final int X_COUNT;//line
    private final int Y_COUNT;//column

    private int[][] numbers;
    private int goal;
    static Random random = new Random();

    public GridNumber(int xCount, int yCount) {
        this.X_COUNT = xCount;
        this.Y_COUNT = yCount;
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        this.initialNumbers();
    }
    public void generate(int val){
        int i,j;
        while (true){
            i=random.nextInt(X_COUNT);j=random.nextInt(Y_COUNT);
            if(numbers[i][j]==0){numbers[i][j]=val;return;}
        }
    }
    public void generate(){
        int i,j,val = 0;
        switch (random.nextInt(1,3)){

            case 1:val=2;break;
            case 2:val=4;break;
        }
        while (true){
            i=random.nextInt(X_COUNT);j=random.nextInt(Y_COUNT);
            if(numbers[i][j]==0){numbers[i][j]=val;break;}
        }
    }
    public void initialNumbers() {
        this.numbers = new int[this.X_COUNT][this.Y_COUNT];
        int cnt=5;
        generate(2);
        generate(4);
    }
    //todo: finish the method of four direction moving.
    public int moveRight() {
        int ans=0;
        for (int i=0;i<X_COUNT;++i){
            int cur=Y_COUNT-1;//current index
            for (int j=cur-1;j>=0;--j){
                if(numbers[i][j]==0)continue;
                if(numbers[i][cur]==0){
                    numbers[i][cur]=numbers[i][j];
                    numbers[i][j]=0;
                    continue;
                }
                if(numbers[i][j]==numbers[i][cur]){
                    ans+=numbers[j][i]*2;
                    numbers[i][cur--]*=2;
                    numbers[i][j]=0;
                }
                else {
                    int t=numbers[i][j];
                    numbers[i][j]=0;
                    numbers[i][--cur]=t;
                }
            }
        }
        generate();
        return ans;
    }
    public int moveLeft(){
        int ans=0;
        for (int i=0;i<X_COUNT;++i){
            int cur=0;//current index
            for (int j=cur+1;j<Y_COUNT;++j){
                if(numbers[i][j]==0)continue;
                if(numbers[i][cur]==0){
                    numbers[i][cur]=numbers[i][j];
                    numbers[i][j]=0;
                    continue;
                }
                if(numbers[i][j]==numbers[i][cur]){
                    ans+=numbers[i][j]*2;
                    numbers[i][cur++]*=2;
                    numbers[i][j]=0;
                }
                else {
                    int t=numbers[i][j];
                    numbers[i][j]=0;
                    numbers[i][++cur]=t;
                }
            }
        }
        generate();
        return ans;
    }
    public int moveUp(){
        int ans=0;
        for (int i=0;i<Y_COUNT;++i){
            int cur=0;
            for(int j=1;j<X_COUNT;++j){
                if (numbers[j][i]==0)continue;
                if(numbers[cur][i]==0){
                    numbers[cur][i]=numbers[j][i];
                    numbers[j][i]=0;
                    continue;
                }
                if(numbers[j][i]==numbers[cur][i]){
                    ans+=numbers[j][i]*2;
                    numbers[cur++][i]*=2;
                    numbers[j][i]=0;
                }
                else {
                    int t=numbers[j][i];
                    numbers[j][i]=0;
                    numbers[++cur][i]=t;
                }
            }
        }
        generate();
        return ans;
    }
    public int moveDown(){
        int ans=0;
        for (int i=0;i<Y_COUNT;++i){
            int cur=X_COUNT-1;
            for(int j=cur-1;j>=0;--j){
                if (numbers[j][i]==0)continue;
                if(numbers[cur][i]==0){
                    numbers[cur][i]=numbers[j][i];
                    numbers[j][i]=0;
                    continue;
                }
                if(numbers[j][i]==numbers[cur][i]){
                    ans+=numbers[j][i]*2;
                    numbers[cur--][i]*=2;
                    numbers[j][i]=0;
                }
                else {
                    int t=numbers[j][i];
                    numbers[j][i]=0;
                    numbers[--cur][i]=t;
                }
            }
        }
        generate();
        return ans;
    }
    public int getNumber(int i, int j) {
        return numbers[i][j];
    }

    public void printNumber() {
        for (int[] line : numbers) {
            System.out.println(Arrays.toString(line));
        }
    }
}