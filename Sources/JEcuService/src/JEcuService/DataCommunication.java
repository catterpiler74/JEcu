package JEcuService;

import javax.xml.bind.DatatypeConverter;

/*
 * Данные для обмена с ЭБУ
 */
public class DataCommunication 
{
	/*
	 * startCommunication
	 */
	public static final byte STC = (byte) 0x81;
	
	/*
	 * stopCommunication
	 */
	public static final byte SPC = (byte) 0x82;
	
	
	private String _startCommunication = "8110F18103";
	private String _stopCommunication = "8110F18204";
	private String _startDiagnosticSessionLow = "8310F110810A1F";
	
	/*
	 * Geters & Seters
	 */
	
	public byte[] getStartCommunication()
	{
		return this.ToBytes(_startCommunication);
	}
	
	public void setStartCommunication(String value)
	{
		_startCommunication = value;
	}
	
	public byte[] getStopCommunication()
	{
		return this.ToBytes(_stopCommunication);
	}
	
	public void setStopCommunication(String value)
	{
		_stopCommunication = value;
	}
	
	public byte[] getStartDiagnosticSessionLow()
	{
		return this.ToBytes(_startDiagnosticSessionLow);
	}
	
	public void setStartDiagnosticSessionLow(String value)
	{
		_startDiagnosticSessionLow = value;
	}
	
	/*
	 * Преобразование строки в массив байтов
	 */
	private byte[] ToBytes(String str)
	{
		return DatatypeConverter.parseHexBinary(str);
	}
}
