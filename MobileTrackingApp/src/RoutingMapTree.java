public class RoutingMapTree {

    Exchange las ;
    public Exchange root ;
    public RoutingMapTree() {
        root = new Exchange(0);
    }

    public RoutingMapTree(Exchange las) {
        this.las = las;
    }
    public Exchange getRoot(){
        return root ;
    }

    public Boolean containsNode(Exchange a){
        return false;
    }

    public void switchOn(MobilePhone a, Exchange b){
        if (a.status()) {
//            a.status = true;

            a.setBasest(b);
            Exchange ptr = b;
//            System.out.println("ni ayya");
            while (ptr != null) {
//                System.out.println(ptr.exid());
                ptr.mps.insert(a);
                ptr = ptr.parent;
            }
        }
    }
    public void switchoff(MobilePhone a) throws Exception{
        if (a.status() ) {
            Exchange ptt = a.location() ;
            int i =0;
            while ( ptt!= null){

                while (i < ptt.residentSet().getCount()) {
                    if ((((MobilePhone) (ptt.residentSet().eltatpos(i))) == a) && (((MobilePhone) (ptt.residentSet().eltatpos(i))).status())) {
                        ((MobilePhone) (ptt.residentSet().eltatpos(i))).SwitchOff();
                    }
                    i++;
                }
                ptt = ptt.parent ;
            }
        }else{
            throw new Exception();
        }
    }
    public Exchange checkid(int bid){
        Exchange temp1 = null ;
        Exchange pre1 = root ;
          temp1 = subcheckid(pre1,bid);
          return temp1 ;
    }
    public Exchange subcheckid(Exchange aa, int bid){
        while(searchild(aa,bid)){
            if (aa.exid() == bid){
                return aa ;
            }else {
                int n = aa.numChildren();
                for (int j = 0; j < n; j++) {
                    if (searchild(aa.child(j), bid)) {
                        return subcheckid(aa.child(j), bid);
                    }
                }
            }

        }
        return null ;
    }
    public boolean searchild(Exchange bb,int bid){
        //Exchange t ;
       // Exchange t1 = null ;
        boolean t = false;
        if (bb.exid() == bid ){
            return true ;
        }
        else {
            int n = bb.numChildren();
            for (int j = 0; j < n; j++) {
//                System.out.println("huhhhu "+bb.exid());
                if (!bb.isExternal())
                {t = t || searchild(bb.child(j), bid);}
                else {
                    return false;
                }
            }
 //           System.out.println(t);
            return t ;
            }
    }
    public Exchange findPhone(MobilePhone m) throws Exception{

            if (root.mps.getMobph(m) !=null ){
                return m.location();
            }else if (root.mps.getMobph(m) !=null || !m.status()) {
                throw new Exception();
            }
            return null ;
    }

    public Exchange lowestRouter(Exchange a, Exchange b) throws Exception{
       if (searchild(a,a.exid())  && searchild(b,b.exid())) {
           Exchange tmp1 = null, tmp2 = null;
           Exchange tmp3 = null, tmp4 = null;
           tmp1 = a;
           tmp2 = b;
           int p1 = 0;
           int p2 = 0;
           while (tmp1 != null) {
               tmp1 = tmp1.parent;
               p1++;
           }
           while (tmp2 != null) {
               tmp2 = tmp2.parent;
               p2++;
           }
           if (p1 < p2) {
               tmp3 = a;
               tmp4 = b;
           } else if (p1 > p2) {
               tmp3 = b;
               tmp4 = a;
           } else if (p1 == p2) {
               tmp3 = a;
               tmp4 = b;
               while ((tmp3 != null && tmp4 != null) && (tmp3.exid() != tmp4.exid())) {
                   tmp3 = tmp3.parent;
                   tmp4 = tmp4.parent;
               }
               return tmp3;
           }
           if (a.exid() == b.exid()) {
               return a;
           }
           int nch = tmp3.numChildren();
           for (int i = 0; i < nch; i++) {
               if (tmp3.child(i).exid() == tmp4.exid()) {
                   return tmp3;
               }
           }
           if (tmp4 != null) {
               tmp4 = tmp4.parent;
           }
           lowestRouter(tmp3, tmp4);
           return null;
       }else {
           throw new Exception();
       }
    }
    public ExchangeList routeCall (MobilePhone a, MobilePhone b) throws Exception{
        ExchangeList rcExl = new ExchangeList();
        Exchange tmp = null;
        Exchange apar = a.location();
        Exchange bpar = b.location();
        Exchange mid = lowestRouter(apar,bpar) ;
        while(apar.exid()!=mid.exid()){
            if (!rcExl.isllMember(apar)) {
                rcExl.insertRear(apar);
            }
            apar = apar.parent ;
        }

        rcExl.insertRear(mid);

            while (mid.exid() != bpar.exid()) {

                int n = mid.numChildren();

                for (int i = 0; i < n; i++) {

                    try {
                        if (lowestRouter(mid.child(i), bpar).exid() == mid.child(i).exid()) {

                            if (!rcExl.isllMember(mid.child(i))) {

                                rcExl.insertRear(mid.child(i));

                            }

                            tmp = mid.child(i);
                        } else {
                            throw new Exception();
                        }
                    }catch (Exception e){
                        System.out.println();
                    }
                }

                mid = tmp;

            }
        if (!rcExl.isllMember(bpar.exid())) {
            rcExl.insertRear(bpar.exid());
        }

        return rcExl;
    }
    public void movePhone(MobilePhone a, Exchange b) throws Exception{
        Exchange r = a.location();
        if (a.status() && b.numChildren()==0){

            if (a.location().exid()!=b.exid()) {
                a.setBasest(b);
            }
            switchOn(a,b);
            if (root.mps.isMember(a)){
                r.mps.delmobph(a);
            }

        }
    }
    public String performAction(String actionMessage) {
        String ress="";
        try {

            if (actionMessage.substring(0,8).equals("switchOn")) {
                //  String test2 = "switchOnMobile a b";
                String[] arr2 = actionMessage.split(" ");
                int a2 = Integer.parseInt(arr2[1]);
                int b2 = Integer.parseInt(arr2[2]);

                Exchange bb = checkid(b2);
                try {
                    if (checkid(b2) != null && bb.getMobile(a2) != null) {
                        switchOn(bb.getMobile(a2), checkid(b2));
                    } else if (bb.getMobile(a2) == null) {
                        MobilePhone na = new MobilePhone(a2);
                        switchOn(na, bb);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                   return ("switchOnMobile "+Integer.toString(a2)+" "+Integer.toString(b2)+": "+"Error- No exchange with identifier "+Integer.toString(b2));
                }

            } else if (actionMessage.substring(0,9).equals("switchOff")) {
                // String test3 = "switchOffMobile a";
                String[] arr3 = actionMessage.split(" ");
                int a3 = Integer.parseInt(arr3[1]);
                MobilePhone mob = null;
                int i = 0;
                MobilePhoneSet tempset = root.residentSet();
                while (i < tempset.getCount()) {
                    if (((MobilePhone) (tempset.eltatpos(i))).number() == a3) {
                        mob = (MobilePhone) (tempset.eltatpos(i));
                    }
                    i++;
                }
                try {
                    if (mob != null) {
                        switchoff(mob);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    return ("switchOffMobile "+Integer.toString(a3)+": "+"Error- No mobile phone with identifier  "+Integer.toString(a3)+ actionMessage);
                }

            } else if ( (actionMessage.substring(0,9)).equals("findPhone")) {
               // System.out.println("fp");
                String[] arr6 = actionMessage.split(" ");
                int x6 = Integer.parseInt(arr6[1]);
                try {
                    MobilePhone m = null ;
                        int i = 0;
                        MobilePhoneSet tempset = root.residentSet();
                        while (i < tempset.getCount()) {
                            if (((MobilePhone) (tempset.eltatpos(i))).number() == x6) {
                                m = (MobilePhone) (tempset.eltatpos(i));
                            }
                            i++;
                        }
                        Exchange res1 = findPhone(m);
                        ress = "queryFindPhone "+Integer.toString(x6)+ ": " + Integer.toString(res1.exid());
                        return ress;
                } catch (Exception e) {
                    return ("queryFindPhone "+Integer.toString(x6)+ ": "+"Error - No mobile phone with identifier " + Integer.toString(x6) + " found in the network");
                }
            }  else if ((actionMessage.substring(0,9)).equals("movePhone")) {
                // System.out.println("hi");
                String[] arr9 = actionMessage.split(" ");
                int x9 = Integer.parseInt(arr9[1]);
                int y9 = Integer.parseInt(arr9[2]);
                MobilePhone m11 = null;
                int i = 0;
                MobilePhoneSet tempset = root.residentSet();
                while (i < tempset.getCount()) {
                    if (((MobilePhone) (tempset.eltatpos(i))).number() == x9) {
                        m11 = (MobilePhone) (tempset.eltatpos(i));

                    }
                    i++;
                }

                try {
                    if (m11 == null || checkid(y9) == null) {
                        throw new Exception();
                    } else {

                        movePhone(m11, checkid(y9));
                    }
                } catch (Exception e) {
                    return ("error in movephone");
                }
            } else if (((actionMessage.substring(0,12)).equals("lowestRouter"))) {
               // System.out.println("lr");
                String[] arr7 = actionMessage.split(" ");
                int x7 = Integer.parseInt(arr7[1]);
                int y7 = Integer.parseInt(arr7[2]);
                try {
                    Exchange res2 = lowestRouter(checkid(x7), checkid(y7));
                    ress = "queryLowestRouter "+Integer.toString(x7)+" "+Integer.toString(y7)+ ": " + Integer.toString(res2.exid());
                    return ress;
                } catch (Exception e) {
                   return ("error in querylowest");
                }
            } else if (actionMessage.substring(0,11).equals("addExchange")) {
                // String test = "addExchange a b";
                String[] arr = actionMessage.split(" ");
                int x = Integer.parseInt(arr[1]);
                int y = Integer.parseInt(arr[2]);
                Exchange b = new Exchange(y);
                Exchange a = checkid(x);
                try {
                    if (a != null) {

                        // a.addchild(y);
                        a.getObj().insertRear(b);

                        //System.out.println("*");
                        b.setParent(a);
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    return ("error");
                }
            } else if ((actionMessage.substring(0,12)).equals("findCallPath")) {
               // System.out.println("fcpppppppppp");
                String[] arr8 = actionMessage.split(" ");
                int x8 = Integer.parseInt(arr8[1]);
                int y8 = Integer.parseInt(arr8[2]);
                int r = 0;

                    MobilePhone m1 = null;
                    MobilePhone m2 = null;
                    int i = 0;
                    MobilePhoneSet tempset = root.residentSet();
                    int n= tempset.getCount();

                    while (i < n) {

                        if (((MobilePhone) (tempset.eltatpos(i))).number() == x8) {
                            m1 = (MobilePhone) (tempset.eltatpos(i));
                        }
                        if (((MobilePhone) (tempset.eltatpos(i))).number() == y8) {
                            m2 = (MobilePhone) (tempset.eltatpos(i));
                        }
                        i++;
                    }

                    if (!m1.status && m2.status) {

                        r = x8;

                    } else if (!m2.status  && m1.status) {

                        r = y8;

                    }
                    try {

                    ExchangeList rfexl = routeCall(m1, m2);

                    ress = "queryFindCallPath " + Integer.toString(x8) + " " +Integer.toString(y8)+": ";

                    int j =0;
                    int nn = rfexl.getCount();

                        try {
                            while (j < nn) {

                                if (j == 0) {

                                    ress = ress + ((Exchange) rfexl.eltatpos(j)).exid();

                                } else {

                                    ress = ress + "," + " " + ((Exchange) rfexl.eltatpos(j)).exid();

                                }
                                j++;

                            }
                        }catch (Exception e){

                        }

                    return ress ;
                } catch (Exception e) {
                    return ("queryFindCallPath " + Integer.toString(x8) + " " +Integer.toString(y8)+": "+"Error - Mobile phone with identifier " + Integer.toString(r) + " is currently switched off");
                }
            }else if (actionMessage.substring(0,13).equals("queryNthChild")) {
                String[] arr4 = actionMessage.split(" ");
                int x1 = Integer.parseInt(arr4[1]);
                int y1 = Integer.parseInt(arr4[2]);
                Exchange tr = checkid(x1);
                try {
                    if (tr != null && tr.numChildren() >= y1) {
                        ress = actionMessage + ": " + Integer.toString(tr.child(y1).exid());
                        return ress;
                    } else {
                        throw new Exception();
                    }
                } catch (Exception e) {
                    return ("Error - No "+Integer.toString(y1) +" child of Exchange "+Integer.toString(x1));
                }

            } else if (actionMessage.substring(0,19).equals("queryMobilePhoneSet")) {
                String[] arr5 = actionMessage.split(" ");
                int x5 = Integer.parseInt(arr5[1]);
                Exchange tt = checkid(x5);
                try {
                    if (tt != null) {
                        MobilePhoneSet resmps = tt.residentSet();
                        int nmp = resmps.getCount();
                        ress = actionMessage + ": ";
                        int xy = 0;
                        int counter = 0;
                        while (xy < nmp && counter == 0) {
                            if (((MobilePhone) resmps.eltatpos(0)).status()) {
                                ress = ress + ((MobilePhone) resmps.eltatpos(0)).number();
                                counter++;
                            }
                            xy++;
                        }

                        for (int i = xy; i < nmp; i++) {
                            MobilePhone tempp = (MobilePhone) resmps.eltatpos(i);
                            if (tempp.status())
                                ress = ress + (", " + tempp.number());

                        }
                        return ress;
                    } else {
                        throw new RuntimeException();
                    }
                } catch (Exception e) {
                    return ("error in queryMob");
                }
            }else {
                throw new Exception();
            }

            return ress;
        }catch(Exception e){
            System.out.println("error in action-msg");
        }
        return null;
        }

        public static void main  (String[] args){

        }
    }

