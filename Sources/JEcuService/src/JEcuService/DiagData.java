package JEcuService;
/*
 * ������ ������ Diag
 */
public class DiagData 
{
	/*
	 * ��������
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
