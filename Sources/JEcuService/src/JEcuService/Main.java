package JEcuService;

public class Main 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
    	{
    	DataCommunication com = new DataCommunication();
    	byte[] sender = com.getStartCommunication();
    	
    	EcuConnection connection = new EcuConnection("COM1");
    	connection.Open();
    	
    	if(connection.GetConnectionStatus() == true)
    	{
    		connection.Send(sender);
    		
				Thread.sleep(1000);
			connection.Send(com.getStartDiagnosticSessionLow());
				
			Thread.sleep(1000);
    		connection.Send(com.getStopCommunication());
    		//connection.Close();
    	}
    	} 
    	catch (InterruptedException e) 
    	{
			// TODO Auto-generated catch block
			e.printStackTrace();
    	}
	}

}
