package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *Creates a Model for Products that contain associated parts
 * @author Winsly Cyrius
 */
public class Product extends Part {


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
    private ObservableList<Part> asssociatedParts;


    public Product(int id, String name, double price, int stock, int min, int max) {
        super(id, name, price, stock, min, max);
        asssociatedParts =FXCollections.observableArrayList();
    }

    public void addAssociatedPart(Part part){
        asssociatedParts.add(part);
    }
    public boolean deleteAssociatedPart( Part associatedPart) {
        if (getAllAssociatedParts().contains(associatedPart)){
            getAllAssociatedParts().remove(associatedPart);
            return true;
        }
   return false;
    }
    public ObservableList<Part> getAllAssociatedParts() {

    return asssociatedParts;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id=id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name=name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price=price;
    }

    @Override
    public int getStock() {
        return stock;
    }

    @Override
    public void setStock(int stock) {
        this.stock=stock;
    }

    @Override
    public int getMin() {
        return min;
    }

    @Override
    public void setMin(int min) {
        this.min=min;
    }

    @Override
    public int getMax() {
        return max;
    }

    @Override
    public void setMax(int max) {
        this.max=max;
    }

}



