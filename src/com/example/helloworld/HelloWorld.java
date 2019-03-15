package com.example.helloworld;

class Sudoku
{
    Sudoku(int board[][])
    {
        m_board = board;
    }

    // public
    public boolean Solve()
    {
        // if all cells are taken, solution is found
        if(!EmptyCell())
            return true;

        // go through the board and get empty cells
        int row = 0, col = 0;
        for (int i = 0; i < m_board.length; i++)
        {
            for (int j = 0; j < m_board[i].length; j++)
            {
                if (m_board[i][j] == 0)
                {
                    // break when empty cell is found
                    row = i;
                    col = j;
                    break;
                }
            }
        }

        // if empty cell is found
        for(int n = 1; n <= 9; n++)
        {
            // if cell meets the requirements
            if (PossibleCell(row, col, n))
            {
                // try a new number
                m_board[row][col] = n;

                if(Solve())
                    return true;

                // if above condition returns false
                m_board[row][col] = 0;
            }
        }

        // recursive
        return false;
    }

    // Print board
    public void Print()
    {
        System.out.println("\n___________________");
        for(int i = 0; i < m_board.length; i++)
        {
            for(int j = 0; j < m_board[i].length; j++)
            {
                System.out.print(m_board[i][j] + " ");

                if((j+1)%3 == 0)
                    System.out.print(" ");
            }

            if((i+1)%3 == 0)
                System.out.print("\n___________________");

            System.out.println();
        }
    }

    // private variables
    private int m_board[][] = {};

    // private functions

    //check if cell is in row
    private boolean IsInRow(int row, int number)
    {
        for (int i = 0; i < m_board[row].length; i++)
        {
            if (m_board[row][i] == number)
            {
                return true;
            }
        }
        return false;
    }

    // check if cell is in column
    private boolean IsInColumn(int col, int number)
    {
        for (int i = 0; i < m_board.length; i++)
        {
            if (m_board[i][col] == number) {
                return true;
            }
        }
        return false;
    }

    // check if cell if in a 3x3 box
    private boolean IsInBox(int row, int col, int number)
    {
        // find which 3x3 box the (row,col) is at

        // x and y represent the starting indexes
        // 0-2 3-5 6-8 indexes
        int x = row - row%3;
        int y = col - col%3;

        for (int i = x; i < x + 3; i++)
        {
            for (int j = y; j < y + 3; j++)
            {
                if(m_board[i][j] == number)
                {
                    return true;
                }
            }
        }

        return false;
    }

    // Check if cell meets the requirements
    private boolean PossibleCell(int row, int col, int number)
    {
        if(!IsInRow(row, number) &&
           !IsInColumn(col, number) &&
           !IsInBox(row, col, number))
        {
            return true;
        }

        return false;
    }

    // Check if there are any empty cells
    private boolean EmptyCell()
    {
        for (int i = 0; i < m_board.length; i++)
        {
            for (int j = 0; j < m_board[i].length; j++)
            {
                // if empty cell found
                if(m_board[i][j] == 0)
                    return true;
            }
        }

        return false;
    }
}

public class HelloWorld
{
    public static void main(String[] args)
    {
        int board[][] = {
            { 8, 0, 0, 0, 0, 0, 0, 0, 0 },
            { 0, 0, 3, 6, 0, 0, 0, 0, 0 },
            { 0, 7, 0, 0, 9, 0, 2, 0, 0 },
            { 0, 5, 0, 0, 0, 7, 0, 0, 0 },
            { 0, 0, 0, 0, 4, 5, 7, 0, 0 },
            { 0, 0, 0, 1, 0, 0, 0, 3, 0 },
            { 0, 0, 1, 0, 0, 0, 0, 6, 8 },
            { 0, 0, 8, 5, 0, 0, 0, 1, 0 },
            { 0, 9, 0, 0, 0, 0, 4, 0, 0 }};

        Sudoku s = new Sudoku(board);

        if(s.Solve())
        {
            s.Print();
        }
    }
}