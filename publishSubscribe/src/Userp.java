import java.util.HashMap;
import java.util.*;
public class Userp {
	int uid ;
	HashMap<Object, Integer> subscribesList = new HashMap<Object, Integer>();
	HashMap<Integer, Integer> publishList = new HashMap<Integer, Integer>(); // <tid, time>
	public Userp(int uid) {
		this.uid = uid;
	}

	public int getUid() {
		return uid;
	}

//    public static void main(String[] args){
//
//    }

}
