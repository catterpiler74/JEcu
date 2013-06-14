package JEcuService;
/*
 * Данные режима Diag
 */
public class DiagData 
{
	/*
	 * Дроссель
	 */
	private byte _trt;
	
	
	/*
	 * Geters & Seters
	 */
	
	public byte getTRT()
	{
		return _trt;
	}
	
	public void setTRT(byte trt)
	{
		_trt = trt;
	}
	
	public String GetLogHeader()
    {
      return "TIME,TRT";
    }
}
