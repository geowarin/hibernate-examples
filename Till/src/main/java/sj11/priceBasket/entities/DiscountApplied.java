package sj11.priceBasket.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class DiscountApplied implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "productId1")
    private Product productId1;
    private Float productRate1;
    @ManyToOne
    @JoinColumn(name = "productId2")
    private Product productId2;
    private Float productRate2;
    @ManyToOne
    @JoinColumn(name = "productId3")
    private Product productId3;
    private Float productRate3;
    @ManyToOne
    @JoinColumn(name = "productId4")
    private Product productId4;
    private Float productRate4;
    @ManyToOne
    @JoinColumn(name = "productId5")
    private Product productId5;
    private Float productRate5;

    public DiscountApplied() {
    }

    public DiscountApplied(Product productId1, Float productRate1, Product productId2, Float productRate2, Product productId3, Float productRate3, Product productId4, Float productRate4, Product productId5, Float productRate5) {
        this.productId1 = productId1;
        this.productRate1 = productRate1;
        this.productId2 = productId2;
        this.productRate2 = productRate2;
        this.productId3 = productId3;
        this.productRate3 = productRate3;
        this.productId4 = productId4;
        this.productRate4 = productRate4;
        this.productId5 = productId5;
        this.productRate5 = productRate5;
    }

    public boolean areAllExistingProductRatePositive() {
        boolean result = true;
        if (productRate1 != null) {
            result = result && !(productRate1<0);
        }
        if (productRate2 != null) {
            result = result && !(productRate2<0);
        }
        if (productRate3 != null) {
            result = result && !(productRate3<0);
        }
        if (productRate4 != null) {
            result = result && !(productRate4<0);
        }
        if (productRate5 != null) {
            result = result && !(productRate5<0);
        }
        return result;
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

    public Float getProductRate1() {
        return productRate1;
    }

    public void setProductRate1(Float productRate1) {
        this.productRate1 = productRate1;
    }

    public Product getProductId2() {
        return productId2;
    }

    public void setProductId2(Product productId2) {
        this.productId2 = productId2;
    }

    public Float getProductRate2() {
        return productRate2;
    }

    public void setProductRate2(Float productRate2) {
        this.productRate2 = productRate2;
    }

    public Product getProductId3() {
        return productId3;
    }

    public void setProductId3(Product productId3) {
        this.productId3 = productId3;
    }

    public Float getProductRate3() {
        return productRate3;
    }

    public void setProductRate3(Float productRate3) {
        this.productRate3 = productRate3;
    }

    public Product getProductId4() {
        return productId4;
    }

    public void setProductId4(Product productId4) {
        this.productId4 = productId4;
    }

    public Float getProductRate4() {
        return productRate4;
    }

    public void setProductRate4(Float productRate4) {
        this.productRate4 = productRate4;
    }

    public Product getProductId5() {
        return productId5;
    }

    public void setProductId5(Product productId5) {
        this.productId5 = productId5;
    }

    public Float getProductRate5() {
        return productRate5;
    }

    public void setProductRate5(Float productRate5) {
        this.productRate5 = productRate5;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.productId1);
        hash = 97 * hash + Objects.hashCode(this.productRate1);
        hash = 97 * hash + Objects.hashCode(this.productId2);
        hash = 97 * hash + Objects.hashCode(this.productRate2);
        hash = 97 * hash + Objects.hashCode(this.productId3);
        hash = 97 * hash + Objects.hashCode(this.productRate3);
        hash = 97 * hash + Objects.hashCode(this.productId4);
        hash = 97 * hash + Objects.hashCode(this.productRate4);
        hash = 97 * hash + Objects.hashCode(this.productId5);
        hash = 97 * hash + Objects.hashCode(this.productRate5);
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
        final DiscountApplied other = (DiscountApplied) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.productId1, other.productId1)) {
            return false;
        }
        if (!Objects.equals(this.productRate1, other.productRate1)) {
            return false;
        }
        if (!Objects.equals(this.productId2, other.productId2)) {
            return false;
        }
        if (!Objects.equals(this.productRate2, other.productRate2)) {
            return false;
        }
        if (!Objects.equals(this.productId3, other.productId3)) {
            return false;
        }
        if (!Objects.equals(this.productRate3, other.productRate3)) {
            return false;
        }
        if (!Objects.equals(this.productId4, other.productId4)) {
            return false;
        }
        if (!Objects.equals(this.productRate4, other.productRate4)) {
            return false;
        }
        if (!Objects.equals(this.productId5, other.productId5)) {
            return false;
        }
        if (!Objects.equals(this.productRate5, other.productRate5)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DiscountApplied{" + "id=" + id + ", productId1=" + productId1 + ", productRate1=" + productRate1 + ", productId2=" + productId2 + ", productRate2=" + productRate2 + ", productId3=" + productId3 + ", productRate3=" + productRate3 + ", productId4=" + productId4 + ", productRate4=" + productRate4 + ", productId5=" + productId5 + ", productRate5=" + productRate5 + '}';
    }
}
