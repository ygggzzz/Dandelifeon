import java.util.Scanner;

public class ChessBoardWorldSystem {
    public static int row=25;
    public static int col=25;

    private static CellBlock[][] Cell=new CellBlock[ChessBoardWorldSystem.getRow()][ChessBoardWorldSystem.getCol()];
    Dandelifeon Dandelifeon=new Dandelifeon();
    public static int getRow()
    {
        return ChessBoardWorldSystem.row;
    }

    public static int getCol()
    {
        return ChessBoardWorldSystem.col;
    }

    public static CellBlock getCell(int row, int col) {
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

    public boolean stepOneSecond(Dandelifeon Dandelifeon) //每秒一循环
    {
        if(Dandelifeon.lifeGameCheck())  { return false; }
        Cell=Dandelifeon.makeOnceInteration(Cell);
        return true;
    }

    public void initMap(int CellNum,Dandelifeon Dandelifeon) //初始化，输入行列表示这个点有存活细胞
    {
        for (int i = 0; i < row; i++)
        {
            for (int j = 0; j < col; j++)
            {
                Cell[i][j] = new CellBlock(0, false);
            }
        }
        Scanner in = new Scanner(System.in);
        for (int i = 0; i < CellNum; i++)
        {
            int row = in.nextInt();
            int col = in.nextInt();
            //特判在启命英周围的细胞
            if ((row >= Dandelifeon.getRow() - 1 && row <= Dandelifeon.getRow() + 1) || (col >= Dandelifeon.getCol() - 1 && col <= Dandelifeon.getCol())) {

                continue;
            }
            setCellBlock(0, true,row,col);
        }
    }

    public void outputMap(Dandelifeon Dandelifeon)
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

    public static void clearMap()
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
        Scanner in = new Scanner(System.in);
        int num=in.nextInt();
        initMap(num,Dandelifeon);
        outputMap(Dandelifeon);
        while(true) //游戏主循环
        {
            boolean flag = stepOneSecond(Dandelifeon);
            if(flag==false)
            {
                System.out.println("启命英周围 8 格区域中出现存活细胞");
                break;
            }

            System.out.print("\n");
            outputMap(Dandelifeon);

            flag=Dandelifeon.scan();
            if(flag==false)
            {
                System.out.println("数量太少");
                break;
            }

            try {
                Thread.sleep(500); //休眠 看清输出
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            //清屏
            System.out.println(new String(new char[50]).replace("\0", "\r\n"));
        }
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