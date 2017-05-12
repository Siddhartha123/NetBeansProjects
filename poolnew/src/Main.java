
public class Main {
    public static void main(String[] args) {
        
        Poolnew p=new Poolnew(3);
        p.getPoolObject();
        p.getPoolObject();
        p.getPoolObject();
        p.getPoolObject();
        p.getPoolObject();
        p.getPoolObject();
        p.getPoolObject();
        p.getPoolObject();
        p.getPoolObject();
        
        p.executor.shutdown();
        /*while (!p.executor.isTerminated()) {
        }*/
    }
}
