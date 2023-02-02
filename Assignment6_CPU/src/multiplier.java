public class multiplier {

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
}
