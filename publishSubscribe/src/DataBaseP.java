//import java.util.ArrayList;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class DataBaseP<A,B,C,D,E> extends MyLinkedList{
    public A time ;
    public B uiid ;
    public C keyword ;
    public D text ;
    public E textid ;

    public DataBaseP(A time, B uiid, C keyword, D text, E textid) {
        this.time = time;
        this.uiid = uiid;
        this.keyword = keyword;
        this.text = text;
        this.textid = textid;
    }

    public A getTime() {
        return time;
    }

    public void setTime(A time) {
        this.time = time;
    }

    public B getUiid() {
        return uiid;
    }

    public void setUiid(B uiid) {
        this.uiid = uiid;
    }

    public C getKeyword() {
        return keyword;
    }

    public void setKeyword(C keyword) {
        this.keyword = keyword;
    }

    public D getText() {
        return text;
    }

    public void setText(D text) {
        this.text = text;
    }

    public E getTextid() {
        return textid;
    }

    public void setTextid(E textid) {
        this.textid = textid;
    }
   // ArrayList<DataBaseP<Integer,Integer,String,String,Integer>> datainf = new ArrayList<DataBaseP<Integer,Integer,String,String,Integer>>();
//    Map<Object,MyLinkedList> hm = new HashMap<>();
//   public static void main(String[] args){
//
//   }
}
