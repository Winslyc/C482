package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private static int partId = 0;
    private  static  int productId=0;
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static  ObservableList<Product> allProducts = FXCollections.observableArrayList();

    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }
    public static void addPart(Part part){
        allParts.add(part);
    }
    public static void addProduct(Product product){
        allProducts.add(product);
    }
    public static Part lookupPart(int id){
        return null;
    }
    public static Product lookupProduct(int id){
        return  null;
    }
    public static void updatePart(int id, Part selectedPart){

    }
    public static void updateProduct(int id, Product selectedPart){

    }

    public static int getNewPartID(){
        return ++partId;
    }
    public  static int getNewProductID(){
        return ++productId;
    }
    public static boolean deletePart(Part selectedPart){
        return false;
    }
    public static boolean deleteProduct(Product selectedPart){
        return false;
    }

}
