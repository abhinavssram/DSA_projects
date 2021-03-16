import java.util.*;

public class pubsubSystem {

	Map<Integer, DataBaseP<Integer, Integer, String, String, Integer>> hmp = new HashMap<Integer, DataBaseP<Integer, Integer, String, String, Integer>>();
	private DataBaseP<Integer, Integer, String, String, Integer> datainfn ;
	private DataBaseP<Integer, Integer, String, String, Integer> datainfre ;
	private DataBaseP<Integer, Integer, String, String, Integer> datainfrly ;


	LinkedList<Userp> userslist = new LinkedList<Userp>();
	public Userp search_user(int b) {
		for (int i = 0; i < userslist.size(); i++) {
			if (userslist.get(i).getUid() == b) {
				return userslist.get(i);
			}
		}
		return null;
	}

	public void subscribe(int a, int b, int c) {
		try {
//            case1 : userslist is empty
			if (userslist.size() == 0) {
				Userp b1 = new Userp(b);
				Userp c1 = new Userp(c);
				userslist.addLast(b1);
				userslist.addLast(c1);
				b1.subscribesList.put(c1, a);
			} else {
//                  case1 ends.
//                  case2 : userslist is non empty
//                  if b is present
				boolean v1 = false;
				boolean v2 = false;
//                System.out.println(userslist.size());
				for (int i = 0; i < userslist.size(); i++) {
					if (userslist.get(i).getUid() == b) {
						v1 = true;
					}
					if (userslist.get(i).getUid() == c) {
						v2 = true;
					}
				}

				if (v1 && v2) {
					if (!search_user(b).subscribesList.containsKey(search_user(c))) {
						search_user(b).subscribesList.put(search_user(c), a);
					}
				} else if (!v1 && v2) {
					Userp b1 = new Userp(b);
					userslist.addLast(b1);
					b1.subscribesList.put(search_user(c), a);
				} else if (v1 && !v2) {
					Userp c1 = new Userp(c);
					userslist.addLast(c1);
					search_user(b).subscribesList.put(c1, a);
				}
			}
			System.out.println("User " + b + " subscribed to " + " user " + c + " at Time " + a);
		} catch (Exception e) {
			System.out.println("error in subscbribe method");
		}

	}

	public void unsubscribe (int t, int uid, int pid) {
		try {
			if (userslist.size() == 0) {
				Userp uib = new Userp(uid);
				Userp uic = new Userp(pid);
				userslist.addLast(uib);
				userslist.addLast(uic);
				System.out.println("uid " + uid + " has not subscribed to pid " + pid);
			} else {
				boolean v1 = false;
				boolean v2 = false;
//                System.out.println(userslist.size());
				for (int i = 0; i < userslist.size(); i++) {
					if (userslist.get(i).getUid() == uid) {
						v1 = true;
					}
					if (userslist.get(i).getUid() == pid) {
						v2 = true;
					}
				}
//                System.out.println(v1);
//                System.out.println(v2);
//
				if (v1 && v2) {
					if (search_user(uid).subscribesList.containsKey(search_user(pid))) {
						Iterator<Object> subcribesIt = search_user(uid).subscribesList.keySet().iterator();
						while (subcribesIt.hasNext()) {
							Object obj = subcribesIt.next();
							if (obj.equals(search_user(pid))) {
								subcribesIt.remove();
							}
						}
					} else {
						System.out.println("uid " + uid + " has not subscribed to pid " + pid);
					}
				} else if (!v1 && v2) {
					Userp b1 = new Userp(uid);
					userslist.addLast(b1);
					System.out.println("uid " + uid + " has not subscribed to pid " + pid);
				} else if (v1 && !v2) {
					Userp c1 = new Userp(pid);
					userslist.addLast(c1);
					System.out.println("uid " + uid + " has not subscribed to pid " + pid);
				}
				System.out.println("User " + uid + " Unsubscribed to " + " user " + pid + " at Time " + t);
			}
		} catch (Exception e) {
			System.out.println("unsubscribe method");
		}
	}


	public static LinkedList<Object> getKeys(Map<Object, Integer> map, Integer value) {
//        storing keys in list form.Initialising with null.
		LinkedList<Object> keys = null;
		if (map.containsValue(value)) {
			keys = new LinkedList<>();
//          iterating over each entry of map using entryset
			for (Map.Entry<Object, Integer> entry : map.entrySet()) {
				if (entry.getValue().equals(value)) {
					boolean add = keys.add((Userp) entry.getKey());
				}
			}
		}
		return keys;
	}

	public static Integer getKeysByValue1(HashMap<Integer, Integer> map, Integer value) {

		if (map.containsValue(value)) {

//          iterating over each entry of map using entryset
			for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
				if (entry.getValue().equals(value)) {
					return entry.getKey();
				}
			}
		}
		return null;
	}

	public String performAction(String actionMessage) {
		String ress = "";
		String ns = actionMessage;
//        System.out.println("149");
		try {

			if (actionMessage.substring(0, 4).equals("READ")) {

				String[] arr = actionMessage.split(",");
				int x = Integer.parseInt(arr[1]);
				int y = Integer.parseInt(arr[2]);
//                System.out.println("156");
				try {
//                    System.out.println("158");
					if (search_user(y) != null) {
//                       System.out.println("160");
						Iterator<Object> it = search_user(y).subscribesList.keySet().iterator();
//                        System.out.println("162");
						Object obj = null;
						int tlastr = -2 ;
						ArrayList<Integer> k = new ArrayList<>(); //created k here
						HashMap<Integer, LinkedList<Object>> subscribers1 = new HashMap<>();
						//                        creating hashmap subscribers1......
						while (it.hasNext()) {
							obj = it.next();
//                            System.out.println("key = " + obj + " value = " + search_user(y).subscribesList.get(obj));
							tlastr = -1;
							while (tlastr <= x) {
								if (search_user(y).subscribesList.get(obj) == tlastr) {
									subscribers1.put(tlastr, getKeys(search_user(y).subscribesList, tlastr));
								}
								tlastr++;
							}
						}

//                        end of creating hashmaps
						Iterator<Integer> subscribers1it = subscribers1.keySet().iterator();
//                        now getting tid's within times of keys(t1-x,t2-x,.....)
						int cc = 0;
//                        System.out.println(subscribers1.size());
						while (subscribers1it.hasNext()) {
//                            System.out.println("186");
							Integer tmpsbit = null;
							tmpsbit = subscribers1it.next();
							Integer var = tmpsbit;

							for (int i = 0; i < subscribers1.get(var).size(); i++) {

								if (((Userp)subscribers1.get(var).get(i)).publishList.size() != 0) {
									int t = var;

									while ((var <= t) && (t <= x)) {
//                                        System.out.println("var :" +var);
//                                        System.out.println("t :"+t);
										Iterator<Integer> publistit = ((Userp) subscribers1.get(var).get(i)).publishList.keySet().iterator();

										Integer pubobj = null;

										while (publistit.hasNext()) {
											pubobj = publistit.next();

											if (((Userp) subscribers1.get(var).get(i)).publishList.get(pubobj).equals(t)) {

												k.add(pubobj);
											}
										}
										t++;
									}
								} else {
//                                    if (cc==0) {
//                                        System.out.println("210");
//                                        System.out.println("no text available for uid " + y);
//                                    }
									cc++;
								}

							}
						}

//                                    part3 begins... - printing final answer
						Iterator<Integer> itr = hmp.keySet().iterator();
						int j = 0;
//                        System.out.println("hmp :"+hmp.keySet().size());
						int score = 0;
						ArrayList<String> store = new ArrayList<>(); // msgs are stored
						while (itr.hasNext()) {

							Integer tmp = itr.next();

							if (k.size() == 0) {
								if (score == 0 && cc != 0) {
									System.out.println("no text available for uid " + y);
								}
								score++;
							} else {
								for (int i = 0; i < k.size(); i++) {
									if (tmp.equals(k.get(i))) {
//                                           store.add(hmp.get(itr).getText());

										if (hmp.get(tmp).getKeyword().length() == 3) {
											store.add(hmp.get(tmp).getText());
										} else if (hmp.get(tmp).getKeyword().equals("REPOST")) {
											store.add(hmp.get(tmp).getText());
										} else if (hmp.get(tmp).getKeyword().equals("REPLY")) {
											store.add(hmp.get(tmp).getText());
										}
									}
								}
							}


						}
						if (store.size() < 1) {
							if (score != 1) {
								System.out.println("no text available for uid " + y);
							}
						} else if (store.size() == 1) {
							System.out.println("[" + store.get(0) + "]");
						} else if (store.size() > 1) {
							String temps = "[";
							int l;
							for (l = 0 ; l < store.size() - 1; l++) {
								temps = temps + store.get(l) + ",";
							}
							temps = temps + store.get(l) + "]";
							System.out.println(temps);
						}

					} else {
						System.out.println("there is no user with uid " + y);
					}
				} catch (Exception e) {
					return ("error in read action msg");
				}
			} else if ((actionMessage.substring(0, 7).equals("PUBLISH")) ) {
				try {
					if ((ns.substring(12, 15).equals("NEW"))) {
						String[] arr1 = actionMessage.split(",");
						int a1, b1, b2;

						a1 = Integer.parseInt(arr1[1]); // t
						b1 = Integer.parseInt(arr1[2]); // uid
						b2 = Integer.parseInt(arr1[5]); // tid

						try {
							if (arr1[3].length() <= 30) {
								if (!hmp.containsKey(b2)) {
									datainfn = new DataBaseP<>(a1, b1, arr1[3], arr1[4], b2);
									search_user(b1).publishList.put(b2, a1);
									hmp.put(b2, datainfn);
								} else {
									System.out.println("Can't publish with tid " + b2);
								}
							} else {
								System.out.println("length of post is more than 30");
							}
						} catch (Exception e) {
							return ("Can't publish with tid " + b2);
						}
					} else if (ns.substring(12, 17).equals("REPLY")) {


						String[] arr3 = actionMessage.split(",");
						int a3 = Integer.parseInt(arr3[1]);
						int b33 = Integer.parseInt(arr3[2]); // uid
						int b34 = Integer.parseInt(arr3[5]); //
						String[] arr22 = actionMessage.split("\\(");
						String[] arr23 = arr22[1].split("\\)");
						int bb4 = Integer.parseInt(arr23[0]);
						try {
							if (arr3[4].length() <= 30) {
								if (!hmp.containsKey(b34)) {
									datainfrly = new DataBaseP<>(a3, b33, "REPLY", arr3[4], b34);
									search_user(b33).publishList.put(b34, a3);
									hmp.put(b34, datainfrly);
								} else {
									System.out.println("Can't publish with tid " + b34);
								}
							} else {
								System.out.println("length of reply post is more than 30");
							}

						} catch (Exception e) {
							return ("Can't publish with tid " + b34);
						}
					} else if ((ns.substring(12, 18).equals("REPOST"))) {
						String[] arr2 = actionMessage.split(",");
						int a2 = Integer.parseInt(arr2[1]);
						int b3 = Integer.parseInt(arr2[2]);
						int b4 = Integer.parseInt(arr2[4]);
						String[] arr22 = actionMessage.split("\\(");
						String[] arr23 = arr22[1].split("\\)");
						int bb4 = Integer.parseInt(arr23[0]);

						try {
							if (!hmp.containsKey(b4)) {
								String safe = hmp.get(bb4).getText();


								datainfre = new DataBaseP<>(a2, b3, "REPOST", safe, b4);

								search_user(b3).publishList.put(b4, a2);

								hmp.put(b4, datainfre);
							} else {
								System.out.println("Can't publish with tid " + b4);
							}

						} catch (Exception e) {
							return ("No text with ptid " + bb4);
						}
					} else {
						System.out.println("315");
					}
				} catch (Exception e) {
					System.out.println("Can't publish");
				}

			} else if ((actionMessage.substring(0, 9)).equals("SUBSCRIBE")) {
				// change according to substring order
//                System.out.println("272");
				String[] arr4 = actionMessage.split(",");
				int a4 = Integer.parseInt(arr4[1]);
				int b4 = Integer.parseInt(arr4[2]);
				int b44 = Integer.parseInt(arr4[3]);

				try {
//                    System.out.println("280");
					subscribe(a4, b4, b44);
				} catch (Exception e) {
					return ("error in subscribe actmsg");
				}
			} else if (((actionMessage.substring(0, 11)).equals("UNSUBSCRIBE"))) {

				String[] arr5 = actionMessage.split(",");
				int x5 = Integer.parseInt(arr5[1]);
				int y5 = Integer.parseInt(arr5[2]);
				int z5 = Integer.parseInt(arr5[3]);
				try {
					unsubscribe(x5, y5, z5);
				} catch (Exception e) {
					return ("error in unsubscribe actmsg");
				}
			} else {
				throw new Exception();
			}
			return ress;
		} catch (Exception e) {
			System.out.println("error in action-msg");
		}
		return null;
	}

}

