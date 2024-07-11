
package model;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private String username;
    private List<Item> listItems;

    public Cart(String username) {
        this.username = username;
        listItems = new ArrayList<>();
    }

    public Cart(String username, List<Item> listItems) {
        this.username = username;
        this.listItems = listItems;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Item> getListItems() {
        return listItems;
    }

    public void setListItems(List<Item> listItems) {
        this.listItems = listItems;
    }

    private Item getItemByID(int id) {
        for (Item item : listItems) {
            if (item.getProduct().getProductID()== id) {
                return item;
            }
        }
        return null;
    }

    public int getQuantityByID(int id) {
        return getItemByID(id).getQuantity();
    }

    public void addItem(Item t) {
        if (getItemByID(t.getProduct().getProductID()) != null) {
            Item m = getItemByID(t.getProduct().getProductID());
            m.setQuantity(m.getQuantity() + t.getQuantity());
        } else {
            listItems.add(t);
        }
    }

    public void removeItem(int id) {
        if (getItemByID(id) != null) {
            listItems.remove(getItemByID(id));
        }
    }

    public List<Product> getlistProducts() {
        List<Product> list = new ArrayList<>();
        for (Item item : listItems) {
            list.add(item.getProduct());
        }
        return list;
    }
    public double getTotalMoney() {
        double total = 0;
        for (Item item : listItems) {
            total += Math.round(item.getQuantity() * item.getPrice() * 100) / 100.0;
        }
        total = Math.round(total * 100.0) / 100.0;
        return total;
    }
}
