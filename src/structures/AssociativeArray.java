package structures;

import static java.lang.reflect.Array.newInstance;

/**
 * A basic implementation of Associative Arrays with keys of type K
 * and values of type V. Associative Arrays store key/value pairs
 * and permit you to look up values by key.
 *
 * @author Audrey Trinh
 * @author Samuel A. Rebelsky
 */
public class AssociativeArray<K, V> {
  // +-----------+---------------------------------------------------
  // | Constants |
  // +-----------+

  /**
   * The default capacity of the initial array.
   */
  static final int DEFAULT_CAPACITY = 16;

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The size of the associative array (the number of key/value pairs).
   */
  int size;

  /**
   * The array of key/value pairs.
   */
  KVPair<K, V>[] pairs;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create a new, empty associative array.
   */
  @SuppressWarnings({"unchecked"})
  public AssociativeArray() {
    // Creating new arrays is sometimes a PITN.
    this.pairs = (KVPair<K, V>[]) newInstance(KVPair.class, DEFAULT_CAPACITY);
    this.size = 0;
  } // structures.AssociativeArray()

  // +------------------+--------------------------------------------
  // | Standard Methods |
  // +------------------+

  /**
   * Create a copy of this structures.AssociativeArray.
   */
  public AssociativeArray<K, V> clone() {
    AssociativeArray<K, V> cloneArr = new AssociativeArray<>();
    cloneArr.size = this.size;
    if (cloneArr.size >= cloneArr.pairs.length) {
      cloneArr.expand();
    }
    for (int i = 0; i < this.size; i++) {
      cloneArr.pairs[i] = new KVPair<>(this.pairs[i].getKey(), this.pairs[i].getValue());
    }
    return cloneArr;
  } // clone()

  /**
   * Convert the array to a string.
   */
  public String toString() {
    if (this.size == 0) {
      return "{}";
    }
    StringBuilder str = new StringBuilder("{");
    for (int i = 0; i < this.size - 1; i++) {
      str.append(this.pairs[i].toString());
      str.append(",");
    }
    str.append(this.pairs[this.size - 1].toString()).append(" }");
    return str.toString();
  } // toString()

  // +----------------+----------------------------------------------
  // | Public Methods |
  // +----------------+

  /**
   * Set the value associated with key to value. Future calls to
   * get(key) will return value.
   */
  public void set(K key, V value) {
    if (this.size >= this.pairs.length) {
      expand();
    }
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key.equals(key)) {
        // update the value if the key already exists
        this.pairs[i].value = value;
        return;
      }
    }
    this.pairs[this.size] = new KVPair<>(key, value);
    this.size++;
  }

  /**
   * Get the value associated with key.
   *
   * @throws KeyNotFoundException when the key does not appear in the associative
   *                              array.
   */
  public V get(K key) throws KeyNotFoundException {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key.equals(key)) {
        return this.pairs[i].value;
      }
    }
    throw new KeyNotFoundException("There is no key in the array");
  } // get(K)

  /**
   * Determine if key appears in the associative array.
   */
  public boolean hasKey(K key) {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key.equals(key)) {
        return true;
      }
    }
    return false;
  } // hasKey(K)

  /**
   * Remove the key/value pair associated with a key. Future calls
   * to get(key) will throw an exception. If the key does not appear
   * in the associative array, does nothing.
   */
  public void remove(K key) {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key.equals(key)) {
        this.pairs[i].key = this.pairs[this.size - 1].key;
        this.pairs[i].value = this.pairs[this.size - 1].value;
        this.pairs[this.size - 1] = null;
        this.size--;
      }
    }
  } // remove(K)

  /**
   * Determine how many values are in the associative array.
   */
  public int size() {
    return this.size;
  } // size()

  public String[] getAllKeys() {
    String[] keys = new String[this.size];
    int j = 0;
    for (int i = 0; i < pairs.length; i++) {
      if (this.pairs[i] == null) {
        continue;
      }
      keys[j] = this.pairs[i].key.toString();
      j++;
    }
    return keys;
  } // populate the array with keys

  // +-----------------+---------------------------------------------
  // | Private Methods |
  // +-----------------+

  /**
   * Expand the underlying array.
   */
  public void expand() {
    this.pairs = java.util.Arrays.copyOf(this.pairs, this.pairs.length * 2);
  } // expand()

  /**
   * Find the index of the first entry in `pairs` that contains key.
   * If no such entry is found, throws an exception.
   */
  public int find(K key) throws KeyNotFoundException {
    for (int i = 0; i < this.size; i++) {
      if (this.pairs[i].key.equals(key)) {
        return i;
      }
    }
    throw new KeyNotFoundException("The key " + key + " does not exist in the array");
  } // find(K)

} // class structures.AssociativeArray