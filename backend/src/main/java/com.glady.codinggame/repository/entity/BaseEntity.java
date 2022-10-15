package com.glady.codinggame.repository.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@ToString
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseEntity {

    @NotNull
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    protected Long id;

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;

        if (o == null || this.getClass() != o.getClass())
            return false;

        BaseEntity entity = (BaseEntity) o;

        return super.equals(entity)
                && Objects.equals(this.id, entity.id);
    }

}
