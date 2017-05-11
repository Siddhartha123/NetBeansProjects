public class DelayExample{
	public static void main(String[] args){
		System.out.println("Hi");
		for (int i = 0; i < 10; i++)
			{
			System.out.println("Number of itartion = " + i);
			System.out.println("Wait:");
			try
			{
				Thread.sleep(1000);        
             
            }catch (InterruptedException ie)
            {
				System.out.println(ie.getMessage());
            }
        }
    }
}