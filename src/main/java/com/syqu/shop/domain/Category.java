package com.syqu.shop.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;


@Entity
@Data
@Table(name = "category")
public class Category {
    @Column(name = "id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "cat_name")
    @NotNull
    @NotEmpty
    private String categoryName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private Set<Product> books = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Category category = (Category) o;

        return id == category.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addBook(Product book){
        books.add(book);
    }
    public void removeBook(Product book){
        books.remove(book);
    }
    public Set<Product> getBooks() {
        return books;
    }
    public void setBooks(Set<Product> books) {
        this.books = books;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getCategoryName() {
        return categoryName;
    }
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
    public Category() {
    }
    public Category(String categoryName) {
        this.categoryName = categoryName;
    }

}
