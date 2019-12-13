package sample;

public class Product {
    private String name;
    private String quantity;
    private double oldPrice;
    private double newPrice;
    private String imgPath;
    private String description;

    public Product(String name, String quantity, double oldPrice, double newPrice, String imgPath, String description) {
        this.name = name;
        this.quantity = quantity;
        this.oldPrice = oldPrice;
        this.newPrice = newPrice;
        this.imgPath = imgPath;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public double getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(double oldPrice) {
        this.oldPrice = oldPrice;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", quantity=" + quantity +
                ", oldPrice=" + oldPrice +
                ", newPrice=" + newPrice +
                ", imgPath='" + imgPath + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
