
public class bit_test {
    public static void main(String[] args) throws Exception {
        runTests();
    }
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

        if (b2.getValue() != false) throw new Exception("Value was not set to FALSE");

    }
    public static void testTOGGLE() throws Exception {

        bit b3 = new bit(false);
        b3.toggle();
        if (b3.getValue() != true) throw new Exception("Toggle FALSE to TRUE Failed");

        bit b4 = new bit(false);
        b4.toggle();
        if (b4.getValue() != true) throw new Exception("Toggle TRUE to FALSE Failed");
    }
    public static void testSetTrue() throws Exception {

        bit b5 = new bit(false);
        bit b6 = new bit(true);

        b5.set();
        b6.set();

        if (b5.getValue() != true) throw new Exception("Set FALSE to TRUE Failed");

        if (b6.getValue() != true) throw new Exception("Set TRUE to TRUE Failed");

    }
    public static void testCLEAR() throws Exception {

        bit b7 = new bit(true);
        bit b8 = new bit(false);

        b7.clear();
        b8.clear();

        if (b7.getValue() != false) throw new Exception("Bit was not cleared from TRUE to FALSE");

        if (b8.getValue() != false) throw new Exception("Bit was not cleared from FALSE to FALSE");

    }
    public static void testGetValue() throws Exception {

        if (new bit(true).getValue() != true) throw new Exception("Constructor bit set to TRUE and isn't TRUE");

        if (new bit(false).getValue() != false) throw new Exception("Constructor bit set to FALSE and isn't FALSE");


    }
    public static void testAND() throws Exception {

        if (new bit(false).and(new bit(false)).getValue() != false) throw new Exception("FALSE AND FALSE failed");

        if (new bit(false).and(new bit(true)).getValue() != false) throw new Exception("FALSE AND TRUE failed");

        if (new bit(true).and(new bit(false)).getValue() != false) throw new Exception("TRUE AND FALSE failed");

        if (new bit(true).and(new bit(true)).getValue() != true) throw new Exception("TRUE AND TRUE failed");
    }
    public static void testOR() throws Exception {

        if (new bit(false).or(new bit(false)).getValue() != false) throw new Exception("FALSE OR FALSE failed");

        if (new bit(false).or(new bit(true)).getValue() != true) throw new Exception("FALSE OR TRUE failed");

        if (new bit(true).or(new bit(false)).getValue() != true) throw new Exception("TRUE OR FALSE failed");

        if (new bit(true).or(new bit(true)).getValue() != true) throw new Exception("TRUE OR TRUE failed");


    }
    public static void testXOR() throws Exception {

        if (new bit(false).xor(new bit(false)).getValue() != false) throw new Exception("FALSE XOR FALSE failed");

        if (new bit(false).xor(new bit(true)).getValue() != true) throw new Exception("FALSE XOR TRUE failed");

        if (new bit(true).xor(new bit(false)).getValue() != true) throw new Exception("TRUE XOR FALSE failed");

        if (new bit(true).xor(new bit(true)).getValue() != false) throw new Exception("TRUE XOR TRUE failed");


    }
    public static void testNOT() throws Exception {

        if (new bit(false).not().getValue() != true) throw new Exception("NOT FALSE failed");

        if (new bit(true).not().getValue() != false) throw new Exception("NOT TRUE failed");

    }
    public static void testToString() throws Exception {

        if (new bit(false).toString() != "f") throw new Exception("FALSE to f failed");

        if (new bit(true).toString() != "t") throw new Exception("TRUE to t failed");

    }
}
