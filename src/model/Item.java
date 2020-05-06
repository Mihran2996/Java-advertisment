package model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Item implements Comparable<Item>, Serializable {
    private long id;
    private String title;
    private String text;
    private double price;
    private User user;
    private Category category;
    private Date createDate;
    private SimpleDateFormat sdf = new SimpleDateFormat(" dd-MM-yyyy hh:mm:ss");


    public Item(String title, String text, double price, User user, Category category, Date createDate) {
        this.title = title;
        this.text = text;
        this.price = price;
        this.user = user;
        this.category = category;
        this.createDate = createDate;
    }

    public Item() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return id == item.id &&
                Double.compare(item.price, price) == 0 &&
                Objects.equals(title, item.title) &&
                Objects.equals(text, item.text) &&
                Objects.equals(user, item.user) &&
                category == item.category &&
                Objects.equals(createDate, item.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, price, user, category, createDate);
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                ", price=" + price +
                ", user=" + user +
                ", category=" + category +
                ", createDate=" + sdf.format(createDate) +
                '}';
    }

    @Override
    public int compareTo(Item o) {
        return title.compareTo(o.getTitle());
    }
}
