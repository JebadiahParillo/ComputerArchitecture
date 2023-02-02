public class computer {

    //bit to determine if the computer is halted or not
    private bit halt_bit = new bit(true);

    private memory computer_mem = new memory();

    public void run(){
        while(halt_bit.getValue()){
           fetch();
           decode();
           execute();
           store();
           halt_bit.set(false);
        }
    }
    public void fetch(){
        System.out.println("FETCH");
    }
    public void decode(){
        System.out.println("DECODE");
    }
    public void execute(){
        System.out.println("EXECUTE");
    }
    public void store(){
        System.out.println("STORE");
    }

}
