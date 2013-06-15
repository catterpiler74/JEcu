package JEcuService;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			DataCommunication com = new DataCommunication();
			byte[] sender = com.getStartCommunication();

			EcuConnection connection = new EcuConnection("COM1");

			byte[] a = connection.MessageGenerator((byte)0x81);
			byte[] b = connection.MessageGenerator((byte)0x82);

			/*String str = connection.BytesToString(b);
			System.out.println(str);
			System.out.println(java.lang.Integer.toHexString((byte)0x82 & 63));
*/
			connection.Open();

			if (connection.GetConnectionStatus() == true) {
		
				connection.Send(com.getStartCommunication());

				Thread.sleep(1000);
				connection.Send(com.getStopCommunication());
				// connection.Close();
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
