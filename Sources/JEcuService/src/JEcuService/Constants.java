package JEcuService;

public class Constants 
{
	/*
	 * ������������ ����� ��������� ��� ������ � 3-x �������� ����������
	 */
	public static final int MaxLengthMessageWithThreeFmt = 63;
	
	/*
	 * ������ ��������� ������ � 3-� �������� ���������
	 */
	public static final byte DefaultFmtToThreeByte = (byte)0x80;
	
	/*
	 * ����� ��������� �� ���������
	 */
	public static final byte DefualtTgt = 0x10;
	
	/*
	 * ����� ����������� �� ���������
	 */
	public static final byte DefaultSrc = (byte)0xF1;
	
	/*
	 * ����� ������������ ������ ��� ��������� ��� 3-� ��������� ���������
	 */
	public static final int LengthPackageWithoutMessageForThreeByte = 4;
	
	/*
	 * ����� ������������ ������ ��� ��������� ��� 4-� ��������� ���������
	 */
	public static final int LengthPackageWithoutMessageForFourByte = 5;
}
