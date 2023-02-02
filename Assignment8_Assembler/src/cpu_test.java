public class cpu_test {
    static void runTests() throws Exception {
        haltTest();
        movTest();
        aluTest();
        registerTest();
        preloadTest();
    }
    static void haltTest() throws Exception {
        computer com = new computer();
        String[] preloadString = {"0000000000000000"};
        com.preload(preloadString);
        com.run();
        if(!com.haltTest){
            throw new Exception("Halt was not hit.");
        }
        else{
            System.out.println("Computer Halted!");
        }
        com.haltTest = false;
    }
    static void movTest() throws Exception {
        computer com = new computer();
        String[] preloadString = {"0001000011111111","0000000000000000"};
        com.preload(preloadString);
        com.run();
        if(com.getRegisters(0).getSigned() != -1 ){
            throw new Exception("Value was not loaded into the register.");
        }
        else{
            System.out.println("Mov Test PASSED! Register 0 was: " + com.getRegisters(0).getSigned());
        }
    }
    static void aluTest() throws Exception {
        computer com = new computer();
        String[] preloadString = {"0001000100001010","0001001011110110", "1110000100100011","0000000000000000"};
        com.preload(preloadString);
        com.run();
        if(com.getRegisters(3).getSigned() != 0){
            throw new Exception("Value was not loaded into the register.");
        }
        else{
            System.out.println("ALU Test PASSED! Register 3 was: " + com.getRegisters(3).getSigned());
        }
    }
    static void registerTest() throws Exception {
        computer com = new computer();
        String[] preloadString = {"0010000000000000","0000000000000000"};
        com.preload(preloadString);
        com.run();
        if(!com.registerTest){
            throw new Exception("Register interrupt was not hit.");
        }
        else{
            System.out.println("Registers Printed!");
        }
        com.registerTest = false;
    }
    static void preloadTest() throws Exception {
        computer com = new computer();
        String[] preloadString = {"0010000000000001","0000000000000000"};
        com.preload(preloadString);
        com.run();
        if(!com.preloadTest){
            throw new Exception("Print memory interrupt was not hit.");
        }
        else{
            System.out.println("Memory printed!");
        }
        com.preloadTest = false;
    }

}
