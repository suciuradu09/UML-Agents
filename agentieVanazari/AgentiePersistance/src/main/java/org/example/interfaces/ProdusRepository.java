package org.example.interfaces;

import org.example.Produs;
import org.example.Repository;

public interface ProdusRepository extends Repository<Long, Produs> {
    Produs findByDenumire(String denumire);
}
