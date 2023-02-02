import java.util.Objects;

public class ALU {
    public static longword doOp(bit b1, bit b2, bit b3, bit b4, longword a, longword b) throws Exception {
        //1111 – subtract
        if(b1.and(b2).and( b1.and(b3)).and(b1.and(b4)).getValue()) {
            return rippleAdder.subtract(a, b);
        }
        //1001 – or
        else if(b1.and(b2).not().and(b1.and(b3).not()).and(b1.and(b4)).getValue()) {
            return a.or(b);
        }
        //1010 – xor
        else if(b1.and(b2).not().and(b1.and(b3)).and(b1.and(b4).not()).getValue()) {
            return a.xor(b);
        }
        //1011 – not (not “a”; ignore b)
        else if(b1.and(b2).not().and(b1.and(b3)).and(b1.and(b4)).getValue()) {
            return a.not();
        }
        //1100 – left shift ( “a” is the value to shift, “b” is the amount to shift it; ignore all but the lowest 5 bits)
        else if(b1.and(b2).and(b1.and(b3).not()).and(b1.and(b4).not()).getValue()){
            return a.leftShift(b.getSigned());
        }
        //1101 – right shift ( “a” is the value to shift, “b” is the amount to shift it; ignore all but the lowest 5 bits)
        else if(b1.and(b2).and(b1.and(b3).not()).and(b1.and(b4)).getValue()) {
            return a.rightShift(b.getSigned());
        }
        // 1110 – add
        else if(b1.and(b2).and(b1.and(b3)).and(b1.and(b4).not()).getValue()) {
            return rippleAdder.add(a, b);
        }
        //0111 - multiply
        else if(b1.and(b2).not().and(b1.and(b3).not()).and(b1.and(b4).not()).and(b2.and(b3)).getValue()){
            return multiplier.multiply(a,b);
        }
        // 1000 – and
        else if(b1.and(b2).not().and(b1.and(b3).not()).and(b1.and(b4).not()).and(b2.and(b3).not()).getValue()) {
            return a.and(b);
        }
        else {
            throw new Exception("invalid operation");
        }
    }

    //compares all the operations with a specified opcode and returns the binary representation
    public static String noOp(String opcode) throws Exception {
        //1111 – subtract
        if(Objects.equals(opcode, "sub")) {
            return "1111";
        }
        //1001 – or
        else if(Objects.equals(opcode, "or")) {
            return "1001";
        }
        //1010 – xor
        else if(Objects.equals(opcode, "xor")) {
            return "1010";
        }
        //1011 – not (not “a”; ignore b)
        else if(Objects.equals(opcode, "sub")) {
            return "1011";
        }
        //1100 – left shift ( “a” is the value to shift, “b” is the amount to shift it; ignore all but the lowest 5 bits)
        else if(Objects.equals(opcode, "ls")){
            return "1100";
        }
        //1101 – right shift ( “a” is the value to shift, “b” is the amount to shift it; ignore all but the lowest 5 bits)
        else if(Objects.equals(opcode, "rs")) {
            return "1101";
        }
        // 1110 – add
        else if(Objects.equals(opcode, "add")) {
            return "1110";
        }
        //0111 - multiply
        else if(Objects.equals(opcode, "mul")){
            return "0111";
        }
        // 1000 – and
        else if(Objects.equals(opcode, "and")) {
            return "1000";
        }
        else if(Objects.equals(opcode, "jump")) {
            return "0011";
        }
        else {
            throw new Exception("invalid operation");
        }
    }
}
