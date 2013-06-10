import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class ECUConnection implements SerialPortEventListener  {

	private SerialPort _port;
	private String _portName = "COM1";
	private boolean _isPortConnected = false;
	private boolean _isEcuConnected = false;
	
	public ECUConnection() { }
	
	public ECUConnection(String portName) throws Exception
	{
		this.SetPort(portName);
		if(!this.Connect())
			throw new Exception("Unable to connect to ECU with port " + _portName);
	}
	
	public void SetPort(String portName)
	{
		this._portName = portName;
	}
	
	public boolean Connect()
	{
		if(this.ConnectToPort())
			return this.ConnectToECU();
		
		return false;
	}
	
	private boolean ConnectToPort()
	{
		try
		{
			this._port = new SerialPort(_portName);
			this._port.openPort();
			
			this._port.setParams(SerialPort.BAUDRATE_38400, 
					 SerialPort.DATABITS_8, 
					 SerialPort.STOPBITS_1, 
					 SerialPort.PARITY_NONE);
			
			//this._port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | 
            //        SerialPort.FLOWCONTROL_RTSCTS_OUT);
			
			this._port.addEventListener(this);
			
			this._isPortConnected = _port.isOpened();
			
		}
		catch(SerialPortException ex)
		{
			ex.printStackTrace();
			this._isPortConnected = false;
		}
		
		return this._isPortConnected;
	}
	
	private boolean ConnectToECU()
	{
		try
		{
			this._port.writeBytes(ECUQueries.Connect.ToBytes());
			this._isEcuConnected = true;
		}
		catch(SerialPortException ex)
		{
			ex.printStackTrace();
			this._isEcuConnected = false;
		}
		
		return this._isEcuConnected;
	}
	
	public boolean IsConnected() 
	{
		return this._isPortConnected && this._isEcuConnected;
	}
	
	public boolean Disconnect()
	{
		if(this.DisconnectFromEcu())
			return this.DisconnectFromPort();
		
		return false;
	}
	
	private boolean DisconnectFromPort() 
	{
		try
		{
			this._port.closePort();
			this._isPortConnected = _port.isOpened();
		}
		catch(SerialPortException ex)
		{
			ex.printStackTrace();
		}
		return !this._isPortConnected;
	}

	private boolean DisconnectFromEcu()
	{
		try
		{
			this._port.writeBytes(ECUQueries.Disconnect.ToBytes());
			this._isEcuConnected = false;
		}
		catch(SerialPortException ex)
		{
			ex.printStackTrace();
		}
		return !this._isEcuConnected;
	}

	@Override
	public void serialEvent(SerialPortEvent event) {
		
		if(event.isRXCHAR() && event.getEventValue() > 0)
        {
            try 
            {
                String data = _port.readHexString(event.getEventValue());
                this.ParseRequest(data);
                System.out.println(data);
            }
            catch (SerialPortException ex) 
            {
                ex.printStackTrace();
            }
        }
		
	}
	
	private void ParseRequest(String request)
	{
		
	}
	
	

}
