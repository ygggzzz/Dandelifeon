import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        ChessBoardWorldSystem ChessBoardWorldSystem=new ChessBoardWorldSystem();
        ChessBoardWorldSystem.Dandelifeon.setchessBoard(ChessBoardWorldSystem);
        int flagg=1;
        while(flagg!=0)
        {
            ChessBoardWorldSystem.check();
            System.out.print("\n");
            System.out.println("输入1继续，0结束");
            flagg=in.nextInt();

            if(flagg==0)
            {
                System.out.println("总魔力："+ChessBoardWorldSystem.getDandelifeonMana());
            }
        }
    }
}
