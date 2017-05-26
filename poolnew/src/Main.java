
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args)  throws SQLException, ClassNotFoundException, InterruptedException, CloneNotSupportedException {
        
            Poolnew p=new Poolnew(3,"com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
            p.getPoolObject(5000);
            p.getPoolObject(10000);
            p.getPoolObject(15000);
            p.getPoolObject(13000);
            p.getPoolObject(7000);
            p.getPoolObject(17000);
            p.getPoolObject(9000);
            p.getPoolObject(16000);
            p.getPoolObject(20000);

            //p.executor.shutdown();
            /*while (!p.executor.isTerminated()) {
            }*/
    
    }
}
