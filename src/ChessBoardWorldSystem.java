import java.util.Scanner;

public class ChessBoardWorldSystem {
    public static int row=25;
    public static int col=25;

    public static int getRow()
    {
        return ChessBoardWorldSystem.row;
    }

    public static int getCol()
    {
        return ChessBoardWorldSystem.col;
    }

    static CellBlock[][] Cell=new CellBlock[ChessBoardWorldSystem.getRow()][ChessBoardWorldSystem.getCol()];
    Dandelifeon Dandelifeon=new Dandelifeon();

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
        //核心规则
        //如果启命英周围 8 格区域中出现存活细胞，启命英将产出这些（细胞年龄之和 x 60）的魔力，
        // 并且游戏范围内所有细胞均变为死亡细胞，游戏结束。
        if (Dandelifeon.lifeGameCheck()) {
            int count_check = Dandelifeon.getAccumulatedMana();
            System.out.print("\nGameOver,Mana:" + count_check + "\n");
            clearMap();
            return false;
        }
        //如果一个细胞周围 8 格内的存活细胞数量 < 2 或 > 4该细胞会死亡并且年龄归 0 ；（即康威生命游戏中生命过少和过剩的情况）。
        //如果一个存活细胞周围有 8 格内有 2 ~ 3 个其他存活细胞，该细胞的年龄 + 1 。
        //如果一个死亡细胞周围8格内有 3 个存活细胞，该细胞会变为存活细胞，年龄则取周围 3 个存活细胞的年龄的最大值；（即康威生命游戏中的细胞繁殖）。
        CellBlock[][] NewCell = new CellBlock[ChessBoardWorldSystem.row][ChessBoardWorldSystem.col];
        for (int i = 0; i < ChessBoardWorldSystem.row; i++)
        {
            for (int j = 0; j < ChessBoardWorldSystem.col; j++)
            {
                NewCell[i][j] = new CellBlock(0, false);
            }
        }
        for (int i = 0; i < ChessBoardWorldSystem.row; i++)
        {
            for (int j = 0; j < ChessBoardWorldSystem.col; j++)
            {
                int count = Dandelifeon.getSurround(i,j);
                if (Cell[i][j].isAlive()) {
                    if (count < 2 || count > 4) {
                        NewCell[i][j].setAlive(false);
                    } else if (count == 2 || count == 3) {
                        NewCell[i][j] = Cell[i][j];
                        NewCell[i][j].setAge(Cell[i][j].getAge() + 1);
                    }
                } else {
                    if (count == 3) {
                        NewCell[i][j].setAlive(true);
                        int max = Dandelifeon.getMaxAge(i,j);
                        NewCell[i][j].setAge(max);
                    }
                }
            }
        }
        Cell = NewCell; //覆盖
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