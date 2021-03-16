public class MyLinkedList {
    Node head ;
    class Node {
        Object data ;
        Node next;
        //constructor
        Node (Object data){
            this.data = data ;
        }
    }

    public void insertFront(Object data){
        Node newest = new Node(data);
        newest.next = head ;
        head = newest;
    }

    public void insertRear(Object new_data){

        Node new_node = new Node(new_data);

        if (head == null)
        {
            head = new Node(new_data);
            return;
        }

        /* 4. This new node is going to be the last node, so
              make next of it as null */
        new_node.next = null;

        /* 5. Else traverse till the last node */
        Node last = head;
        while (last.next != null)
            last = last.next;

        /* 6. Change the next of last node */
        last.next = new_node;
    }

    public void deletenode(Object key){
        // Store head node
        Node temp = head, prev = null;

        // If head node itself holds the key to be deleted
        if (temp != null && temp.data == key)
        {
            head = temp.next; // Changed head
            return;
        }

        // Search for the key to be deleted, keep track of the
        // previous node as we need to change temp.next
        while (temp != null && temp.data != key)
        {
            prev = temp;
            temp = temp.next;
        }

        // If key was not present in linked list
        if (temp == null) return;

        // Unlink the node from linked list
        prev.next = temp.next;
    }

    //    public void deletatpos(int position){
//
//            // If linked list is empty
//            if (head == null)
//                return;
//
//            // Store head node
//            Node temp = head;
//
//            // If head needs to be removed
//            if (position == 0)
//            {
//                head = temp.next;   // Change head
//                return;
//            }
//
//            // Find previous node of the node to be deleted
//            for (int i=0; temp!=null && i<position-1; i++)
//                temp = temp.next;
//
//            // If position is more than number of ndoes
//            if (temp == null || temp.next == null)
//                return;
//
//            // Node temp->next is the node to be deleted
//            // Store pointer to the next of node to be deleted
//            Node next = temp.next.next;
//
//            temp.next = next;  // Unlink the deleted node from list
//
//    }
    public Object eltatpos(int position){
        // If linked list is empty
        if (head == null)
            return null;

        // Store head node
        Node temp = head;

        // If head needs to be removed
//        if (position == 0)
//        {
//            head = temp.next;   // Change head
//            return head;
//        }

        // Find previous node of the node to be deleted
        for (int i=0; temp!=null && i<position; i++)
            temp = temp.next;

        // If position is more than number of ndoes
        if (temp == null )
            return null;

        // Node temp->next is the node to be deleted
        // Store pointer to the next of node to be deleted
        //  Node next = temp.next.next;

        return temp.data;  // return the  node from list
    }


    public void printlist(){
        Node n = head ;
        System.out.println("hi printlist");
        while(n != null){
            System.out.print(n.data + ","+" ");
            n=n.next ;
        }
    }
    public String printslist(){
        Node n = head ;
        String[] arr = new String[getCount()];
        for(int i=0 ;i<getCount();i++){
            arr[i] = n.data + " " ;
            n=n.next ;
        }
        return arr[getCount()];
    }

    public boolean isllEmpty(){
        if (head==null){
            return  true ;
        }else{
            return false;
        }
    }
    public boolean isllMember(Object data){
        Node temp, prev ;
        temp = head ;
        prev = null ;
        while(temp!= null && temp.data != data){
            prev = temp;
            temp=temp.next ;
        }
        //if that particular data element is not found
        if (temp == null){
            return false ;
        }else{
            return true ;
        }
    }


    public int getCount()
    {
        Node temp = head;
//        System.out.println(temp);
        int count = 0;
        while (temp != null)
        {
            count++;
            temp = temp.next;
        }
        return count;
    }

//    public static void main(String[] args){
//
//    }

}
