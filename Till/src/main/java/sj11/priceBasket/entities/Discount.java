package sj11.priceBasket.entities;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
public class Discount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @PrimaryKeyJoinColumn
    private DiscountToApply discountToApply;
    @OneToOne
    @PrimaryKeyJoinColumn
    private DiscountApplied discountApplied;

    public Discount() {
    }
    
    public Discount(DiscountToApply discountToApply, DiscountApplied discountApplied) {
        this.discountToApply = discountToApply;
        this.discountApplied = discountApplied;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DiscountToApply getDiscountToApply() {
        return discountToApply;
    }

    public void setDiscountToApply(DiscountToApply discountToApply) {
        this.discountToApply = discountToApply;
    }

    public DiscountApplied getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(DiscountApplied discountApplied) {
        this.discountApplied = discountApplied;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 19 * hash + Objects.hashCode(this.id);
        hash = 19 * hash + Objects.hashCode(this.discountToApply);
        hash = 19 * hash + Objects.hashCode(this.discountApplied);
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
        final Discount other = (Discount) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.discountToApply, other.discountToApply)) {
            return false;
        }
        if (!Objects.equals(this.discountApplied, other.discountApplied)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Discount{" + "id=" + id + ", discountToApply=" + discountToApply + ", discountApplied=" + discountApplied + '}';
    }
}
