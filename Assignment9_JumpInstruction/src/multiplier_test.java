public class multiplier_test {
    public static void runTests() throws Exception {
        multiply_test(29,7);
        multiply_test(-56,4);
        multiply_test(87,-3);
        multiply_test(-9,-45);
    }
    public static void multiply_test(int val1, int val2) throws Exception {
        longword a = new longword(val1);
        longword b = new longword(val2);
        if(multiplier.multiply(a,b).getSigned() != val1 * val2) throw new Exception("Multiply did not return the correct value.");
        else System.out.println("Multiply Passed! " + val1 + " X " + val2 + " = " + multiplier.multiply(a,b).getSigned());
    }
}
