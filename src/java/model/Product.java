
package model;

import java.util.Arrays;
import java.util.List;

public class Product {
    private int productID;
    private String productName;
    private int categoryID;
    private String size;
    private double price;
    private int quantity;
    private int quantitySold;
    private String image;
    private String image2;
    private String image3;
    private String image4;
    private String description;
    private float discount;
    private boolean status;

    public Product(int productID, String productName, int categoryID, String size, double price, int quantity, int quantitySold, String image, String image2, String image3, String image4, String description, float discount, boolean status) {
        this.productID = productID;
        this.productName = productName;
        this.categoryID = categoryID;
        this.size = size;
        this.price = price;
        this.quantity = quantity;
        this.quantitySold = quantitySold;
        this.image = image;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
        this.description = description;
        this.discount = discount;
        this.status = status;
    }

    // Getters and Setters

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(int quantitySold) {
        this.quantitySold = quantitySold;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getImage2() {
        return image2;
    }

    public void setImage2(String image2) {
        this.image2 = image2;
    }

    public String getImage3() {
        return image3;
    }

    public void setImage3(String image3) {
        this.image3 = image3;
    }

    public String getImage4() {
        return image4;
    }

    public void setImage4(String image4) {
        this.image4 = image4;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean isSizeAvailable(String requestedSize) {
        if (this.size == null || this.size.isEmpty()) {
            return false;
        }
        List<String> availableSizes = Arrays.asList(this.size.split(","));
        return availableSizes.contains(requestedSize);
    }

    @Override
    public String toString() {
        return "Product{" + "productID=" + productID + ", productName=" + productName + ", categoryID=" + categoryID + ", size=" + size + ", price=" + price + ", quantity=" + quantity + ", quantitySold=" + quantitySold + ", image=" + image + ", image2=" + image2 + ", image3=" + image3 + ", image4=" + image4 + ", description=" + description + ", discount=" + discount + ", status=" + status + '}';
    }
}
