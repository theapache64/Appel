package com.theah64.appel.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.theah64.appel.models.Contact;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by theapache64 on 21/7/16.
 * id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
 * phone VARCHAR(15) NOT NULL,
 * call_count_min_range INT NOT NULL DEFAULT 0,
 * call_count_max_range INT NOT NULL DEFAULT 0,
 * is_active INTEGER CHECK ( is_active IN (0,1)) NOT NULL DEFAULT 1,
 * created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
 */
public class Schedule extends BaseTable {

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_PHONE = "phone";
    private static final String COLUMN_CALL_COUNT_MIN_RANGE = "call_count_min_range";
    private static final String COLUMN_CALL_COUNT_MAX_RANGE = "call_count_max_range";
    private static final String COLUMN_IS_ACTIVE = "is_active";
    private static final String TABLE_NAME_SCHEDULE = "schedule";
    private static Schedule instance;

    private Schedule(Context context) {
        super(context);
    }

    public static Schedule getInstance(final Context context) {
        if (instance == null) {
            instance = new Schedule(context);
        }
        return instance;
    }

    /**
     * To add new contact into the  database.
     *
     * @param newContact Contact object with new contact details.
     * @return true on success, false otherwise.
     */
    public boolean add(final Contact newContact) {

        final ContentValues cv = new ContentValues(3);

        cv.put(COLUMN_PHONE, newContact.getPhone());
        cv.put(COLUMN_CALL_COUNT_MIN_RANGE, newContact.getMinCallCount());
        cv.put(COLUMN_CALL_COUNT_MAX_RANGE, newContact.getMaxCallCount());

        return this.getWritableDatabase().insert(TABLE_NAME_SCHEDULE, null, cv) != -1;
    }


    /**
     * To edit existing contact
     *
     * @param editedContact Edited Contact object.
     * @return true on success,false otherwise.
     */
    public boolean edit(final Contact editedContact) {

        final ContentValues cv = new ContentValues(4);

        cv.put(COLUMN_PHONE, editedContact.getPhone());
        cv.put(COLUMN_CALL_COUNT_MIN_RANGE, editedContact.getMinCallCount());
        cv.put(COLUMN_CALL_COUNT_MAX_RANGE, editedContact.getMaxCallCount());
        cv.put(COLUMN_IS_ACTIVE, editedContact.isActive());

        return this.getWritableDatabase().update(TABLE_NAME_SCHEDULE, cv, "id = ?", new String[]{editedContact.getId()}) == 1;
    }

    /**
     * To delete a specific contact;
     *
     * @param contactId id of the contact row.
     * @return true on success, false otherwise.
     */
    public boolean delete(final String contactId) {
        return this.getWritableDatabase().delete(TABLE_NAME_SCHEDULE, "id = ?", new String[]{contactId}) == 1;
    }

    /**
     * To return all contacts from the schedule table.
     *
     * @return List<Contact> if there's at least one contact, else null.
     */
    public List<Contact> getContacts() {

        List<Contact> contacts = null;

        final String query = "SELECT id,phone,call_count_min_range,call_count_max_range,is_active FROM schedule;";
        final Cursor contactCursor = this.getReadableDatabase().rawQuery(query, null);

        if (contactCursor.moveToFirst()) {

            contacts = new ArrayList<>(contactCursor.getCount());
            do {


                final String id = contactCursor.getString(contactCursor.getColumnIndex(COLUMN_ID));
                final String phone = contactCursor.getString(contactCursor.getColumnIndex(COLUMN_PHONE));
                final int minCallCount = contactCursor.getInt(contactCursor.getColumnIndex(COLUMN_CALL_COUNT_MIN_RANGE));
                final int maxCallCount = contactCursor.getInt(contactCursor.getColumnIndex(COLUMN_CALL_COUNT_MAX_RANGE));
                final boolean isActive = contactCursor.getInt(contactCursor.getColumnIndex(COLUMN_IS_ACTIVE)) == 1;

                contacts.add(new Contact(id, phone, minCallCount, maxCallCount, isActive));


            } while (contactCursor.moveToNext());

        }

        contactCursor.close();

        return contacts;
    }


}
