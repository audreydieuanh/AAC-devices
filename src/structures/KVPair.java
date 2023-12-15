package structures;

/**
 * An easy way to store key/value pairs. We assume that other
 * classes will access fields directly.
 *
 * @author Audrey Trinh
 * @author Samuel A. Rebelsky
 */
class KVPair<K, V> {
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The key.
   */
  K key;

  /**
   * The value.
   */
  V value;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Create an empty key/value pair.
   */
  KVPair() {
    this(null, null);
  } // structures.KVPair()

  /**
   * Create a new key/value pair.
   */
  KVPair(K key, V value) {
    this.key = key;
    this.value = value;
  } // structures.KVPair(K,V)

  // +------------------+--------------------------------------------
  // | Standard methods |
  // +------------------+

  public KVPair<K, V> clone() {
    return new KVPair<K, V>(this.key, this.value);
  } // clone()

  public String toString() {
    if (key == null) {
      return "";
    }
    return " " + this.key.toString() + ": " + this.value.toString();
  } // toString()

  public K getKey() {
    return key;
  }

  public V getValue() {
    return value;
  }
} // class structures.KVPair
