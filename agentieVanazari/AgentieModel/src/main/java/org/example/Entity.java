package org.example;

import java.io.Serial;
import java.io.Serializable;

public class Entity<ID> implements Serializable {
    @Serial
    private static final long serialVersionUID = 733112453532525L;

    /**
     Entity id
     */
    private ID id;

    /**
     * Getter method for Entity id
     */
    public ID getId() {
        return id;
    }

    /**
     * Setter method for Entity id
     */
    public void setId(ID id) {
        this.id = id;
    }
}
