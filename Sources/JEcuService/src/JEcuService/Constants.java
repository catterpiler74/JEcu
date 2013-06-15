package JEcuService;

public class Constants 
{
	public static final String StringEmpty = "";
	
	/*
	 * максимальная длина сообщения для пакета с 3-x байтовым заголовком
	 */
	public static final int MaxLengthMessageWithThreeFmt = 63;
	
	/*
	 * Шаблон заголовка пакета с 3-х байтовым заголовом
	 */
	public static final byte DefaultFmtToThreeByte = (byte)0x80;
	
	/*
	 * Адрес приемника по умолчанию
	 */
	public static final byte DefualtTgt = 0x10;
	
	/*
	 * Адрес передатчика по умолчанию
	 */
	public static final byte DefaultSrc = (byte)0xF1;
	
	/*
	 * Длина стандартного пакета без сообщения для 3-х байтового заголовка
	 */
	public static final int LengthPackageWithoutMessageForThreeByte = 4;
	
	/*
	 * Длина стандартного пакета без сообщения для 4-х байтового заголовка
	 */
	public static final int LengthPackageWithoutMessageForFourByte = 5;
}
