package t1708e.hellospring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import t1708e.hellospring.entity.Hero;
import t1708e.hellospring.entity.Universe;
import t1708e.hellospring.util.ObjectUtil;

import javax.persistence.*;
import java.util.Set;

public class UniverseDTO {

    private int id;
    private String name;
    private String description;
    private long createdAt;
    private long updatedAt;
    private long deletedAt;
    private int status;

    public UniverseDTO(Universe universe) {
        ObjectUtil.cloneObject(this, universe);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(long createdAt) {
        this.createdAt = createdAt;
    }

    public long getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(long updatedAt) {
        this.updatedAt = updatedAt;
    }

    public long getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(long deletedAt) {
        this.deletedAt = deletedAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
