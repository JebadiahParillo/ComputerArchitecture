public class memory_test {
    static memory mem = new memory(false);
    public static void runTests() throws Exception {

        test_memory(4,9888);
        test_memory(167, 98);
        test_memory(56, 3352);
        test_memory(1020, 78654);
        test_overwrite(0,1054334);
    }
    public static void test_memory(int address, int val) throws Exception {
        mem.write(new longword(address), new longword(val));

        if(mem.read(new longword(address)).getSigned() != val){throw new Exception("The Value read was not the one passed");}

        else{System.out.println("Memory read & write has Passed! the value " + val + " was at address " + address);}
    }
    public static void test_overwrite(int address, int val)throws Exception {
        mem.write(new longword(address),new longword(val));
        mem.write(new longword(address+1),new longword(val));
        mem.write(new longword(address+2),new longword(val));

        if(mem.read(new longword(address)).getSigned() == val){throw new Exception("Value was not overwritten as expected");}
        else{System.out.println("The value at the original address was overwritten as expected: " + val + " Actual: " + mem.read(new longword(address)).getSigned());}

    }

}
