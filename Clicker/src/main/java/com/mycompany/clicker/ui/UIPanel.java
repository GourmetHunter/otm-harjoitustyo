/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.clicker.ui;

import com.mycompany.clicker.utility.Commons;
import com.mycompany.clicker.utility.Handler;
import com.mycompany.clicker.utility.Settings;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Node;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author Olli K. Kärki
 */
public class UIPanel extends UIElement {

    // variables ---------------------------------------------------------------
    private Text text;
    private Handler handler;

    // Constructor -------------------------------------------------------------
    /**
     * Constructor with text
     *
     * @param handler Handler
     * @param view Node
     * @param text String
     * @param x int
     * @param y int
     */
    public UIPanel(Handler handler, Node view, String text, int x, int y) {
        super(view, x, y);
        if (!Settings.notTesting) {
            text = null;
        }
        this.text = new Text(text);
        if (Settings.notTesting) {
            this.text.setFont(Commons.font);
        }
        this.handler = handler;
        //Text position
        this.text.setLayoutX(x + 2);
        this.text.setLayoutY(y + 12);
        this.text.setVisible(false);
    }

    /**
     * Constructor without a text.
     *
     * @param handler Handler
     * @param view Node
     * @param x int
     * @param y int
     */
    public UIPanel(Handler handler, Node view, int x, int y) {
        super(view, x, y);
        this.handler = handler;
        this.text = null;
    }

    // getters and setters -----------------------------------------------------
    /**
     * Returns text object of the UIPanel.
     *
     * @return Text
     */
    public Text getText() {
        return text;
    }

    /**
     * Sets string attached to the Text object of the UIPanel.
     *
     * @param text String
     */
    public void setText(String text) {
        if (!Settings.notTesting) {
            text = null;
        }
        if (this.text != null) {
            this.text.setText(text);
        }
    }

    /**
     * If the UIElement was initiated with a Text element, changes the font of
     * the text.
     *
     * @param font Font
     */
    public void setFont(Font font) {
        if (this.text != null) {
            this.text.setFont(font);
        }
    }

    // overridden methods ------------------------------------------------------
    /**
     * Sets active status of Nodes attached to the UIElement.
     *
     * @param value boolean
     */
    @Override
    public void setActive(boolean value) {
        super.setActiveBool(value);
        super.getView().setVisible(value);
        if (this.text != null) {
            this.text.setVisible(value);
        }
    }

    /**
     * UIPanel doesn't require updating.
     */
    @Override
    public void update() {
        //Has no active functionality
    }

    /**
     * Returns list of Nodes attached to the UIPanel.
     *
     * @return List of Nodes
     */
    @Override
    public List<Node> getNodes() {
        List<Node> r = new ArrayList<>();
        r.add(this.getView());
        if (this.text != null) {
            r.add(this.getText());
        }
        return r;
    }

}
