package t1708e.hellospring.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import t1708e.hellospring.entity.Hero;
import t1708e.hellospring.entity.Universe;
import t1708e.hellospring.util.DateTimeUtil;
import t1708e.hellospring.util.ObjectUtil;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HeroDTO {

    private int id;
    private String name;
    private String description;
    private String history;
    private String thumbnail;
    private String universeName;

    private String createdAt;
    private String updatedAt;
    private String deletedAt;
    private String status;

    public HeroDTO() {
    }

    public HeroDTO(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public HeroDTO(Hero hero) {
        this.description = "";
        this.history = "";
        this.thumbnail = "";
        ObjectUtil.cloneObject(this, hero);
        this.universeName = hero.getUniverse().getName();
        this.createdAt = DateTimeUtil.formatDateFromLong(hero.getCreatedAt());
        this.updatedAt = DateTimeUtil.formatDateFromLong(hero.getUpdatedAt());
        this.deletedAt = DateTimeUtil.formatDateFromLong(hero.getDeletedAt());
        this.status = hero.getStatus() == 1 ? "Active" : "Deactive";
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

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getUniverseName() {
        return universeName;
    }

    public void setUniverseName(String universeName) {
        this.universeName = universeName;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(String deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
