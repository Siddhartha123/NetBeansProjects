
import java.util.concurrent.CompletionService;



public class CanteenService {

	public static void main(String[] args) throws Exception{
		/*
		 * Scenario1: Canteen Staff (Producers) preparing food plates 
		 * and no students yet at counter
		 */
		
		//Create a few Canteen Staffs as producers.
		CanteenStaffProducer prod1 = new CanteenStaffProducer("staff1");
		CanteenStaffProducer prod2 = new CanteenStaffProducer("staff2");
		
		//submit tasks of food plate creation to the CompletionService
		CompletionService compService = CompletionServiceProvider.getCompletionservice();
		
		compService.submit(prod1);
		compService.submit(prod2);
	
//		Scenario2: Students (Consumers) at the canteen counter
//		but no food plates yet available.
//		Remember to comment out the two submit calls from above
//		to simulate this situation. Note that the following 
//		thread would block since we have used CompletionService.take 
//		If you need an unblocking retrieval of completed tasks 
//              (retrieval of food plates), use poll method.
		
		new Thread(new StudentConsumer("student1",compService)).start();
		new Thread(new StudentConsumer("student2",compService)).start();
		new Thread(new StudentConsumer("student3",compService)).start();
		/*
		 * Scenario3: To simulate random Produers and Consumers 
		 * (Canteen Staff and Students) please un-comment the 
		 * call to submit methods and enjoy the food plates ;)
		 */
              

	}
}
