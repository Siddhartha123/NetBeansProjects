import java.util.Stack;
import java.util.concurrent.CompletionService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

public class CompletionServiceProvider {
	public static Stack<FoodPlate> Opool = new Stack<FoodPlate>();
        //push n objects while initializing this class
        CompletionServiceProvider(){
            FoodPlate f=new FoodPlate();
            Opool.push(f);
            Opool.push(f);
        }
	public static final Executor exec = Executors.newFixedThreadPool(2);
	public static final CompletionService completionService = new ExecutorCompletionService<>(exec);
	public static CompletionService getCompletionservice() {
		return completionService;
	}
}