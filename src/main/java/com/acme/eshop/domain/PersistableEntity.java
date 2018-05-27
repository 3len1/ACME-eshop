package com.acme.eshop.domain;


import com.acme.eshop.utils.DateConverter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;


/**
 * Created by Eleni on 5/7/2018.
 */
@MappedSuperclass
abstract public class PersistableEntity implements Persistable<Long> {

    @Id
    @Column(name = "ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @CreatedDate
    @Convert(converter = DateConverter.class)
    private Long createdDate;

    public PersistableEntity() {
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
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
