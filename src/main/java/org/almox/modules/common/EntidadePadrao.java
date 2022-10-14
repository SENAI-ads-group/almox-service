package org.almox.modules.common;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.domain.Persistable;

import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
@MappedSuperclass
public interface EntidadePadrao extends Serializable, Persistable<UUID> {

    UUID getId();

    void setId(UUID id);

    @Override
    default boolean isNew() {
        return getId() == null;
    }
}
