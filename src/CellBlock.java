public class CellBlock {

        private int m_age;
        private boolean m_alive;
        //private int m_row, m_col;

        CellBlock() {
            this.m_age = 0;
            this.m_alive = false;

        }
        CellBlock(int age, boolean alive) {
            this.m_age = age;
            this.m_alive = alive;
//        this.m_row = row;
//        this.m_col = col;
        }

//        public void clearCell()
//        {
//            this.m_age=0;
//            this.m_alive=false;
//        }

        public int getAge() {
            if(m_age>100)
                m_age=100;
            return m_age;
        }

        public void setAge(int age) {
            this.m_age = age;
        }

        public boolean isAlive() {
            return m_alive;
        }

        public void setAlive(boolean alive) {
            this.m_alive = alive;
        }

//    public int getRow() {
//        return m_row;
//    }
//
//    public void setRow(int row) {
//        this.m_row = row;
//    }
//
//    public int getCol() {
//        return m_col;
//    }
//
//    public void setCol(int col) {
//        this.m_col = col;
//    }


}
