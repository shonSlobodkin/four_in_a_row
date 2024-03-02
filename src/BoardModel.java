public class BoardModel {
    private final int Rows = 6;
    private final int Columns = 7;
    private final int[][] gameBoard;

    private int nextFreePlaceColumn1, nextFreePlaceColumn2, nextFreePlaceColumn3, nextFreePlaceColumn4, nextFreePlaceColumn5, nextFreePlaceColumn6, nextFreePlaceColumn7;
    private int numOfPlayer1Circles, numOfPlayer2Circles;

    /** BoardMode Class constructor.*/
    public BoardModel()
    {
        gameBoard = new int[Rows][Columns];
        this.initGame();
    }

    /** Initialize the game. Init the logical gameBoard.*/
    public void initGame()
    {
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

    /** Check if input column in full.
     * @param Column Input Column to be checked.
     * @return true - if Input Column is full, false - otherwise.*/
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

    /** Add a circle to logical gameBoard.
     * @param currentPlayer Current Player Playing.
     * @param Column Column where to add a circle.*/
    public int addCircleToColumn(int Column, int currentPlayer)
    {
        int rowInputed=0;
            switch (Column)
            {
                case 1:
                    rowInputed = nextFreePlaceColumn1;
                    gameBoard[nextFreePlaceColumn1][Column-1] = currentPlayer;
                    nextFreePlaceColumn1--;
                    break;
                case 2:
                    rowInputed = nextFreePlaceColumn2;
                    gameBoard[nextFreePlaceColumn2] [Column-1]= currentPlayer;
                    nextFreePlaceColumn2--;
                    break;
                case 3:
                    rowInputed = nextFreePlaceColumn3;
                    gameBoard[nextFreePlaceColumn3] [Column-1]= currentPlayer;
                    nextFreePlaceColumn3--;
                    break;
                case 4:
                    rowInputed = nextFreePlaceColumn4;
                    gameBoard[nextFreePlaceColumn4] [Column-1]= currentPlayer;
                    nextFreePlaceColumn4--;
                    break;
                case 5:
                    rowInputed = nextFreePlaceColumn5;
                    gameBoard[nextFreePlaceColumn5] [Column-1]= currentPlayer;
                    nextFreePlaceColumn5--;
                    break;
                case 6:
                    rowInputed = nextFreePlaceColumn6;
                    gameBoard[nextFreePlaceColumn6] [Column-1]= currentPlayer;
                    nextFreePlaceColumn6--;
                    break;
                case 7:
                    rowInputed = nextFreePlaceColumn7;
                    gameBoard[nextFreePlaceColumn7] [Column-1]= currentPlayer;
                    nextFreePlaceColumn7--;
                    break;
            }
            switch (currentPlayer)
            {
                case 1:
                    numOfPlayer1Circles++;
                    break;
                case 2:
                    numOfPlayer2Circles++;
            }
            if(numOfPlayer1Circles>=4 || numOfPlayer2Circles >=4)
            {
                return this.checkWin(currentPlayer,rowInputed,Column-1);
            }
            return 0;
    }

    /** Check for a possible Win.
     * @param currentPlayer Current Player Playing.
     * @param inputRow Row index of current player's move.
     * @param inputCol Column index of current player's move.
     * @return currentPlayer - if player won, 0 otherwise.*/
    private int checkWin(int currentPlayer, int inputRow, int inputCol)
    {
        int count = 0;

        // Check 4 in inputted row starting from 0 column
        for(int j = 0; j < this.Columns; j++)
        {
            if(this.gameBoard[inputRow][j]==currentPlayer)
            {
                count++;
            }
            else
            {
                count=0;
            }
            if(count==4)
                return currentPlayer;
        }

        // Check 4 in inputted column starting from inputted row downwards
        for(int j = inputRow; j < this.Rows; j++)
        {
            if(this.gameBoard[j][inputCol]==currentPlayer)
            {
                count++;
            }
            else
            {
                count=0;
            }
            if(count==4)
                return currentPlayer;
        }

        // Check 4 in diagonal from up left to down right
        // Calculate starting point of diagonal
        int i,j;
        if(inputRow>inputCol)
        {
            i = Math.abs(inputRow-inputCol);
            j = 0;
        }
        else {
            i = 0;
            j = Math.abs(inputRow-inputCol);
        }

        // Check 4 in diagonal from up left to down right
        while(i<this.Rows && j<this.Columns)
        {
            if(this.gameBoard[i][j]==currentPlayer)
            {
                count++;
            }
            else
            {
                count=0;
            }
            if(count==4)
                return currentPlayer;
            i++;
            j++;
        }

        // Check 4 in diagonal from up right to down left
        // Calculate starting point of diagonal
        j = (inputCol+inputRow);
        i = 0;
        if(j>this.Columns-1)
        {
            i += (j-(this.Columns-1));
            j -= i;
        }

        // Check 4 in diagonal from up right to down left
        while(i<this.Rows && j>=0)
        {
            if(this.gameBoard[i][j]==currentPlayer)
            {
                count++;
            }
            else
            {
                count=0;
            }
            if(count==4)
                return currentPlayer;
            i++;
            j--;
        }

        return 0;
    }

    /** Check for a valid input Column.
     * @return true - if input Column index is valid. false - otherwise.*/
    public boolean checkValidColumn(int column)
    {
        return column>0 && column<=this.Columns;
    }

    /** Get how many rows in gameBoard.*/
    public int getRows() {
        return Rows;
    }

    /** Get how many Columns in gameBoard.*/
    public int getColumns() {
        return Columns;
    }

    /** Get next free row index in input column index chosen by current player.
     * @param column Column index where next free row is checked.
     * @return Next free row index in input Column index.*/
    public int getNextFreeSpace(int column)
    {
        int space = -1;
        switch (column)
        {
            case 1:
                space =  this.nextFreePlaceColumn1;
                break;
            case 2:
                space =  this.nextFreePlaceColumn2;
                break;
            case 3:
                space =  this.nextFreePlaceColumn3;
                break;
            case 4:
                space =  this.nextFreePlaceColumn4;
                break;
            case 5:
                space =  this.nextFreePlaceColumn5;
                break;
            case 6:
                space =  this.nextFreePlaceColumn6;
                break;
            case 7:
                space =  this.nextFreePlaceColumn7;
        }
        return space;
    }
}