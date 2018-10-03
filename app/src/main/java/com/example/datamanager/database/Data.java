package com.example.datamanager.database;

import android.databinding.Bindable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

@Entity(nameInDb = "data")
public class Data {

    @Id(autoincrement = true)
    private Long id;

    @Property(nameInDb = "title")
    @Bindable
    private String title;

    @Property(nameInDb = "text")
    private String text;

    @Property(nameInDb = "pin_active")
    private Boolean pinActive;

    @Generated(hash = 410877884)
    public Data(Long id, String title, String text, Boolean pinActive) {
        this.id = id;
        this.title = title;
        this.text = text;
        this.pinActive = pinActive;
    }

    @Generated(hash = 2135787902)
    public Data() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Boolean getPinActive() {
        return this.pinActive;
    }

    public void setPinActive(Boolean pinActive) {
        this.pinActive = pinActive;
    }

}
