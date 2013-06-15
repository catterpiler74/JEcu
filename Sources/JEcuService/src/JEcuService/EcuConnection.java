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
	 * Открыть соединение с COM портом
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
	 * Закрыть соединение с COM портом
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
	 * Отправка COM порту массива байтов
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
	
	public byte[] MessageGenerator(byte...args)
	{
		System.out.println("length: " + args.length);
		int sizeArgs = args.length;
		
		int sizeArray = Constants.LengthPackageWithoutMessageForThreeByte + sizeArgs;
		
		byte[] result = new byte[sizeArray];
		result[0] = (byte)(sizeArgs + Constants.DefaultFmtToThreeByte);
		result[1] = Constants.DefualtTgt;
		result[2] = Constants.DefaultSrc;
	
		int i = 3;
		for(byte arg : args)
		{
			result[i] = arg;
			i++;
		}
		
		byte sum = 0x0;
		for(int j = 0; j < (result.length - 1); ++j)
		{
			sum += result[j];
		}
		
		result[sizeArray - 1] = sum;	
		return result;
	}
	
	public String BytesToString(byte[] value)
	{	
		StringBuffer result = new StringBuffer();
		for(byte val : value)
		{
			result.append(String.format("%02X", val));
		}
		return result.toString();
	}
}
