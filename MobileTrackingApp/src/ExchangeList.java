public class ExchangeList extends MyLinkedListclass1{
    public ExchangeList() {
    }
    public void printexl(){
       // System.out.println("hi in printexl");
        Node temp ;
        temp =head ;
        while (temp!= null){
            System.out.println("queryfindcall" +temp.data +","+" ");
            temp = temp.next;
        }
    }
}
