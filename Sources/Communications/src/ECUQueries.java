import javax.xml.bind.DatatypeConverter;

public enum ECUQueries
{
	Connect("8110F18103"),
	Disconnect("8110F18204"),
	DiagLow("8310F110810A1F"),
	DiagMed("8310F11081263B"),
	DiagHi("8310F11081394E"),
	DiagStop("8110F120A2"),
	DiagLong("8210F1210FB3"),
	DiagShort("8210F1210EB2"),
	ClearErrors("8310F114FF0097"),
	ReadErrors("8410F11800FF009C"),
	DiagOltV1("8210F1210FB3"),
	DiagOltV3("8210F1210FB3"),
	DiagOltJ7("8210F1210DB1"),
	DiagEuro2("8210F12101A5"),
	DiagRus83("8210F12101A5");
	
	private ECUQueries(final String query)
	{
		this._query = query;
	}
	
	private final String _query;
	
	@Override
	public String toString()
	{
		return _query;
	}
		
	public byte[] ToBytes()
	{
		return DatatypeConverter.parseHexBinary(_query);
	}
}
