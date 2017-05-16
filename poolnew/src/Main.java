
import java.sql.Connection;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args)  throws SQLException, ClassNotFoundException, InterruptedException, CloneNotSupportedException {
        jdbc_con db;
        db = new jdbc_con("com.mysql.jdbc.Driver","jdbc:mysql://localhost/cyborg","root","");
        if(db.is_connected){
            Connection con=db.getConnection(db);
            Object con1=(Object)con;
            Poolnew p=new Poolnew(4,con1);
            p.getPoolObject(1000);
            p.getPoolObject(5000);
            p.getPoolObject(3000);
            p.getPoolObject(5000);
            p.getPoolObject(2000);
            p.getPoolObject(3000);
            p.getPoolObject(5000);
            p.getPoolObject(2000);
            p.getPoolObject(6000);

            //p.executor.shutdown();
            /*while (!p.executor.isTerminated()) {
            }*/
    }
    }
}
