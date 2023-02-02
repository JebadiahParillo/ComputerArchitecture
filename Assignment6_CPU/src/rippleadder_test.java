public class rippleadder_test {
    public static void runTests()throws Exception{
        testAdd(13,6);
        testAdd(-13,6);
        testAdd(1099,21);
        testSubtract(12,5);
        testSubtract(-12,5);
        testSubtract(9089 , 1089);

    }
    public static void testAdd(int val1, int val2)throws Exception{
        int sum = val1 + val2;

        if (rippleAdder.add(new longword(val1), new longword(val2)).getSigned() != sum) {throw new Exception("The values did not add correctly.");}
        else {System.out.println("Add Passed! " + val1 + " + " + val2 + " = ( " + rippleAdder.add(new longword(val1), new longword(val2)).getSigned() + " ) " + rippleAdder.add(new longword(val1), new longword(val2)));}
    }
    public static void testSubtract(int val1, int val2)throws Exception{
        int sum = val1 - val2;

        if (rippleAdder.subtract(new longword(val1), new longword(val2)).getSigned() != sum) {throw new Exception("The values did not subtract correctly.");}
        else {System.out.println("Subtract Passed! " + val1 + " + " + val2 + " = ( " + rippleAdder.subtract(new longword(val1), new longword(val2)).getSigned() + " ) " + rippleAdder.subtract(new longword(val1), new longword(val2)));}
    }
}
