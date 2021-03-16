public class MobilePhone {
    public int number ;
    public boolean status = true ;
    Exchange basest;
    public MobilePhone amp;
    //constructor
    public MobilePhone(int number){
        this.number = number ;

    }

    public int number(){
        return number;
    }
    public boolean status(){
        if (!status){
            return false ;
        }else{
            return true;
        }
    }
    public void SwitchOn(){
        if (!status) {
            status = true ;
        }
    }
    public void SwitchOff(){

            status = false;
    }
    public Exchange location() throws Exception{
        try {
            if (status) {
                return basest;
            } else {
                throw new Exception();
            }
        }catch (Exception e){
         return null ;
        }
    }
    public void setBasest(Exchange b){
        basest = b ;
    }

    public static void main (String[] args){

    }
}
