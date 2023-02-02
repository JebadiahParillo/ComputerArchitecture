public class ALU_test {
    public static void main(String args[])throws Exception{

        System.out.println("Bit testing");
        System.out.println("__________________________");
        bit_test.runTests();
        System.out.println("__________________________");
        System.out.println();
        System.out.println("Longword testing");
        System.out.println("____________________________________________");
        longword_test.runTests();
        System.out.println("____________________________________________");
        System.out.println();
        System.out.println("Add/Subtract testing");
        System.out.println("____________________________________________");
        rippleadder_test.runTests();
        System.out.println("____________________________________________");
        System.out.println("Multiply/ALU testing");
        System.out.println("____________________________________________");
        runTests();
        System.out.println("____________________________________________");
    }
    public static void runTests() throws Exception {
        multiply_test(29,7);
        multiply_test(-56,4);
        multiply_test(87,-3);
        multiply_test(-9,-45);

        alu_test();
    }
    public static void multiply_test(int val1, int val2) throws Exception {
        longword a = new longword(val1);
        longword b = new longword(val2);
        if(ALU.multiply(a,b).getSigned() != val1 * val2) throw new Exception("Multiply did not return the correct value.");
        else System.out.println("Multiply Passed! " + val1 + " X " + val2 + " = " + ALU.multiply(a,b).getSigned());
    }
    public static void alu_test() throws Exception{
        subtract_alu_test(9876,7676);
        or_alu_test(false,true);
        xor_alu_test(false,true);
        and_alu_test(true,true);
        add_alu_test(5436,-6576);
        not_alu_test(true);
        lshift_alu_test(4);
        rshift_alu_test(5);
        multiply_alu_test(4,86);
    }
    public static void subtract_alu_test(int val1, int val2)throws Exception{
        if(ALU.doOp(new bit(true),new bit(true),new bit(true),new bit(true),new longword(val1),new longword(val2)).getSigned() == val1 - val2){
            System.out.println("Subtract (1111) Passed!");
        }
        else throw new Exception("Subtract (1111) Failed");
    }
    public static void or_alu_test(boolean val1, boolean val2)throws Exception{
        if(ALU.doOp(new bit(true),new bit(false),new bit(false),new bit(true),new longword(val1),new longword(val2)) != new longword(val1).or(new longword(val2))){
            System.out.println("or (1001) Passed!");
        }
        else throw new Exception("or (1001) Failed");
    }
    public static void not_alu_test(boolean val1)throws Exception{
        if(ALU.doOp(new bit(true),new bit(false),new bit(true),new bit(true),new longword(val1),new longword(false)) != new longword(val1).not()){
            System.out.println("not (1011) Passed!");
        }
        else throw new Exception("not (1011) Failed");
    }
    public static void xor_alu_test(boolean val1, boolean val2)throws Exception{
        if(ALU.doOp(new bit(true),new bit(false),new bit(true),new bit(false),new longword(val1),new longword(val2)) != new longword(val1).xor(new longword(val2))){
            System.out.println("xor (1010) Passed!");
        }
        else throw new Exception("xor (1010) Failed");
    }
    public static void and_alu_test(boolean val1, boolean val2)throws Exception{
        if(ALU.doOp(new bit(true),new bit(false),new bit(false),new bit(false),new longword(val1),new longword(val2)) != new longword(val1).xor(new longword(val2))){
            System.out.println("and (1000) Passed!");
        }
        else throw new Exception("and (1000) Failed");
    }
    public static void add_alu_test(int val1, int val2)throws Exception{
        if(ALU.doOp(new bit(true),new bit(true),new bit(true),new bit(false),new longword(val1),new longword(val2)).getSigned() == val1 + val2){
            System.out.println("add (1110) Passed!");
        }
        else throw new Exception("add (1110) Failed");
    }
    public static void multiply_alu_test(int val1, int val2)throws Exception{
        if(ALU.doOp(new bit(false),new bit(true),new bit(true),new bit(true),new longword(val1),new longword(val2)).getSigned() == val1 * val2){
            System.out.println("multiply (0111) Passed!");
        }
        else throw new Exception("multiply (0111) Failed");
    }
    public static void rshift_alu_test(int val1)throws Exception{
        if(ALU.doOp(new bit(true),new bit(true),new bit(false),new bit(true),new longword(true),new longword(val1)).getSigned() == new longword(true).rightShift(val1).getSigned()){
            System.out.println("right shift (1101) Passed!");
        }
        else throw new Exception("right shift (1101) Failed");
    }

    public static void lshift_alu_test(int val1)throws Exception{
        if(ALU.doOp(new bit(true),new bit(true),new bit(false),new bit(false),new longword(true),new longword(val1)).getSigned() == new longword(true).leftShift(val1).getSigned()){
            System.out.println("left shift (1100) Passed!");
        }
        else throw new Exception("left shift (1100) Failed");
    }
}
