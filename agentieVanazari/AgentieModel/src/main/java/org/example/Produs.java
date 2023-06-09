package org.example;

import javax.persistence.*;
import java.util.Objects;

@javax.persistence.Entity
@Table(name = "produse")
public class Produs extends Entity<Long> {

    private Long id;

    private String denumire;

    private Double pret;

    private Integer cantitate;

    public Produs() {
    }

    public Produs(Long id, String denumire, Double pret, Integer cantitate) {
        this.id = id;
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    public Produs(String denumire, Double pret, Integer cantitate) {
        this.denumire = denumire;
        this.pret = pret;
        this.cantitate = cantitate;
    }

    @Id
    @GeneratedValue
    @Column(name = "id")
    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "denumire")
    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    @Basic
    @Column(name = "pret")
    public Double getPret() {
        return pret;
    }

    public void setPret(Double pret) {
        this.pret = pret;
    }

    @Basic
    @Column(name = "cantitate")
    public Integer getCantitate() {
        return cantitate;
    }

    public void setCantitate(Integer cantitate) {
        this.cantitate = cantitate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Produs produs = (Produs) o;
        return Objects.equals(id, produs.id) && Objects.equals(denumire, produs.denumire) && Objects.equals(pret, produs.pret) && Objects.equals(cantitate, produs.cantitate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, denumire, pret, cantitate);
    }

    @Override
    public String toString() {
        return  "Denumire='" + denumire + '\'' +
                " Pret=" + pret +
                " Cantitate=" + cantitate;
    }
}
