package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * The Inventory class is responsible for managing the parts and products of the application.
 */
public class Inventory {
    /**
     * The ID of the next part to be added
     */
    private static int partId = 0;

    /**
     * The ID of the next product to be added
     */
    private  static  int productId=0;

    /**
     * The list of all parts currently in the inventory
     */
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();

    /**
     * The list of all products currently in the inventory
     */
    private static  ObservableList<Product> allProducts = FXCollections.observableArrayList();

    /**
     * Returns the list of all parts currently in the inventory
     * @return an ObservableList of all parts
     */
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    /**
     * Returns the list of all products currently in the inventory
     * @return an ObservableList of all products
     */
    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    /**
     * Adds a new part to the inventory
     * @param part the part to be added
     */
    public static void addPart(Part part){
        allParts.add(part);
    }

    /**
     * Adds a new product to the inventory
     * @param product the product to be added
     */
    public static void addProduct(Product product){
        allProducts.add(product);
    }

    /**
     * Looks up a part by its ID
     * @param id the ID of the part to be looked up
     * @return the part object corresponding to the given ID, or null if the part is not found
     */
    public static Part lookupPart(int id){
        return null;
    }

    /**
     * Looks up a product by its ID
     * @param id the ID of the product to be looked up
     * @return the product object corresponding to the given ID, or null if the product is not found
     */
    public static Product lookupProduct(int id){
        return  null;
    }

    /**
     * Updates an existing part in the inventory
     * @param id the ID of the part to be updated
     * @param selectedPart the new part object to replace the old part object
     */
    public static void updatePart(int id, Part selectedPart){

    }

    /**
     * Updates an existing product in the inventory
     * @param id the ID of the product to be updated
     * @param selectedPart the new product object to replace the old product object
     */
    public static void updateProduct(int id, Product selectedPart){

    }

    /**
     * Returns a new ID for a part
     * @return the next available ID for a part
     */
    public static int getNewPartID(){
        return ++partId;
    }

    /**
     * Returns a new ID for a product
     * @return the next available ID for a product
     */
    public  static int getNewProductID(){
        return ++productId;
    }

    /**
     * Deletes a part from the inventory
     * @param selectedPart the part to be deleted
     * @return true if the part is successfully deleted, false otherwise
     */
    public static boolean deletePart(Part selectedPart){
        allParts.remove(selectedPart);
        if(allParts.contains(selectedPart)){
            return false;
        } else{
            System.out.println("Part successfully deleted.");
            return true;

}
    } /**
        *Deletes a product from the inventory
        * @param selectedProduct the product to be deleted
     * @return true if the part is deleted, false otherwise
        */
    public static boolean deleteProduct(Product selectedProduct){
        allProducts.remove(selectedProduct);
        if(allProducts.contains(selectedProduct)){
            return false;
        } else{
            System.out.println("Product successfully deleted.");
            return true;
        }
    }
}