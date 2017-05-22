
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args)  throws SQLException, ClassNotFoundException, InterruptedException, CloneNotSupportedException {
        
            Poolnew p=new Poolnew(5,"com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
            p.getPoolObject(500);
            p.getPoolObject(1000);
            p.getPoolObject(1500);
            p.getPoolObject(1300);
            p.getPoolObject(700);
            p.getPoolObject(1700);
            p.getPoolObject(900);
            p.getPoolObject(1600);
            p.getPoolObject(2000);

            //p.executor.shutdown();
            /*while (!p.executor.isTerminated()) {
            }*/
    
    }
}
