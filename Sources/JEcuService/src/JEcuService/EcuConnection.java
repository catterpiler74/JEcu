package JEcuService;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class EcuConnection implements SerialPortEventListener
{
	private SerialPort _serialPort;
	private String _comName = "";
	private BaseHelper _helper = new BaseHelper();
	
	private boolean _isConnect = false;
	
	public EcuConnection(String com)
	{
		_comName = com;
		
		_serialPort = new SerialPort(_comName);
		
	}
	
	/*
	 * Return boolean connection status
	 */
	public boolean GetConnectionStatus()
	{
		return _isConnect;
	}
	
	/*
	 * Open connection with port
	 */
	public void Open()
	{
		try 
		{
			_serialPort.openPort();
			
			_serialPort.setParams(SerialPort.BAUDRATE_38400, 
						SerialPort.DATABITS_8, 
						SerialPort.STOPBITS_1, 
						SerialPort.PARITY_NONE);
			
			_serialPort.addEventListener(this);
			
			_isConnect = true;
			
		} catch (SerialPortException e) 
		{
			e.printStackTrace();
		}		
	}
	
	/*
	 * Close connection with port
	 */
	public void Close()
	{
		try
		{
			_serialPort.closePort();
			_isConnect = false;
		}
		catch(SerialPortException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void serialEvent(SerialPortEvent event) 
	{
		if(event.isRXCHAR() && event.getEventValue() > 0)
		{
            try 
            {
                byte[] data = _serialPort.readBytes(event.getEventValue());
                
                String message = _helper.GetDataMessage(data);
                System.out.println("Message: " + message);
                
                //this.Close();
            }
            catch (SerialPortException e) 
            {
                e.printStackTrace();
            }
        }
	}
	
	/*
	 * Send array bytes
	 */
	public void Send(byte[] bytes)
	{
		try
		{
			_serialPort.writeBytes(bytes);
		} 
		catch (SerialPortException e) 
		{
			e.printStackTrace();
		}
	}	
}
