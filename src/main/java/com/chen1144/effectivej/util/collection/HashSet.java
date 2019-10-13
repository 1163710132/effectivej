package com.chen1144.effectivej.util.collection;

public class HashSet<TValue> implements ICollection<TValue> {
    private BucketNode<TValue>[] buckets;
    private int size;
    private int capacity;
//    private int bits;
//    private int leftBits;

    public HashSet() {
        this.buckets = new BucketNode[8];
        this.capacity = 8;
//        this.bits = 3;
//        this.leftBits = 32 - 3;
    }

    private int capacity(){
        return capacity;//buckets.length;
    }

    private boolean bitAt(int integer, int bit){
        return (integer & (1 << bit)) != 0;
    }

    private void grow(){
        /*if(bits == 30){
            throw new RuntimeException("Bits == 30, Cannot grow anymore");
        }*/
        int newCapacity = capacity * 2;
        BucketNode<TValue>[] newBuckets = new BucketNode[newCapacity];
        for(int i = 0;i < this.capacity;i++){
            BucketNode<TValue> node = buckets[i];
            while (node != null){
                BucketNode<TValue> next = node.next;
                int index = node.hashCode & (capacity - 1); // bitAt(node.hashCode, bits) ? i + capacity : i;
                //int index = node.hashCode % newCapacity;
                node.next = newBuckets[index];
                newBuckets[index] = node;
                node = next;
            }
        }
        capacity = newCapacity;
        buckets = newBuckets;
//        bits = bits + 1;
//        leftBits = leftBits - 1;
    }

    private void requireCapacity(int newSize){
        while (newSize * 4 > capacity * 3){
            grow();
        }
    }

    @Override
    public void add(TValue value) {
        if (++size * 4 > capacity * 3){
            grow();
        }
        int hashCode = value.hashCode();
        int index = hashCode & (capacity - 1); //<< leftBits >>> leftBits;
        buckets[index] = new BucketNode<>(value, hashCode, buckets[index]);
    }

    @Override
    public void remove(TValue value) {
        int hashCode = value.hashCode();
        int index = hashCode & (capacity - 1); // << leftBits >>> leftBits;
        BucketNode<TValue> node = buckets[index];
        if(node == null){
            return;
        }
        if(node.hashCode == hashCode && node.value.equals(value)){
            buckets[index] = node.next;
            --size;
            return;
        }
        while (node.next != null){
            BucketNode<TValue> next = node.next;
            if(next.hashCode == hashCode && next.value.equals(value)){
                node.next = next.next;
                --size;
            }else{
                node = node.next;
            }
        }
    }

    @Override
    public boolean contains(TValue value) {
        int hashCode = value.hashCode();
        int index = hashCode & (capacity - 1); // << leftBits >>> leftBits;
        BucketNode<TValue> node = buckets[index];
        while (node != null){
            if(node.value.equals(value)){
                return true;
            }
            node = node.next;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public IReadonlyEnumerator<TValue> getEnumerator() {
        return new IReadonlyEnumerator<TValue>() {
            private int index = -1;
            private BucketNode<TValue> node = null;

            @Override
            public boolean moveNext() {
                if(node == null){
                    if(index+1 < capacity()){
                        do{
                            node = buckets[++index];
                        }while (node == null && index+1 < capacity);
                    }
                    return node != null;
                }
                else if(node.hasNext()){
                    node = node.next;
                    return true;
                }else{
                    if(index+1 < capacity()){
                        do{
                            node = buckets[++index];
                        }while (node == null && index+1 < capacity);
                    }
                    return node != null;
                }
            }

            @Override
            public TValue value() {
                return node.value;
            }
        };
    }

    public static final class BucketNode<TValue>{
        private TValue value;
        private int hashCode;
        private BucketNode<TValue> next;

        public BucketNode(TValue value, int hashCode, BucketNode<TValue> next) {
            this.value = value;
            this.hashCode = hashCode;
            this.next = next;
        }

        public boolean hasNext(){
            return next != null;
        }
    }
}
