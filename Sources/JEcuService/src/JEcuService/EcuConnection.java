package JEcuService;

import java.util.*;

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
	
	public boolean GetConnectionStatus()
	{
		return _isConnect;
	}
	
	/*
	 * ������� ���������� � COM ������
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
	 * ������� ���������� � COM ������
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
               
                System.out.println("Data:" + _helper.BytesToString(data));
                
                int byteHeader = (int)(data[0] & 0xff);                
                int lengthHeader = 0;
                if(byteHeader == 128)
                {
                	lengthHeader = 4;
                }
                else
                {
                	lengthHeader = 3;
                }
                
                System.out.println("Length header: " + lengthHeader);
                
                int lengthMessage = (int)(data[0] & 63);
                System.out.println("Length message: " + lengthMessage);
                
                /*BitSet bitSet = new BitSet();
                for(byte b : data)
                {
                	String str = String.format("%s : %s", b, (b & 0xff));
                	System.out.println(str);
                }
                
                BitSet fmtBits = new BitSet(data[0] & 0xff);*/
                
               // System.out.println(data[0] & 0xff);
               
                System.out.println();
                
                //this.Close();
            }
            catch (SerialPortException e) 
            {
                e.printStackTrace();
            }
        }
	}
	
	/*
	 * �������� COM ����� ������� ������
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