package JEcuService;

public class BaseHelper 
{
	/*
	 * ������������� ������ ������ � ������
	 * 
	 * @param value - ������ ������
	 * @return ���������� ��������������� � ������ ������ ������
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
}
