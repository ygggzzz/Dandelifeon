import java.util.Scanner;

public class ChessBoardWorldSystem {
    public int row=25;
    public int col=25;
    private CellBlock[][] Cell;
    Dandelifeon Dandelifeon;

    public ChessBoardWorldSystem() {
        Dandelifeon = new Dandelifeon();
        Cell = new CellBlock[row][col];
        row=25;
        col=25;
    }


    public int getRow()
    {
        return this.row;
    }

    public int getCol()
    {
        return this.col;
    }

    public CellBlock getCell(int row, int col) {
        return Cell[row][col];
    }

    public void replaceCellBlock(int row, int col) {
        Cell[row][col]=new CellBlock();
    }

    public void setCellBlock(int age, boolean alive,int row,int col) {
        Cell[row][col] = new CellBlock(age, alive);
    }

    public int getDandelifeonMana()
    {
        return Dandelifeon.getCountMana();
    }

    public boolean stepOneSecond() //每秒一循环
    {
        if(Dandelifeon.lifeGameCheck())  { return false; }
        Cell=Dandelifeon.makeOnceInteration(Cell);
        return true;
    }

    public void initMap() //初始化，随机设置存活细胞
    {
        for (int i = 0; i < getRow(); i++)
        {
            for (int j = 0; j < getCol(); j++)
            {
                Cell[i][j] = new CellBlock(0, false);
            }
        }
        for (int i = 0; i < getRow(); i++)
        {
            for (int j = 0; j < getCol(); j++)
            {
                if(Math.random()<0.3)
                {
                    if ((i >= Dandelifeon.getRow() - 1 && i <= Dandelifeon.getRow() + 1) || (j >= Dandelifeon.getCol() - 1 && j <= Dandelifeon.getCol()))
                    {
                        continue;
                    }
                    setCellBlock(0,true,i,j);
                }
            }
        }
    }

    public void outputMap()
    {
        for (int i = -1; i != row + 1; i++)
        {
            System.out.print("+");
        }
        System.out.print("\n");

        for (int i = 0; i < row; i++)
        {
            System.out.print("+");
            for (int j = 0; j < col; j++)
            {
                if (i == Dandelifeon.getRow() && j == Dandelifeon.getCol())
                {
                    System.out.print("*");
                    continue;
                }
                if (Cell[i][j].isAlive())
                {
                    System.out.print("X");
                }
                else
                {
                    System.out.print(" ");
                }
            }
            System.out.print("+\n");
        }

        for (int i = -1; i < row + 1; i++)
        {
            System.out.print("+");
        }
    }

    public void clearMap()
    {
        for (int i = 0; i < Cell.length; i++)
        {
            for (int j = 0; j < Cell[i].length; j++)
            {
                Cell[i][j] =new CellBlock(); //初始化
            }
        }
    }

    public void check()
    {
        Dandelifeon.Dcheck();
    }
}


// input:
//        30
//        1 1
//        0 1
//        1 0
//        2 2
//        2 3
//        3 4
//        4 5
//        2 5
//        3 5
//        3 6
//        3 7
//        4 6
//        4 7
//        2 7
//        5 5
//        5 3
//        5 6
//        5 7
//        6 6
//        6 7
//        6 2
//        6 4
//        7 7
//        7 5
//        7 2
//        7 7
//        8 2
//        8 7
//        8 6
//        8 9
//        9 10
//output:
//        +++++++++++++++++++++++++++
//        +                         +
//        +                         +
//        +           X             +
//        +         XX XX           +
//        +             X           +
//        +      X                  +
//        +     X       X           +
//        +    X      X XX          +
//        +    XX X     X           +
//        +  XX X                   +
//        +  XXX    XX              +
//        +    X    XXX             +
//        +            *            +
//        +         X               +
//        +      XXX                +
//        +      XXX                +
//        +                         +
//        +                         +
//        +                         +
//        +                         +
//        +                         +
//        +                         +
//        +                         +
//        +                         +
//        +                         +
//        +++++++++++++++++++++++++++
//  GameOver,Mana:2520
//  启命英周围 8 格区域中出现存活细胞