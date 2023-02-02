public class longword{
    private bit[] lw;

    //default longword constructor
    public longword() {
        this.lw = new bit[32];
    }

    public longword(int value) throws Exception {
        this.lw = new bit[32];
        this.set(value);
    }

    //assign a longword to all true or false with this constructor
    public longword(boolean bool) throws Exception {
        this.lw = new bit[32];

        for (int i = 31; i >= 0; i--) {
            lw[i] = new bit(bool);
        }

    }

    //gets the bit at index i of the longword
    public bit getBit(int i) throws Exception {
        if (i <= 31 && i >= 0) {return this.lw[i];}
        else {throw new Exception("The index of " + i + " is not valid.");}
    }

    //sets the bit at index i of the longword with bit value
    public void setBit(int i, bit value) throws Exception {
        if (i <= 31 && i >= 0) {
            this.lw[i] = value;
        } else {
            throw new Exception("The index passed is not valid.");
        }
    }

    //calls the and function from bit iteratively to and every bit in the longword
    public longword and(longword other) throws Exception {

        longword andout = new longword();

        for (int i = 0; i <= 31; i++) {

            andout.setBit(i, getBit(i).and(other.getBit(i)));

        }
        return andout;
    }

    //calls the or function from bit iteratively to or every bit in the longword
    public longword or(longword other) throws Exception {

        longword orout = new longword();

        for (int i = 0; i <= 31; i++) {

            orout.setBit(i, getBit(i).or(other.getBit(i)));

        }

        return orout;

    }

    //calls the xor function from bit iteratively to xor every bit in the longword
    public longword xor(longword other) throws Exception {

        longword xorout = new longword();

        for (int i = 0; i <= 31; i++) {

            xorout.setBit(i, getBit(i).xor(other.getBit(i)));

        }

        return xorout;

    }

    //calls the not function from bit iteratively to not every bit in the longword
    public longword not() throws Exception {

        longword notout = new longword();
        bit b;

        for (int i = 0; i <= 31; i++) {
            b = getBit(i);
            notout.setBit(i, b.not());

        }
        return notout;
    }

    //right shift the given 32 bit longword by the given amount does not preserve sign
    public longword rightShift(int amount) throws Exception {
        longword rightShiftout = new longword();

        if(amount <= 31 && amount >= 0) {
            int counter = 31 - amount;
            //create a counter that is the index of the new index 31 of the longword

            for (int i = 31; i >= 0; i--) {

                //move counter index to the end of the longword and continue
                if (counter != 0) {
                    rightShiftout.setBit(i, getBit(counter));
                    counter--;
                }
                //fill the rest with zeroes
                else {rightShiftout.setBit(i, new bit(false));}
            }
        }
        else{throw new Exception("The longword can not be shifted by this amount.");}

        return rightShiftout;
    }

    //left shift the given 32 bit longword by the given amount does not preserve sign
    public longword leftShift(int amount) throws Exception {
        longword leftShiftout = new longword();

        if(amount <= 31 && amount >= 0) {
            //start at the far left of the longword
            int counter = 0;
            //make the new zero index into zero plus the shift amount and fill the longword with previous values
            for (int i = 0 + amount; i <= 31; i++) {
                leftShiftout.setBit(counter, getBit(i));
                counter++;
            }
            //fill the rest of the longword with false
            int temp = 32 - amount;
            for (int i = temp; i <= 31; i++) {
                leftShiftout.setBit(i, new bit(false));
            }
        }
        else{throw new Exception("The longword can not be shifted by this amount.");}

        return leftShiftout;
    }

    //copy the called upon longword into the other passed longword
    public void copy(longword other) throws Exception {
        for (int i = 0; i < this.lw.length; i++) {
            other.setBit(i, getBit(i));
        }
    }

    //print the longword as a string e.g. ( 10 ) == f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,f,t,f,t,f
    @Override
    public String toString(){

        String toStringout = "";

        for (int i = 0; i <= 31; i++) {
            toStringout = toStringout +","+ this.lw[i].toString();

        }
        toStringout = toStringout.substring(1);
        return toStringout;
    }

    //return a long decimal representation of an unsigned longword
    public long getUnsigned() throws Exception {

        long getUnsignedout = 0;
        int counter = 0;

        //when the bit at index i is true increment the long by 2 ^ i
        for (int i = 31; i >= 0; i--) {
            bit b = getBit(i);
            if (b.getValue()) {
                getUnsignedout = getUnsignedout + (long) Math.pow(2, counter);
            }
            counter++;
        }

        return getUnsignedout;
    }

    //return an integer representation of the signed longword passed if a positive is passed call unsigned
    public int getSigned() throws Exception{

        int getSignedout = 0;
        int counter = 0;

        //when the bit at index i is false increment the int by 2 ^ i

        if(getBit(0).getValue()) {

            for (int i = 31; i >= 0; i--) {

                bit b = getBit(i);

                if (!b.getValue()) {
                    getSignedout = getSignedout - (int) Math.pow(2, counter);

                }
                counter++;
            }
            return getSignedout - 1;
        }
        else{
            return (int) getUnsigned();
        }
    }

    //sets the longword to the integer value passed in
    public void set(int value) throws Exception {

        int counter = 0;
        boolean bool1;
        boolean bool2;
        if (value > Math.pow(2,31) || value < -Math.pow(2,31)) throw new Exception("Value is larger then what can be represented in 32 bits");
        else {
            //if the value passed is positive
            if (value >= 0) {
                bool1 = false;
                bool2 = true;
            } else {
                bool1 = true;
                bool2 = false;
                value += 1;
            }

            this.setBit(0, new bit(bool1));

            for (int i = 31; i >= 0; i--) {
                int pow = (int) Math.pow(2, i);

                if (value % pow != value) {
                    if(value != 0){
                        this.setBit(counter, new bit(bool2));
                    }
                    else{
                        this.setBit(counter, new bit(false));
                    }
                    if (bool1) {
                        value += pow ;
                    } else {
                        value -= pow;
                    }
                }
                else {
                    this.setBit(counter, new bit(bool1));
                }
                counter++;
            }
        }
    }
}

