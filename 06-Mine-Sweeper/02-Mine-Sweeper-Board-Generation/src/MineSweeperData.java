public class MineSweeperData {

    public static String blockImageURL = "resources/block.png";
    public static String flagImageURL = "resources/flag.png";
    public static String mineImageURL = "resources/mine.png";
    public static String numberImageURL(int num){
        if(num < 0 || num >= 8)
            throw new IllegalArgumentException("No such a number image!");
        return "resources/" + num + ".png";
    }

    private int N, M;
    private boolean[][] mines;

    public MineSweeperData(int N, int M, int mineNumber){

        if(N <= 0 || M <= 0)
            throw new IllegalArgumentException("Mine sweeper size is invalid!");

        if(mineNumber > N*M)
            throw new IllegalArgumentException("Mine number is larger than the size of mine sweeper board!");

        this.N = N;
        this.M = M;

        mines = new boolean[N][M];
        for(int i = 0 ; i < N ; i ++)
            for(int j = 0 ; j < M ; j ++){
                mines[i][j] = false;
            }

        for(int i = 0 ; i < mineNumber; i ++){
            int x = i/M;
            int y = i%M;
            mines[x][y] = true;
        }
        shuffleBoard();
    }

    public int N(){ return N; }
    public int M(){ return M; }

    public boolean isMine(int x, int y){
        if(!inArea(x, y))
            throw new IllegalArgumentException("Out of index in isMine function!");
        return mines[x][y];
    }

    public boolean inArea(int x, int y){
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    private void shuffleBoard(){

        for(int i = N*M-1; i >= 0 ; i-- ){
            int randNumber = (int)(Math.random()*(i+1));

            int randX = randNumber/M;
            int randY = randNumber%M;

            int iX = i/M;
            int iY = i%M;

            boolean t = mines[iX][iY];
            mines[iX][iY] = mines[randX][randY];
            mines[randX][randY] = t;
        }
    }
}
