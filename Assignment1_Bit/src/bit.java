
interface bit_interface{
    public void set(boolean value); // sets the value of the bit
    public void toggle(); // changes the value from true to false or false to true
    public void set(); // sets the bit to true
    public void clear(); // sets the bit to false
    public boolean getValue(); // returns the current value
    public bit and(bit other); // performs and on two bits and returns a new bit set to the result
    public bit or(bit other); // performs xor on two bits and returns a new bit set to the result
    public bit xor(bit other); // performs or on two bits and returns a new bit set to the result
    public bit not(); // performs not on the existing bit, returning the result as a new bit
    @Override
    public String toString(); // returns t or f
}
public class bit implements bit_interface{
    private boolean value;
    public bit() {this.value = false;}
    public bit(boolean value) {this.value = value;}
    public void set(boolean value) {this.value = value;}
    public void toggle()
    {
        if (this.value) clear();
        else set();
    }
    public void set() {this.value = true;}
    public void clear() {this.value = false;}
    public boolean getValue() {return this.value;}
    public bit and(bit other)
    {
        if (this.value)
        {
            if(other.getValue()) return new bit(true);

            return new bit(false);
        }
        return new bit(false);

    }
    public bit or(bit other)
    {
        if (this.value) return new bit(true);

        if (other.getValue()) return new bit(true);

        return new bit(false);
    }
    public bit xor(bit other)
    {
        if (this.value == other.getValue()) return new bit(false);

        return new bit(true);
    }
    public bit not()
    {
        if (this.value) return new bit(false);

        return new bit(true);

    }
    @Override
    public String toString()
    {
        if (this.value) return "t";

        return "f";

    }
}

