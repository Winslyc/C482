package Model;

public class Outsourced extends Part {

    /**
     * The name of the company that supplies the part.
     */
    private String companyName;

    /**
     * Constructs a new Outsourced object with the given ID, name, price, stock, minimum and maximum levels, and company name.
     * @param id the unique identifier for the part
     * @param name the name of the part
     * @param price the price of the part
     * @param stock the current stock level of the part
     * @param min the minimum stock level required for the part
     * @param max the maximum stock level allowed for the part
     * @param companyName the name of the company that supplies the part
     */
    public Outsourced(int id, String name, double price, int stock, int min, int max, String companyName) {
        super(id, name, price, stock, min, max);
        this.companyName = companyName;
    }

    /**
     * Returns the name of the company that supplies the part.
     * @return the name of the company
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets the name of the company that supplies the part.
     * @param companyName the name of the company
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}