import java.io.Serializable;

public class Transaction implements Serializable {

    private String name;

    public Transaction(String name)
    {
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        name = name;
    }
}
