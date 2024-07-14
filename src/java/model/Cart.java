package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private String username;
    private List<Item> listItems;
    private int size;

    public Cart(String username) {
        this.username = username;
        listItems = new ArrayList<>();
        this.size = 0;
    }

    public Cart(String username, List<Item> listItems) {
        this.username = username;
        this.listItems = listItems;
        this.size = listItems.size();
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
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
        this.size = listItems.size();
    }

    public Item getItemByIDAndSize(int id, String size) {
        for (Item item : listItems) {
            if (item.getProduct().getProductID() == id && item.getSize().equals(size)) {
                return item;
            }
        }
        return null;
    }

    public int getQuantityByIDAndSize(int id, String size) {
        Item item = getItemByIDAndSize(id, size);
        return (item != null) ? item.getQuantity() : 0;
    }

    public boolean addItem(Item t) {
        List<Item> list= getItemsByPID(t.getProduct().getProductID());
        int currentQuantity = 0;
        for(Item i:list) {
            currentQuantity+=i.getQuantity();
        }
        int newQuantity = currentQuantity + t.getQuantity();
        if (newQuantity > t.getProduct().getQuantity()) {
            return false;
        }
        Item item= getItemByIDAndSize(t.getProduct().getProductID(), t.getSize());
        
        if (item != null) {
            item.setQuantity(item.getQuantity()+t.getQuantity());
        } else {
            listItems.add(t);
        }
        size = listItems.size();
        return true;
    }

    public void removeItem(int id, String sSize) {
        Item item = getItemByIDAndSize(id, sSize);
        if (item != null) {
            listItems.remove(item);
        }
        size = listItems.size();
    }

    public List<Product> getListProducts() {
        List<Product> list = new ArrayList<>();
        for (Item item : listItems) {
            list.add(item.getProduct());
        }
        return list;
    }
    public List<Item> getItemsByPID(int productID) {
        List<Item> items = new ArrayList<>();
        for (Item item : listItems) {
            if (item.getProduct().getProductID() == productID) {
                items.add(item);
            }
        }
        return items;
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
