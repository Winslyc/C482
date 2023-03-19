package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *Creates a Model for Products that contain associated parts
 * @author Winsly Cyrius
 */
public class Product {


    /**
     * The product Id
     */
    private int id;
    /**
     * The product name
     */
    private String name;
    /**
     * The product price
     */
    private double price;
    /**
     * the product stock
     */
     private int stock;
    /**
     * The minimum capacity for the product
     */
    private int min;
    /**
     * the Maximum capacity for the product.
     */
     private  int max;
    /**
     * A List of Parts associated with a specific product
     */
    private ObservableList<Part> asssociatedParts =FXCollections.observableArrayList();

    /**
     * Constructor For the Product class
     * @param id the ID for the product
     * @param name the name for the product
     * @param price the price for the product
     * @param stock the stock for the product
     * @param min the minimum for the product
     * @param max the maximum for the product
     */
    public Product(int id, String name, double price, int stock, int min, int max) {


        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
        this.min = min;
        this.max = max;

    }

    /**
     *  Adds associated Parts to the list of associated parts
     *
     * @param part the part to add to Associated Parts
     */
    public void addAssociatedPart(Part part){
        asssociatedParts.add(part);
    }

    /**
     * Deletes a part from the associated parts list
     * @param associatedPart the selected part to remove from the part list
     * @return a boolean indicating the successful deletion of a part
     */
    public boolean deleteAssociatedPart( Part associatedPart) {
        if (getAllAssociatedParts().contains(associatedPart)){
            getAllAssociatedParts().remove(associatedPart);
            return true;
        }
   return false;
    }

    /**
     * Getter Method for associated parts list
     * @return the list of associated parts
     */
    public ObservableList<Part> getAllAssociatedParts() {

    return asssociatedParts;
    }

    /**
     * The getter for ID
     *
     * @return id of product
     */

    public int getId() {
        return id;
    }

    /**
     * Setter for ID
     * @param id sets the products ID
     */
    public void setId(int id) {
        this.id=id;
    }

    /**
     * Getter for the product Name
     * @return name of product
     */

    public String getName() {
        return name;
    }

    /**
     * Setter for the product name
     * @param name The name of the product
     */
    public void setName(String name) {
        this.name=name;
    }

    /**
     * Getter for the price of product
     * @return the price of product
     */
    public double getPrice() {
        return price;
    }

    /**
     * Setter for the price of product
     * @param price sets product price
     */
    public void setPrice(double price) {
        this.price=price;
    }

    /**
     * Getter for the stock of product
     * @return stock of product
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter for the stock of product
     *
     * @param stock sets the stock of product
     */
    public void setStock(int stock) {
        this.stock=stock;
    }

    /**
     * Getter for the Minimum of product
     *
     * @return the minimum for product
     */
    public int getMin() {
        return min;
    }

    /**
     * Setter for min of product
     *
     * @param min sets minimum for product
     */
    public void setMin(int min) {
        this.min=min;
    }

    /**
     * Getter for product Maximum
     * @return product maximum
     */

    public int getMax() {
        return max;
    }


    /**
     * Setter for product Maximum
     * @param max sets product max
     */
    public void setMax(int max) {
        this.max=max;
    }

    /**
     * Helper Method Checks if product has associated parts
     * @return boolean indicating associated parts existence
     */
    public boolean hasAssociatedParts() {
        if(getAllAssociatedParts().isEmpty()){
            return false;
        }
        else{
            return true;
        }
    }
}



