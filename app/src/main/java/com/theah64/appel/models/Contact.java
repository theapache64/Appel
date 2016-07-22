package com.theah64.appel.models;

/**
 * Created by theapache64 on 22/7/16.
 * id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
 * phone VARCHAR(15) NOT NULL,
 * call_count_min_range INT NOT NULL DEFAULT 0,
 * call_count_max_range INT NOT NULL DEFAULT 0,
 * is_active INTEGER CHECK ( is_active IN (0,1)) NOT NULL DEFAULT 1,
 * created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
 */
public class Contact {
    private final String id, phone;
    private final int minCallCount, maxCallCount;
    private final boolean isActive;

    public Contact(String id, String phone, int minCallCount, int maxCallCount, boolean isActive) {
        this.id = id;
        this.phone = phone;
        this.minCallCount = minCallCount;
        this.maxCallCount = maxCallCount;
        this.isActive = isActive;
    }

    public String getId() {
        return id;
    }

    public String getPhone() {
        return phone;
    }

    public int getMinCallCount() {
        return minCallCount;
    }

    public int getMaxCallCount() {
        return maxCallCount;
    }

    public boolean isActive() {
        return isActive;
    }
}
