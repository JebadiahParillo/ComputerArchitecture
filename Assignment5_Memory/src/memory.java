public class memory {
    private bit[] mem;

    //default constructor
    public memory() {
        this.mem = new bit[8192];
    }

    //paramatized constructor to initialize the memory to all false
    public memory(boolean bool){
        this.mem = new bit[8192];
        for (int i = 8191; i >= 0; i--) {
            mem[i] = new bit(bool);
        }
    }
    //read the value stored at the adddress passed in and return it as a longword
    public longword read(longword address) throws Exception {
        //the address passed in should be from 0-1020
        int address_val = address.getSigned();
        if (address_val > 1020 || address_val <0){throw new Exception("Address passed is not valid.");}
        else {
            address_val = address_val * 8;

            int counter = 0;
            longword output = new longword(false);
            //address.getSigned will range from 0-1020
            //i will index the 32 bits within the address 0-1020
            for (int i = address_val; i < address_val + 32; i++) {
                //copy every bit from the address specified into the output longword
                output.setBit(counter, this.mem[i]);
                counter++;
            }
            return output;
        }
    }
    //write a longword value to the soecified longword address
    public void write(longword address, longword value) throws Exception {
        //the address passed in should be from 0-1020
        int address_val = address.getSigned();
        if (address_val > 1020 || address_val <0){throw new Exception("Address passed is not valid.");}
        else {
            address_val = address_val * 8;
            //index for the value passed
            int counter = 0;
            //address.getSigned will range from 0-1020
            //i will index the 32 bits within the address 0-1020
            for (int i = address_val; i < address_val + 32; i++) {
                //copy the value to the memory starting at the address specified
                this.mem[i].set(value.getBit(counter).getValue());
                counter++;
            }
        }
    }
}
