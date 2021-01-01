/*
 * Copyright (C) 2013-2021 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */
package com.b3dgs.lionengine.io;

import com.b3dgs.lionengine.LionEngineException;

/**
 * Represents the input device used to control entity with directional support and fire buttons.
 */
public interface InputDeviceControl extends InputDeviceDirectional
{
    /**
     * Set the fire button code.
     * 
     * @param index The button index (must not be <code>null</code>, must be positive).
     * @param code The fire button code (must not be <code>null</code>).
     * @throws LionEngineException If invalid argument.
     */
    void setFireButton(Integer index, Integer code);

    /**
     * Check if up button is enabled one time.
     * 
     * @return <code>true</code> if active, <code>false</code> else.
     */
    boolean isUpButtonOnce();

    /**
     * Check if down button is enabled one time.
     * 
     * @return <code>true</code> if active, <code>false</code> else.
     */
    boolean isDownButtonOnce();

    /**
     * Check if left button is enabled one time.
     * 
     * @return <code>true</code> if active, <code>false</code> else.
     */
    boolean isLeftButtonOnce();

    /**
     * Check if right button is enabled one time.
     * 
     * @return <code>true</code> if active, <code>false</code> else.
     */
    boolean isRightButtonOnce();

    /**
     * Check if fire button is enabled.
     * 
     * @param index The button index (must not be <code>null</code>, must be positive).
     * @return <code>true</code> if active, <code>false</code> else.
     */
    boolean isFireButton(Integer index);

    /**
     * Check if fire button is enabled one time.
     * 
     * @param index The button index (must not be <code>null</code>, must be positive).
     * @return <code>true</code> if active, <code>false</code> else.
     */
    boolean isFireButtonOnce(Integer index);
}
