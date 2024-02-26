public class BoardModel {
    private final int Rows = 6;
    private final int Columns = 7;
    private int[][] gameBoard;

    private int nextFreePlaceColumn1, nextFreePlaceColumn2, nextFreePlaceColumn3, nextFreePlaceColumn4, nextFreePlaceColumn5, nextFreePlaceColumn6, nextFreePlaceColumn7;
    private int numOfPlayer1Circles, numOfPlayer2Circles;

    private Presenter UIPresenter;
    public BoardModel()
    {
        UIPresenter = new Presenter(Rows,Columns,this);

        gameBoard = new int[Rows][Columns];

        numOfPlayer1Circles = 0;
        numOfPlayer2Circles = 0;

        // The length of the rows in the array
        for(int i = 0; i < gameBoard.length; i++)
        {  // The length of the columns in the array
            for(int j = 0; j < gameBoard[0].length; j++)
            {
                gameBoard[i][j] = 0;
            }
        }
        nextFreePlaceColumn1 = Rows -1;
        nextFreePlaceColumn2 = Rows -1;
        nextFreePlaceColumn3 = Rows -1;
        nextFreePlaceColumn4 = Rows -1;
        nextFreePlaceColumn5 = Rows -1;
        nextFreePlaceColumn6 = Rows -1;
        nextFreePlaceColumn7 = Rows -1;
    }
    public boolean checkIfColumnFull(int Column)
    {
        switch (Column)
        {
            case 1:
                return nextFreePlaceColumn1 == -1;
            case 2:
                return nextFreePlaceColumn2 == -1;
            case 3:
                return nextFreePlaceColumn3 == -1;
            case 4:
                return nextFreePlaceColumn4 == -1;
            case 5:
                return nextFreePlaceColumn5 == -1;
            case 6:
                return nextFreePlaceColumn6 == -1;
            case 7:
                return nextFreePlaceColumn7 == -1;
        }
        for(int i = 0; i < gameBoard[0].length; i++)
        {
            if(gameBoard[Column][i] != 1 || gameBoard[Column][i] != 2)
                return false;
        }
        return true;
    }
    public void addCircleToLine(int Column, int player)
    {
        if (!checkIfColumnFull(Column))
        {
            switch (Column)
            {
                case 1:
                    gameBoard[Column-1][nextFreePlaceColumn1] = player;
                    nextFreePlaceColumn1--;
                case 2:
                    gameBoard[Column-1][nextFreePlaceColumn2] = player;
                    nextFreePlaceColumn2--;
                case 3:
                    gameBoard[Column-1][nextFreePlaceColumn3] = player;
                    nextFreePlaceColumn3--;
                case 4:
                    gameBoard[Column-1][nextFreePlaceColumn4] = player;
                    nextFreePlaceColumn4--;
                case 5:
                    gameBoard[Column-1][nextFreePlaceColumn5] = player;
                    nextFreePlaceColumn5--;
                case 6:
                    gameBoard[Column-1][nextFreePlaceColumn6] = player;
                    nextFreePlaceColumn6--;
                case 7:
                    gameBoard[Column-1][nextFreePlaceColumn7] = player;
                    nextFreePlaceColumn7--;
            }
            switch (player)
            {
                case 1:
                    numOfPlayer1Circles++;
                case 2:
                    numOfPlayer2Circles++;
            }
        }
    }
    public boolean checkWin(int player, int rowIndex, int columnIndex)
    {
        switch (player)
        {
            case 1:
                numOfPlayer1Circles++;
            case 2:
                numOfPlayer2Circles++;
        }
        return true;
    }
}