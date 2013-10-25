/*
 * Copyright (C) 2013 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package com.b3dgs.lionengine.game.rts;

import java.util.HashSet;
import java.util.Set;

import com.b3dgs.lionengine.ColorRgba;
import com.b3dgs.lionengine.Graphic;
import com.b3dgs.lionengine.Keyboard;
import com.b3dgs.lionengine.Rectangle;
import com.b3dgs.lionengine.core.UtilityMath;
import com.b3dgs.lionengine.game.CameraGame;
import com.b3dgs.lionengine.game.rts.entity.EntityRts;

/**
 * This class represents the control panel (HUD), which will contain selected entities, actions, and many other
 * informations.
 * 
 * @param <E> Entity type used.
 * @author Pierre-Alexandre (contact@b3dgs.com)
 */
public abstract class ControlPanelModel<E extends EntityRts>
        implements ControlPanelListener
{
    /** Player owning the control panel. */
    protected PlayerRts player;
    /** List of listeners. */
    private final Set<ControlPanelListener> listeners;
    /** Selection area. */
    private final Rectangle selectionArea;
    /** Area outside panel (where the map is displayed). */
    private Rectangle outsidePanel;
    /** Mouse click number to start a selection. */
    private int clickSelection;
    /** Handler clicked state. */
    private boolean clicked;
    /** Handler clicked flag. */
    private boolean clickedFlag;
    /** Handler selecting flag. */
    private boolean selecting;
    /** Handler selected flag. */
    private boolean selected;
    /** Handler ordered flag. */
    private boolean ordered;
    /** Mouse location x when started click selection. */
    private int sy;
    /** Mouse location y when started click selection. */
    private int sx;
    /** Current selection x (stored in selectionArea when selection is done). */
    private int selectX;
    /** Current selection y (stored in selectionArea when selection is done). */
    private int selectY;
    /** Current selection width (stored in selectionArea when selection is done). */
    private int selectW;
    /** Current selection height (stored in selectionArea when selection is done). */
    private int selectH;
    /** Cursor selection color. */
    private ColorRgba colorSelection;

    /**
     * Create a new control panel.
     */
    public ControlPanelModel()
    {
        listeners = new HashSet<>(1);
        selectionArea = UtilityMath.createRectangle();
        outsidePanel = null;
        clicked = false;
        clickedFlag = false;
        selecting = false;
        selected = false;
        ordered = false;
        sx = 0;
        sy = 0;
        selectX = 0;
        selectY = 0;
        selectW = 0;
        selectH = 0;
        colorSelection = ColorRgba.GRAY;
    }

    /**
     * Called when the selection has been updated by the handler.
     * 
     * @param selection The selected entities.
     */
    public abstract void notifyUpdatedSelection(Set<E> selection);

    /**
     * Called when an order started.
     */
    protected abstract void onStartOrder();

    /**
     * Called when an order terminated.
     */
    protected abstract void onTerminateOrder();

    /**
     * Add a control panel listener.
     * 
     * @param listener The listener.
     */
    public void addListener(ControlPanelListener listener)
    {
        listeners.add(listener);
    }

    /**
     * Add a control panel listener.
     * 
     * @param listener The listener.
     */
    public void removeListener(ControlPanelListener listener)
    {
        listeners.add(listener);
    }

    /**
     * Update panel routine.
     * 
     * @param extrp The extrapolation value.
     * @param camera The camera viewpoint.
     * @param cursor The cursor reference (used for selection).
     * @param keyboard The keyboard reference.
     */
    public void update(double extrp, CameraRts camera, CursorRts cursor, Keyboard keyboard)
    {
        // Restore clicked state
        if (cursor.getClick() == 0)
        {
            clicked = false;
        }

        // Cursor selection
        if (!ordered)
        {
            updateCursorSelection(cursor, camera);

            // Clear selection if done
            if (selected)
            {
                notifySelectionDone(selectionArea);
                selectionArea.set(-1, -1, 0, 0);
                selected = false;
            }
        }
        else
        {
            if (!clicked && cursor.getClick() > 0)
            {
                clicked = true;
                ordered = false;
                onTerminateOrder();
            }
        }

        // Apply clicked state if necessary
        if (clickedFlag)
        {
            clicked = true;
            clickedFlag = false;
        }
    }

    /**
     * Render cursor selection routine. This function will draw the current active selection on screen, depending of its
     * localisation, and using the camera point of view (location on map).
     * 
     * @param g The graphic output.
     * @param camera The camera reference.
     */
    public void renderCursorSelection(Graphic g, CameraRts camera)
    {
        if (selecting)
        {
            final int x = camera.getViewpointX((int) selectionArea.getX());
            final int y = camera.getViewpointY((int) (selectionArea.getY() + selectionArea.getHeight()));
            final int w = (int) selectionArea.getWidth();
            final int h = (int) selectionArea.getHeight();
            g.setColor(colorSelection);
            g.drawRect(x, y, w, h, false);
        }
    }

    /**
     * Reset order state (order failed).
     */
    public void resetOrder()
    {
        ordered = false;
        clicked = false;
    }

    /**
     * Set the mouse click selection value.
     * 
     * @param click The click id.
     */
    public void setClickSelection(int click)
    {
        clickSelection = click;
    }

    /**
     * Set clickable area on map (not on panel).
     * 
     * @param area The area representing the clickable area.
     */
    public void setClickableArea(Rectangle area)
    {
        outsidePanel = area;
    }

    /**
     * Set clickable area on map (not on panel), depending of the camera view.
     * 
     * @param camera The camera reference.
     */
    public void setClickableArea(CameraGame camera)
    {
        outsidePanel = UtilityMath.createRectangle();
        outsidePanel.set(camera.getViewX(), camera.getViewY(), camera.getViewWidth(), camera.getViewHeight());
    }

    /**
     * Set player (player owning this panel).
     * 
     * @param player The player reference.
     */
    public void setPlayer(PlayerRts player)
    {
        this.player = player;
    }

    /**
     * Set the selection color.
     * 
     * @param color The selection color.
     */
    public void setSelectionColor(ColorRgba color)
    {
        colorSelection = color;
    }

    /**
     * Get clickable area on map (out panel).
     * 
     * @return The clickable map area from panel.
     */
    public Rectangle getArea()
    {
        return outsidePanel;
    }

    /**
     * Check if cursor can click on panel.
     * 
     * @param cursor The cursor reference.
     * @return <code>true</code> if can click on panel, <code>false</code> else.
     */
    public boolean canClick(CursorRts cursor)
    {
        return !outsidePanel.contains(cursor.getScreenX(), cursor.getScreenY());
    }

    /**
     * Set the ordered state (when an action skill is chosen).
     */
    public void ordered()
    {
        ordered = true;
        clicked = true;
        onStartOrder();
    }

    /**
     * Check if panel is in ordered mode (waiting for a second click).
     * 
     * @return <code>true</code> if ordering, <code>false</code> else.
     */
    public boolean isOrdered()
    {
        return ordered;
    }

    /**
     * Check if panel is in selection mode.
     * 
     * @return <code>true</code> if selecting, <code>false</code> else.
     */
    public boolean isSelecting()
    {
        return selecting;
    }

    /**
     * Function handling cursor selection (preparing area transposed on the map).
     * 
     * @param cursor The cursor reference.
     * @param camera The camera reference.
     */
    protected void updateCursorSelection(CursorRts cursor, CameraRts camera)
    {
        // Start selection on click, and reset last selection
        if (clickSelection == cursor.getClick())
        {
            final boolean canClick = canClick(cursor);
            clickedFlag = true;
            if (!selecting && !canClick && !ordered && !clicked)
            {
                selecting = true;
                sx = cursor.getLocationX();
                sy = cursor.getLocationY();
                computeSelection(cursor, camera);
                notifySelectionStarted(selectionArea);
            }
        }
        else
        {
            if (selecting)
            {
                selected = true;
            }
            selecting = false;
        }

        // Update selection while selecting (mouse pressed, stop on releasing)
        if (selecting)
        {
            computeSelection(cursor, camera);
        }
    }

    /**
     * Perform the width selection by considering the click point and current location.
     * 
     * @param cursor The cursor reference.
     * @param camera The camera reference.
     * @param sx The starting horizontal click.
     * @param sy The starting vertical click.
     * @return The selection width.
     */
    protected int computeSelectionWidth(CursorRts cursor, CameraRts camera, int sx, int sy)
    {
        return UtilityMath.fixBetween(cursor.getLocationX() - sx, Integer.MIN_VALUE,
                camera.getViewX() + camera.getLocationIntX() - sx + camera.getViewWidth());
    }

    /**
     * Perform the height selection by considering the click point and current location.
     * 
     * @param cursor The cursor reference.
     * @param camera The camera reference.
     * @param sx The starting horizontal click.
     * @param sy The starting vertical click.
     * @return The selection height.
     */
    protected int computeSelectionHeight(CursorRts cursor, CameraRts camera, int sx, int sy)
    {
        return UtilityMath.fixBetween(cursor.getLocationY() - sy, Integer.MIN_VALUE,
                camera.getViewY() + camera.getLocationIntY() + camera.getViewHeight() + sy);
    }

    /**
     * Compute the selection from cursor location.
     * 
     * @param cursor The cursor reference.
     * @param camera The camera reference.
     */
    private void computeSelection(CursorRts cursor, CameraRts camera)
    {
        selectX = sx;
        selectY = sy;
        selectW = computeSelectionWidth(cursor, camera, sx, sy);
        selectH = computeSelectionHeight(cursor, camera, sx, sy);
        // This will avoid negative size
        if (selectW < 0)
        {
            selectX += selectW;
            selectW = -selectW;
        }
        if (selectH < 0)
        {
            selectY += selectH;
            selectH = -selectH;
        }
        if (selectX < 0)
        {
            selectW += selectX;
            selectX = 0;
        }
        if (selectY < 0)
        {
            selectH += selectY;
            selectY = 0;
        }
        selectionArea.set(selectX, selectY, selectW, selectH);
    }

    /*
     * Control panel listener
     */

    @Override
    public void notifySelectionStarted(Rectangle selection)
    {
        for (final ControlPanelListener listener : listeners)
        {
            listener.notifySelectionStarted(selection);
        }
    }

    @Override
    public void notifySelectionDone(Rectangle selection)
    {
        for (final ControlPanelListener listener : listeners)
        {
            listener.notifySelectionDone(selection);
        }
    }
}
