// Custom exception class used throughout the dictionary implementation.
public class DictionaryException extends RuntimeException 
{
	public DictionaryException()
	{
		super ("Dictionary Error");
	}
}