package org.almox.modules.common;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;
import java.util.UUID;

public interface EntidadePadrao extends Serializable, Persistable<UUID> {

    UUID getId();

    void setId(UUID id);

    @Override
    default boolean isNew() {
        return getId() == null;
    }
}
