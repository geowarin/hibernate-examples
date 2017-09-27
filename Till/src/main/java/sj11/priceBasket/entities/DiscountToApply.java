package sj11.priceBasket.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DiscountToApply implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId1")
    private Product productId1;
    @ManyToOne
    @JoinColumn(name = "productId2")
    private Product productId2;
    @ManyToOne
    @JoinColumn(name = "productId3")
    private Product productId3;
    @ManyToOne
    @JoinColumn(name = "productId4")
    private Product productId4;
    @ManyToOne
    @JoinColumn(name = "productId5")
    private Product productId5;

    public DiscountToApply() {
    }

    public DiscountToApply(Product productId1, Product productId2, Product productId3, Product productId4, Product productId5) {
        this.productId1 = productId1;
        this.productId2 = productId2;
        this.productId3 = productId3;
        this.productId4 = productId4;
        this.productId5 = productId5;
    }

    public List<Product> getProducts() {
        List<Product> productList = new ArrayList<>();
        if (productId1 != null) {
            productList.add(productId1);
        }
        if (productId2 != null) {
            productList.add(productId2);
        }
        if (productId3 != null) {
            productList.add(productId3);
        }
        if (productId4 != null) {
            productList.add(productId4);
        }
        if (productId5 != null) {
            productList.add(productId5);
        }
        return productList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProductId1() {
        return productId1;
    }

    public void setProductId1(Product productId1) {
        this.productId1 = productId1;
    }

    public Product getProductId2() {
        return productId2;
    }

    public void setProductId2(Product productId2) {
        this.productId2 = productId2;
    }

    public Product getProductId3() {
        return productId3;
    }

    public void setProductId3(Product productId3) {
        this.productId3 = productId3;
    }

    public Product getProductId4() {
        return productId4;
    }

    public void setProductId4(Product productId4) {
        this.productId4 = productId4;
    }

    public Product getProductId5() {
        return productId5;
    }

    public void setProductId5(Product productId5) {
        this.productId5 = productId5;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.productId1);
        hash = 29 * hash + Objects.hashCode(this.productId2);
        hash = 29 * hash + Objects.hashCode(this.productId3);
        hash = 29 * hash + Objects.hashCode(this.productId4);
        hash = 29 * hash + Objects.hashCode(this.productId5);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DiscountToApply other = (DiscountToApply) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.productId1, other.productId1)) {
            return false;
        }
        if (!Objects.equals(this.productId2, other.productId2)) {
            return false;
        }
        if (!Objects.equals(this.productId3, other.productId3)) {
            return false;
        }
        if (!Objects.equals(this.productId4, other.productId4)) {
            return false;
        }
        if (!Objects.equals(this.productId5, other.productId5)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DiscountToApply{" + "id=" + id + ", productId1=" + productId1 + ", productId2=" + productId2 + ", productId3=" + productId3 + ", productId4=" + productId4 + ", productId5=" + productId5 + '}';
    }
}
