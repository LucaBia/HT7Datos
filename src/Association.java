public class Association <K, V> {
    public K key ;
    public V value;

    public Association(K key , V value){
        this.key = key;
        this.value = value;
    }

    @Override
    public String toString() {
        return "(" + this.key + ", " + this.value + ") ";
    }
}
