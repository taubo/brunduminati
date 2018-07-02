package com.example.android.textswitcher;

/*
 * using this class you can:
 * - add a new argument
 * - delete an argument and so on
 */

import java.util.ArrayList;

public interface ArgumentManager {
    void add(Argument argument);

    void delete(int index);

    ArrayList<Argument> getAll();
}
