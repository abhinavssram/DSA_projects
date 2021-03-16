public class MobilePhoneSet extends Myset{

    Myset phs = null;
    public static void main(String[] args){

    }
    public MobilePhone getMobph(MobilePhone m){
        Node tmp =head ;
        while (tmp.data != m){
            tmp =tmp.next ;
        }
        return (MobilePhone) tmp.data ;
    }
    public void delmobph(MobilePhone s){
        Node tmp = head ;
        while (tmp.data!=s){
            tmp =tmp.next;
        }
       deletenode(tmp.data);
    }
    public MobilePhone getmwithid(int mid){
        int i =0;
        int n = phs.getCount();
        while (i<n){
            if (((MobilePhone) phs.eltatpos(i)).number == mid){
                return (MobilePhone) phs.eltatpos(i);
            }
            i++;
        }
        return null;
    }
}
