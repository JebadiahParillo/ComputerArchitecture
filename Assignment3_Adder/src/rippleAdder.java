public class rippleAdder {
    public static longword add(longword a, longword b) throws Exception {
        longword c = new longword(false);
        bit carrybitin = new bit(false);

        for(int i = 31; i >=0; i--){

            c.setBit(i, a.getBit(i).xor(b.getBit(i)).xor(carrybitin));

            carrybitin = a.getBit(i).and(b.getBit(i)).or(a.getBit(i).xor(b.getBit(i)).and(carrybitin));

        }
        return c;
    }
    public static longword subtract(longword a, longword b)throws Exception{
        longword temp = add(a,b.not());
        return add(temp,new longword(1));
    }

}
