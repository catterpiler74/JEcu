package JEcuService;

public class BaseHelper 
{
	/*
	 * Преобразовать массив байтов в строку
	 * 
	 * @param value - массив байтов
	 * @return Возвращает преобразованный в строку массив байтов
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
