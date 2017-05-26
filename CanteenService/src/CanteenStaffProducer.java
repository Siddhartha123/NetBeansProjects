import java.util.Date;
import java.util.concurrent.Callable;

public class CanteenStaffProducer implements Callable<FoodPlate> {
	
	@Override
	public FoodPlate call() throws Exception {
		//Simulate time required to prepare food plates using Thread sleeps
		System.out.println("Current Canteen Staff at work: at "+ new Date());
		//Thread.sleep(2000L);
                while(CompletionServiceProvider.Opool.empty());
                
                FoodPlate foodPlate = CompletionServiceProvider.Opool.pop();
                foodPlate.setBurgerReady(true);
                foodPlate.setPizzaReady(true);
                foodPlate.setOtherJunkReady(true);
		return foodPlate;
	}
}
 