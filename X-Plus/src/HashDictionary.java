import java.util.LinkedList;
/* Implements the DictionaryADT using a hash table with separate chaining. 
   It stores game configurations and their evaluation scores.				*/
public class HashDictionary implements DictionaryADT{
	// Declaring instance variables
	private LinkedList<Data>[] hashTable;	// The hash table
	private int size;						// Its size 
	private int multiplier = 11;			// The multiplier constant 'x' used in the polynomial hash function
	
	public HashDictionary(int size) {	// Constructor that initializes the instance variables
		this.size = size;
		hashTable = new LinkedList[size];	// create a dictionary of size 'size'
	}
	
	private int hashFunction(String key) {	// Method for computing hash code using Horner's rule for polynomial evaluation
		int hashCode = (int)key.charAt(key.length()-1);	// convert the last character in the string to its int equivalent
		// Loop through from the rest from second last char to the first
		// Multiply the code by 11 and add the character's int equivalent
		// Mod result by size to ensure it gets mapped to valid position on dictionary (0 <= hashcode <= size - 1)
		for(int i = key.length() - 2; i >= 0; i--) {
			hashCode = (hashCode * this.multiplier + (int)key.charAt(i)) % this.size;
		}
		
		return hashCode;
	}
	
	@Override
	public int put(Data pair) throws DictionaryException { // Method to add records to dictionary
		int pos = hashFunction(pair.getConfiguration()); // Generate hash code to find appropriate position in hash table
		
		if(hashTable[pos] == null) { // Initialize a linked list and add pair if dictionary at position is empty
			hashTable[pos] = new LinkedList<Data>(); 
			hashTable[pos].add(pair);
			return 0;	// indicates no collisions
		}
		
		else { // Check if pair's configuration is already in linked list at this position in dictionary
			for(int i = 0; i < hashTable[pos].size(); i++) {
				if(hashTable[pos].get(i).getConfiguration().equals(pair.getConfiguration())) {
					throw new DictionaryException(); 
				}
			}
			// Throw an exception if already existing, otherwise add to linked list
			hashTable[pos].add(pair);
			return 1; // Indicates collisions
		}
	}

	@Override
	public void remove(String config) throws DictionaryException { // Method to remove a record with given config
		int pos = hashFunction(config);
		// If dictionary at position is empty, nothing to remove, throw exception
		if(hashTable[pos] == null) {
			throw new DictionaryException();
		}
		
		else { // Loop through linked list, find the element with same config and remove it
			for(int i = 0; i < hashTable[pos].size(); i++){
				if(hashTable[pos].get(i).getConfiguration().equals(config)) {
					hashTable[pos].remove(i);
					return;
				}
			}
		}
	}

	@Override
	public int get(String config) { // Method to return score of record with key config
		int pos = hashFunction(config);
		// If dictionary at position is empty, config isn't in the dictionary, return -1
		if(hashTable[pos] == null) {
			return -1;
		}
		// Loop through linked list, find element with key config, return its score
		else {
			for(int i = 0; i < hashTable[pos].size(); i++){
				if(hashTable[pos].get(i).getConfiguration().equals(config)) {
					return hashTable[pos].get(i).getScore();
				}
			}
		}
	
		return -1; // Return -1 if config not in linked list
	}

	@Override
	public int numRecords() { // Returns number of records in dictionary
		int count = 0;
		for(int i = 0; i < this.size; i++) { // Loop through positions in dictionary
			if(hashTable[i] == null) {		 // If the position is empty, skip to the next
				continue;
			} else {						 // If not, add the amount of elements in the linked list to count
				count += hashTable[i].size();
			}
		}
		
		return count;	// Return count (number of records)
	}
}
