import java.util.Date;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


public class StudentConsumer implements Runnable {
	private String studName;
	private CompletionService service;
	public String getStudName() {
		return studName;
	}

	public void setStudName(String studName) {
		this.studName = studName;
	}

	public CompletionService getService() {
		return service;
	}

	public void setService(CompletionService service) {
		this.service = service;
	}

	public StudentConsumer(String studName, CompletionService service) {
		this.studName = studName;
		this.service = service;
	}

	@Override
	public void run() {
		System.out.println(this.studName +" waiting "+ " at "+ new Date());
		try {
                     
                        CompletionServiceProvider.completionService.submit(new CanteenStaffProducer());
		        //CompletionServiceProvider.
                    
                        Future<FoodPlate> fp = service.take();
                        FoodPlate f=fp.get();
                        Thread.sleep(1000);
			System.out.println(this.studName+" got Object created by: "+f.getFoodPlateCreatedBy());
                        CompletionServiceProvider.Opool.push(f);
		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
                //put the FoodPlate back in the pool
                
		System.out.println("Exiting run()");
                System.out.println(new Date());
	}

}
