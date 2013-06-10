
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		try 
		{
			ECUConnection connection = new ECUConnection("COM6");
			
			connection.Disconnect();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}

}
