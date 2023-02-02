public class ALU {
    public static longword multiply(longword a, longword b) throws Exception{

        int cnt = 0;
        longword output = new longword(false);
        longword temp1 = new longword(false);

        for(int i = 31; i >=0; i--){
            for(int k = 31; k >=0; k--){
                temp1.setBit(k, new bit(b.getBit(i).and(a.getBit(k)).getValue()));
            }
            output = rippleAdder.add(output, temp1.leftShift(cnt));
            cnt++;
        }

        return output;
    }
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
        else if(b1.and(b2).not().and( b1.and(b3)).and(b1.and(b4)).getValue()) {
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
            return ALU.multiply(a,b);
        }
        // 1000 – and
        else if(b1.and(b2).not().and(b1.and(b3).not()).and(b1.and(b4).not()).and(b2.and(b3).not()).getValue()) {
            return a.and(b);
        }
        else {
            throw new Exception("invalid operation");
        }
    }
}
