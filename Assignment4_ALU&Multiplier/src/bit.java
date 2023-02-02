
public class bit{
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

