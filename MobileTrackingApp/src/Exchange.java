public class Exchange {
        int num ;
        public Exchange parent = null ;
        public int count  = 0;
        public Exchange child ;
        ExchangeList obj = new ExchangeList();
        MobilePhoneSet mps = new MobilePhoneSet();

        public Exchange(int num) {
            this.num = num ;
        }
        public int exid(){
            return num ;
        }
        public Exchange parent(){
            return parent ;
        }
        public void setParent(Exchange a){
            parent = a;
        }

        public ExchangeList getObj() {
                return obj;
        }

        public int numChildren(){
            int a = obj.getCount();
            count = a;
            return count ;
        }
        public int numParent(){
            int i =0 ;

            while (parent()!=null){
                i++;
            }
            return i;
        }
        public Exchange child(int i){
           child = (Exchange) (obj.eltatpos(i));
            return child ;
        }
        public boolean isRoot(){
            if (parent() == null){
                return true ;
            }else{
                return false ;
            }
        }
        public RoutingMapTree subTree(int i){
            //Exchange pre1 = new Exchange(i);
            RoutingMapTree basicroot = new RoutingMapTree();
//            if (i==0){
//                return basicroot ;
//            }else {
//                Exchange temp1 = null;
//                temp1 = basicroot;
//                RoutingMapTree res = new RoutingMapTree(temp1.child(i));
//                return res;
//            }
            return basicroot;
        }
        public MobilePhoneSet residentSet(){
            return mps;
        }

        public void addchild(int a) {
             Exchange child = new Exchange(a);
            //child.parent = this ;
                child.parent().getObj().insertRear(child);
        }
        public boolean isExternal(){
            if (numChildren() == 0){
                return true ;
            }else{
                return false ;
            }
        }

        public MobilePhone getMobile(int i){

            if (mps.isMember(i)){
                 return (MobilePhone) mps.eltatpos(i) ;
            }else{
                return null ;
            }
        }
        public static void  main(String args[]){

        }
    }
