package com.almox.modules.common;

import javax.persistence.Transient;
import java.io.Serializable;

public abstract class EntidadePadrao implements Serializable {

    private static final int PRIME = 31;

    public abstract Long getId();

    public abstract void setId(Long id);

    @Override
    public int hashCode() {
            return PRIME + ((getId() == null) ? 0 : getId().hashCode());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        final EntidadePadrao OTHER = (EntidadePadrao) obj;
        return getId() != null && getId().equals(OTHER.getId()) || this == obj;
    }

    @Transient
    public boolean estaPersistida() {
        return getId() != null && getId() > 0L;
    }
}
