package JEcuService;

public class BaseHelper 
{
	/*
	 * Convert array bytes to string 
	 * @param value - array bytes
	 * @return Get string with hex data from array bytes
	 */
	public String BytesToString(byte[] value)
	{	
		StringBuffer result = new StringBuffer();
		for(byte val : value)
		{
			result.append(String.format("%02X", val));
		}
		return result.toString();
	}
	
	/*
	 * Package message generator
	 */
	public byte[] MessageGenerator(TypeHeader typeHeader, byte...args)
	{
		int sizeArgs = args.length;
		int sizeArray = 0;
		byte[] result = null;
		
		//�������� ���������� ��� ���� ����
		if(typeHeader == TypeHeader.ThreeBytes)
		{
			sizeArray = Constants.LengthPackageWithoutMessageForThreeByte + sizeArgs;
			result = new byte[sizeArray];
			result[0] = (byte)(sizeArgs + Constants.DefaultFmtToThreeByte);		
		}
		
		//�������� ���������� ��� ������ ����
		if(typeHeader == TypeHeader.FourBytes)
		{
			sizeArray = Constants.LengthPackageWithoutMessageForFourByte + sizeArgs;
			result = new byte[sizeArray];
			result[0] = Constants.DefaultFmtToThreeByte;	
			result[3] = (byte)sizeArgs;			
		}		
		
		result[1] = Constants.DefualtTgt;
		result[2] = Constants.DefaultSrc;
		
		int indexDataBytes = typeHeader.GetValue();
		for(byte arg : args)
		{
			result[indexDataBytes] = arg;
			indexDataBytes++;
		}
		
		byte sum = this.GetChecksum(result);		
		result[sizeArray - 1] = sum;	
		return result;
	}
	
	/*
	 * Get checksum package message
	 */
	private byte GetChecksum(byte[] data)
	{
		byte sum = 0x0;
		for(int j = 0; j < (data.length - 1); ++j)
		{
			sum += data[j];
		}
		
		return sum;
	}
	
	/*
	 * Get data input package message
	 */
	public String GetDataMessage(byte[] message)
	{
		int byteHeader = (int)(message[0] & 0xff);                
        int lengthHeader = 0;
        
        if(byteHeader == 128)
        {
        	lengthHeader = 4;
        }
        else
        {
        	lengthHeader = 3;
        }
        
        StringBuffer result = new StringBuffer();
        
        if(lengthHeader == 3)
        {
        	for(int i = lengthHeader; i < (message.length - 1); i++)
        	{
        		result.append(String.format("%02X",  message[i]));
        	}
        	
        	return result.toString();
        }
        
        return Constants.StringEmpty;
	}
}
