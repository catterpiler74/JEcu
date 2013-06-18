package JEcuService;

public enum TypeHeader 
{
	ThreeBytes(3), FourBytes(4);
	
	private int _value;
	
	private TypeHeader(int value)
	{
		_value = value;
	}
	
	public int GetValue()
	{
		return _value;
	}
}
