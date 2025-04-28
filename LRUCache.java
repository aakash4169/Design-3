import java.util.HashMap;

//time complexity: average O(1)
//space complexity : average O(1)
/*
This code implements an LRU (Least Recently Used) Cache using a combination of a
HashMap and a doubly linked list. The HashMap provides O(1) access to the nodes based on keys,
 while the doubly linked list keeps track of the usage order, allowing quick addition and
 removal of nodes. The get method retrieves a value and moves the accessed node to the head
  (most recently used position). The put method inserts a new key-value pair or updates an
  existing one, and evicts the least recently used node if the cache exceeds its capacity.
his design ensures that both get and put operations run efficiently.
* */
class LRUCache {
    //watch video..how maam thinks
    //my coding style
    //DO NEATLY
    //time complexity
    HashMap<Integer,ListNode> map;
    int capacity;
    ListNode head;
    ListNode tail;

    public LRUCache(int capacity) {
        this.capacity=capacity;
        head=new ListNode(-1,-1);
        tail=new ListNode(-1,-1);
        head.next=tail;
        tail.prev=head;
        map=new HashMap<>();
    }

    private void remove(ListNode temp)
    {
        temp.prev.next=temp.next;
        temp.next.prev=temp.prev;
        temp.prev=null;
        temp.next=null;
    }
    private void addToHead(ListNode temp)
    {
        temp.prev=head;
        temp.next=head.next;
        temp.next.prev=temp;
        head.next=temp;
    }

    public int get(int key) {
        //System.out.println(key);
        if(map.containsKey(key))
        {
            ListNode temp=map.get(key);
            remove(temp);
            addToHead(temp);
            return temp.val;
        }
        else
        {
            return -1;
        }
    }

    public void put(int key, int value) {
        //System.out.println(key);
        if(map.containsKey(key))
        {
            ListNode oldNode=map.get(key);
            oldNode.val=value;
            remove(oldNode);
            addToHead(oldNode);
        }
        else
        {
            if(map.size()==capacity)
            {
                ListNode lastNode=tail.prev;
                //System.out.println("failed after this");
                remove(lastNode);
                map.remove(lastNode.key);
            }
            ListNode newNode=new ListNode(key,value);
            addToHead(newNode);
            map.put(key,newNode);
        }




    }
    class ListNode{
        ListNode prev;
        ListNode next;
        int key;
        int val;
        ListNode(int key,int val){
            this.key=key;
            this.val=val;
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */