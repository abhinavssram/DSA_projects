public class Myset extends MyLinkedListclass1{
    public  Myset mst ;
    public boolean isEmpty(){
        if (isllEmpty() ){
            return true ;
        }else {
            return false;
        }
    }
    public boolean isMember(Object o){
        if (isllMember(o)){
            return true;
        }else{
            return false;
        }
    }
    public void insert(Object o){
        if (!isMember(o) ) {
            insertRear(o);
        }
    }
    public void delete(Object o) throws Exception{
        if (isllMember(o)) {
            deletenode(o);
        }else{
            throw new Exception();
        }

    }
    public Myset Union(Myset a){
        while(!a.isEmpty()){
            if (!mst.isMember(a)){
                mst.insert(a);
            }
        }
        return mst;
    }
    public Myset Intersection(Myset a) throws Exception{
        while (!a.isEmpty()){
            if (mst.isMember(a)){
                a.delete(a);
            }
        }
        return a;
    }
//    public static void main(String[] args) throws  Exception{
//        Myset mp = new Myset();
//        mp.insert(10);
//        mp.insert(30);
//        mp.insert(20);
//        mp.delete(30);
//        mp.printlist();
//    }
}
