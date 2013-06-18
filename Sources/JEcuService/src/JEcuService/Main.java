package JEcuService;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			EcuConnection connection = new EcuConnection("COM1");
			BaseHelper helper = new BaseHelper();

			byte[] a = helper.MessageGenerator(TypeHeader.ThreeBytes, DataCommunication.STC);
			byte[] b = helper.MessageGenerator(TypeHeader.ThreeBytes, DataCommunication.SPC);
			connection.Open();

			if (connection.GetConnectionStatus() == true) {
		
				connection.Send(a);

				Thread.sleep(1000);
				connection.Send(b);
				// connection.Close();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
