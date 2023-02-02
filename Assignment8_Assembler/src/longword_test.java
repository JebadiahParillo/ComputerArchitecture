public class longword_test {

    public static void runTests()throws Exception{


        testgetBit(true);
        testgetBit(false);
        System.out.println();

        testsetBit(true, 5);
        testsetBit(false, 16);
        System.out.println();

        //testsetBit(false,33);

        testAnd(true,true, true);
        testAnd(true,false, false);
        testAnd(false,true, false);
        testAnd(false,false, false);
        System.out.println();


        testOr(true,true, true);
        testOr(true,false, true);
        testOr(false,true, true);
        testOr(false,false, false);
        System.out.println();


        testXor(true,true, false);
        testXor(true,false, true);
        testXor(false,true, true);
        testXor(false,false, false);
        System.out.println();

        testNot(false, true);
        testNot(true, false);
        System.out.println();



        testrightShift( 4);
        testrightShift( 0);
        testrightShift( 1);
        System.out.println();

        testleftShift(6);
        testleftShift(0);
        testleftShift(1);
        System.out.println();


        testtoString(true);
        testtoString(false);
        System.out.println();

        testgetUnsigned(9483332);
        testgetUnsigned(0);
        testgetUnsigned(1);
        System.out.println();

        testgetSigned(98323);
        testgetSigned(1);
        testgetSigned(0);
        testgetSigned(-1);
        testgetSigned(-109834);
        System.out.println();


        testCopy(true);
        testCopy(false);
        System.out.println();

        testSet(1);
        testSet(-1);
        testSet(0);
        testSet(902442);
        testSet(-74633);
        System.out.println();

    }
    public static void testgetBit(boolean bool1)throws Exception{

        longword lw = new longword(bool1);

        boolean bool2 = lw.getBit(0).getValue();

        if(bool1 != bool2) throw new Exception("The bit was not the expected value");

        else System.out.println("getBit Passed! Actual: " + bool2 + " Expected: " + bool1);

    }
    public static void testsetBit(boolean bool1, int i)throws Exception{

        boolean bool2 = !bool1;
        longword lw = new longword(bool2);
        lw.setBit(i, new bit(bool1));
        boolean bool3 = lw.getBit(i).getValue();

        if(bool3 != bool1) {
            throw new Exception("Bit at index " + i + " was not set to " + bool1 + "instead was " + bool3);
        }
        else System.out.println("setBit Passed! Index: " + i + " Actual: " + bool3 + " Expected: " + bool1);

    }
    public static void testAnd(boolean bool1, boolean bool2, boolean expected)throws Exception{
        longword lw1 = new longword(bool1);
        longword lw2 = new longword(bool2);
        longword lw3 = new longword();

        lw3 = lw1.and(lw2);

        boolean flag = false;

        for(int i = 0; i <= 31; i++){
            if(lw3.getBit(i).getValue() != expected){
                flag = true;
            }
        }
        if (flag) {
            throw new Exception("The AND longword method has not returned a longword with the expected value of " + expected);
        }
        else System.out.println("and Passed! " + lw1 + " && " + lw2 + " == " + lw3);
    }
    public static void testOr(boolean bool1, boolean bool2, boolean expected)throws Exception{
        longword lw1 = new longword(bool1);
        longword lw2 = new longword(bool2);
        longword lw3 = new longword();

        lw3 = lw1.or(lw2);

        boolean flag = false;

        for(int i = 0; i <= 31; i++){
            if(lw3.getBit(i).getValue() != expected){
                flag = true;
            }
        }
        if (flag) {
            throw new Exception("The OR longword method has not returned a longword with the expected value of " + expected);
        }
        else System.out.println("or Passed! " + lw1 + " || " + lw2 + " == " + lw3);
    }
    public static void testXor(boolean bool1, boolean bool2, boolean expected)throws Exception{
        longword lw1 = new longword(bool1);
        longword lw2 = new longword(bool2);
        longword lw3 = new longword();

        lw3 = lw1.xor(lw2);

        boolean flag = false;

        for(int i = 0; i <= 31; i++){
            if(lw3.getBit(i).getValue() != expected){
                flag = true;
            }
        }
        if (flag) {
            throw new Exception("The XOR longword method has not returned a longword with the expected value of " + expected);
        }
        else System.out.println("xor Passed! " + lw1 + " ^ " + lw2 + " == " + lw3);

    }
    public static void testNot(boolean bool1, boolean expected)throws Exception{
        longword lw1 = new longword(bool1);
        longword lw2 = new longword();

        lw2 = lw1.not();

        boolean flag = false;

        for(int i = 0; i <= 31; i++){
            if(lw2.getBit(i).getValue() != expected){
                flag = true;
            }
        }
        if (flag) {
            throw new Exception("The NOT longword method has not returned a longword with the expected value of " + expected);
        }
        else System.out.println("not Passed! " +lw1 + " != " + lw2 );

    }
    public static void testrightShift(int amount)throws Exception{

        longword lw1 = new longword();
        lw1.set(65536);

        longword lw2 = lw1.rightShift(amount);

        if(lw2.getBit(15+amount).getValue() != true){
            throw new Exception("The longword was not shifted right correctly.");
        }
        else {
            System.out.println("LW1 " + lw1 + " was right shifted " + amount + " bits and is now " + lw2);
        }
    }
    public static void testleftShift(int amount)throws Exception{

        longword lw1 = new longword();

        lw1.set(65536);

        longword lw2 = lw1.leftShift(amount);

        if(lw2.getBit(15-amount).getValue() != true){
            throw new Exception("The longword was not shifted left correctly.");
        }
        else {
            System.out.println("LW1 " + lw1 + " was left shifted " + amount + " bits and is now " + lw2);
        }
    }
    public static void testtoString(boolean bool)throws Exception{
        longword lw = new longword(bool);
        boolean flag = false;

        char temp1 = 'f';

        if(bool){ temp1 = 't';}

        String temp2 = lw.toString();
        temp2 = temp2.replace(",","");

        for(int i = 0; i <= 31; i ++){
            char c = temp2.charAt(i);
            if(temp1 != c){
                flag = true;
            }
        }
        if (flag){ throw new Exception("There was an unexpected value in your longword toString.");}

        else{System.out.println("The string has outputted as expected: " + temp2);}

    }
    public static void testgetUnsigned(int value) throws Exception{
        longword lw = new longword();
        lw.set(value);
        long lng = lw.getUnsigned();
        if(lng != value) throw new Exception("The longword was not converted from Unsigned binary to long" + lng);
        else System.out.println("getUnsigned Passed! Expected: " + value + " Actual: " + (int) lng);
    }
    public static void testgetSigned(int value)throws Exception{
        longword lw = new longword();
        lw.set(value);
        int in = lw.getSigned();
        if(in != value) throw new Exception("The longword was not converted from Unsigned binary to long" + in);
        else System.out.println("getSigned Passed! Expected: " + value + " Actual: " + in);
    }
    public static void testCopy(boolean bool)throws Exception{
        longword lw1 = new longword(bool);
        longword lw2 = new longword();
        lw1.copy(lw2);

        boolean flag = false;

        for(int i = 0; i <= 31; i++){
            if (lw1.getBit(i) != lw2.getBit(i)){
                flag = true;
            }
        }
        if(flag) throw new Exception("The longword was not copied to the other longword correctly");
        else System.out.println("copy Passed! LW1: " + lw1 + " was copied to LW2: " + lw2);

    }
    public static void testSet(int value) throws Exception{
        longword lw = new longword();
        int temp;
        lw.set(value);

        if(value < 0){
            temp = lw.getSigned();
        }
        else{
            temp = (int) lw.getUnsigned();
        }

        if(temp != value) throw new Exception("Value was not set to the Expected: " + value + " it was Actual: " + temp);

        else System.out.println("set Passed! The longword was set to " + "( " + temp + " ) " + lw);

    }

}
