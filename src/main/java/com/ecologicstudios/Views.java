package com.ecologicstudios;
import javafx.scene.layout.Pane;

/**
 * The Views interface defines a contract for graphical user interface (GUI) components.
 * Implementing classes should provide methods for initializing, structuring,
 * and handling user interactions in a Swing-based application.
 */
public interface Views {

    /**
     * Initializes the GUI components.
     */
    void initializeComponents();

    /**
     * Defines the layout of the GUI components.
     */
    void setLayout();

    /**
     * Adds components to the GUI layout.
     */
    void addComponents();

    /**
     * Handles user interactions and events.
     */
    void EventHandler();

   /**
     * Retrieves the main container of the view.
     *
     * @return The Pane representing the view.
     */
    Pane getRoot();
}