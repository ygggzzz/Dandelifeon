public class Dandelifeon {
    private static int row=12;
    private static int col=12;
    public int countMana=0;

    public int getCountMana()
    {
        return this.countMana;
    }

    public static int getCol() {
        return col;
    }

    public static int getRow() {
        return row;
    }

    public boolean lifeGameCheck()
    {
        boolean flag=false;
        W:
        for(int row=Dandelifeon.getRow()-1;row<=Dandelifeon.getRow()+1;row++)
        {
            for(int col=Dandelifeon.getCol()-1;col<=Dandelifeon.getCol()+1;col++)
            {
                CellBlock Cell=ChessBoardWorldSystem.getCell(row,col);
                if(Cell.isAlive()) //寻找存活细胞
                {
                    flag=true;
                    break W;
                }
            }
        }
        return flag;
    }

    public int getAccumulatedMana()
    {
        int count=0;
        for(int row=Dandelifeon.getRow()-1;row<=Dandelifeon.getRow()+1;row++)
        {
            for(int col=Dandelifeon.getCol()-1;col<=Dandelifeon.getCol()+1;col++)
            {
                CellBlock Cell=ChessBoardWorldSystem.getCell(row,col);
                if(Cell.isAlive())//寻找存活细胞
                {
                    count+=Cell.getAge();
                }
            }
        }
        countMana+=count*60;
        return count*60;
    }

    public int getSurround(int m_row,int m_col) //获取周围存活的细胞数量
    {
        //限定区域
        int start_row = (m_row == 0 ? m_row : m_row - 1);
        int end_row = (m_row == ChessBoardWorldSystem.getRow() - 1 ? m_row : m_row + 1);
        int start_col = (m_col == 0 ? m_col : m_col - 1);
        int end_col = (m_col == ChessBoardWorldSystem.getCol() - 1 ? m_col : m_col + 1);

        int surrounding = 0;
        for (int row = start_row; row <= end_row; row++) {
            for (int col = start_col; col <= end_col; col++) {
                if (row == m_row && col == m_col) {
                    continue; //跳过这个点
                }
                CellBlock cell = ChessBoardWorldSystem.getCell(row, col);
                if (cell.isAlive()) {
                    surrounding++;
                }
            }
        }
        return surrounding;
    }

    public int getMaxAge(int m_row,int m_col) //获取周围存活的细胞最大年龄
    {
        //限定区域
        int start_row = (m_row == 0 ? m_row : m_row - 1);
        int end_row = (m_row == ChessBoardWorldSystem.getRow() - 1 ? m_row : m_row + 1);
        int start_col = (m_col == 0 ? m_col : m_col - 1);
        int end_col = (m_col == ChessBoardWorldSystem.getCol() - 1 ? m_col : m_col + 1);

        int MaxAge=0;
        for(int row=start_row;row<=end_row;row++)
        {
            for(int col=start_col;col<=end_col;col++)
            {
                if(row == m_row && col == m_col)
                {
                    continue; //跳过这个点
                }
                CellBlock cell= ChessBoardWorldSystem.getCell(row,col);
                if(cell.isAlive())
                {
                    if(cell.getAge()>MaxAge)
                    {
                        MaxAge=cell.getAge();
                    }
                }
            }
        }
        return MaxAge;
    }

    public boolean scan() //扫描棋盘，如果细胞小于三个直接结束
    {
        int count = 0;
        boolean flag = false;
        T:
        for (int i = 0; i < ChessBoardWorldSystem.row; i++)
        {
            for (int j = 0; j < ChessBoardWorldSystem.col; j++)
            {
                CellBlock Cell=ChessBoardWorldSystem.getCell(i,j);
                if (Cell.isAlive())
                {
                    count++;
                    if (count == 3)
                    {
                        flag = true;
                        break T;
                    }
                }
            }
        }
        return flag;
    }
}

