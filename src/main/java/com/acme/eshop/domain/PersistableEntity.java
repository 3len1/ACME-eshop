package com.acme.eshop.domain;

import java.util.UUID;
import javax.persistence.*;

import com.acme.eshop.utils.DateConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;



/**
 * Created by Eleni on 5/7/2018.
 */
@MappedSuperclass
abstract public class PersistableEntity implements Persistable<UUID> {

    @Id
    @Column(name = "id", nullable = false, length = 16)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    private UUID id;

    @CreatedDate
    @Convert(converter=DateConverter.class)
    private Long createdDate;

    public PersistableEntity() {
    }

    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }


    @Override
    @Transient
    @JsonIgnore
    public boolean isNew() {
        return id == null;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj instanceof PersistableEntity && !this.isNew() && !((PersistableEntity) obj).isNew()) {
            return ((PersistableEntity) obj).getId().equals(this.id);
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }
}
