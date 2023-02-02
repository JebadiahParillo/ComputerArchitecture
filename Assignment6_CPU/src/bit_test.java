
public class bit_test {
    static void runTests() throws Exception {
        testSET();
        testTOGGLE();
        testSetTrue();
        testCLEAR();
        testGetValue();
        testAND();
        testOR();
        testXOR();
        testNOT();
        testToString();

    }
    public static void testSET() throws Exception {

        bit b1 = new bit();
        bit b2 = new bit();

        b1.set(true);
        b2.set(false);

        if (b1.getValue() != true) throw new Exception("Value was not set to TRUE");
        else System.out.println("Bit was set to True");

        if (b2.getValue() != false) throw new Exception("Value was not set to FALSE");
        else System.out.println("Bit was set to False");

        System.out.println("set PASSED!");
        System.out.println();

    }
    public static void testTOGGLE() throws Exception {

        bit b3 = new bit(false);
        b3.toggle();
        if (b3.getValue() != true) throw new Exception("Toggle FALSE to TRUE Failed");
        else System.out.println("Testing toggle from FALSE to TRUE Passed.");

        bit b4 = new bit(false);
        b4.toggle();
        if (b4.getValue() != true) throw new Exception("Toggle TRUE to FALSE Failed");
        else System.out.println("Testing toggle from TRUE to FALSE Passed.");

        System.out.println("toggle PASSED!");
        System.out.println();

    }
    public static void testSetTrue() throws Exception {

        bit b5 = new bit(false);
        bit b6 = new bit(true);

        b5.set();
        b6.set();

        if (b5.getValue() != true) throw new Exception("Set FALSE to TRUE Failed");
        else System.out.println("Testing set from FALSE to TRUE Passed.");


        if (b6.getValue() != true) throw new Exception("Set TRUE to TRUE Failed");
        else System.out.println("Testing set from TRUE to TRUE Passed.");

        System.out.println("set PASSED!");
        System.out.println();

    }
    public static void testCLEAR() throws Exception {

        bit b7 = new bit(true);
        bit b8 = new bit(false);

        b7.clear();
        b8.clear();

        if (b7.getValue() != false) throw new Exception("Bit was not cleared from TRUE to FALSE");
        else System.out.println("Testing set from TRUE to FALSE Passed.");


        if (b8.getValue() != false) throw new Exception("Bit was not cleared from FALSE to FALSE");
        else System.out.println("Testing set from FALSE to FALSE Passed.");

        System.out.println("clear PASSED!");
        System.out.println();


    }
    public static void testGetValue() throws Exception {

        if (new bit(true).getValue() != true) throw new Exception("Constructor bit set to TRUE and isn't TRUE");
        else System.out.println("Value was TRUE as expected.");


        if (new bit(false).getValue() != false) throw new Exception("Constructor bit set to FALSE and isn't FALSE");
        else System.out.println("Value was FALSE as expected.");

        System.out.println("getValue PASSED!");
        System.out.println();


    }
    public static void testAND() throws Exception {

        if (new bit(false).and(new bit(false)).getValue() != false) throw new Exception("FALSE AND FALSE failed");
        else System.out.println("FALSE && FALSE Passed.");

        if (new bit(false).and(new bit(true)).getValue() != false) throw new Exception("FALSE AND TRUE failed");
        else System.out.println("FALSE && TRUE Passed.");

        if (new bit(true).and(new bit(false)).getValue() != false) throw new Exception("TRUE AND FALSE failed");
        else System.out.println("TRUE && FALSE Passed.");

        if (new bit(true).and(new bit(true)).getValue() != true) throw new Exception("TRUE AND TRUE failed");
        else System.out.println("TRUE && TRUE Passed.");

        System.out.println("and PASSED!");
        System.out.println();

    }
    public static void testOR() throws Exception {

        if (new bit(false).or(new bit(false)).getValue() != false) throw new Exception("FALSE OR FALSE failed");
        else System.out.println("FALSE || FALSE Passed.");

        if (new bit(false).or(new bit(true)).getValue() != true) throw new Exception("FALSE OR TRUE failed");
        else System.out.println("FALSE || TRUE Passed.");

        if (new bit(true).or(new bit(false)).getValue() != true) throw new Exception("TRUE OR FALSE failed");
        else System.out.println("TRUE || FALSE Passed.");

        if (new bit(true).or(new bit(true)).getValue() != true) throw new Exception("TRUE OR TRUE failed");
        else System.out.println("TRUE || TRUE Passed.");

        System.out.println("or PASSED!");
        System.out.println();

    }
    public static void testXOR() throws Exception {

        if (new bit(false).xor(new bit(false)).getValue() != false) throw new Exception("FALSE XOR FALSE failed");
        else System.out.println("FALSE ^ FALSE Passed.");

        if (new bit(false).xor(new bit(true)).getValue() != true) throw new Exception("FALSE XOR TRUE failed");
        else System.out.println("FALSE ^ TRUE Passed.");

        if (new bit(true).xor(new bit(false)).getValue() != true) throw new Exception("TRUE XOR FALSE failed");
        else System.out.println("TRUE ^ FALSE Passed.");

        if (new bit(true).xor(new bit(true)).getValue() != false) throw new Exception("TRUE XOR TRUE failed");
        else System.out.println("TRUE ^ TRUE Passed.");

        System.out.println("xor PASSED!");
        System.out.println();

    }
    public static void testNOT() throws Exception {

        if (new bit(false).not().getValue() != true) throw new Exception("NOT FALSE failed");
        else System.out.println("! FALSE Passed.");

        if (new bit(true).not().getValue() != false) throw new Exception("NOT TRUE failed");
        else System.out.println("! TRUE Passed.");

        System.out.println("not PASSED!");
        System.out.println();

    }
    public static void testToString() throws Exception {

        if (new bit(false).toString() != "f") throw new Exception("FALSE to f failed");
        else System.out.println("FALSE to f Passed.");

        if (new bit(true).toString() != "t") throw new Exception("TRUE to t failed");
        else System.out.println("TRUE to t Passed.");


        System.out.println("toString PASSED!");
        System.out.println();
    }
}
