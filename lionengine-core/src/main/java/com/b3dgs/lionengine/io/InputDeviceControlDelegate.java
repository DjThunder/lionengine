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

import java.util.function.Supplier;

/**
 * Delegate device.
 */
public final class InputDeviceControlDelegate implements InputDeviceControl
{
    /** The provider reference. */
    private final Supplier<InputDeviceControl> provider;

    /**
     * Create delegate.
     * 
     * @param provider The provider reference.
     */
    public InputDeviceControlDelegate(Supplier<InputDeviceControl> provider)
    {
        super();

        this.provider = provider;
    }

    /**
     * Get current control.
     * 
     * @return The current control.
     */
    private InputDeviceControl get()
    {
        return provider.get();
    }

    @Override
    public void setHorizontalControlPositive(Integer code)
    {
        get().setHorizontalControlPositive(code);
    }

    @Override
    public void setHorizontalControlNegative(Integer code)
    {
        get().setHorizontalControlNegative(code);
    }

    @Override
    public void setVerticalControlPositive(Integer code)
    {
        get().setVerticalControlPositive(code);
    }

    @Override
    public void setVerticalControlNegative(Integer code)
    {
        get().setVerticalControlNegative(code);
    }

    @Override
    public Integer getHorizontalControlPositive()
    {
        return get().getHorizontalControlPositive();
    }

    @Override
    public Integer getHorizontalControlNegative()
    {
        return get().getHorizontalControlNegative();
    }

    @Override
    public Integer getVerticalControlPositive()
    {
        return get().getVerticalControlPositive();
    }

    @Override
    public Integer getVerticalControlNegative()
    {
        return get().getVerticalControlNegative();
    }

    @Override
    public double getHorizontalDirection()
    {
        return get().getHorizontalDirection();
    }

    @Override
    public double getVerticalDirection()
    {
        return get().getVerticalDirection();
    }

    @Override
    public void setFireButton(Integer index, Integer code)
    {
        get().setFireButton(index, code);
    }

    @Override
    public boolean isUpButtonOnce()
    {
        return get().isUpButtonOnce();
    }

    @Override
    public boolean isDownButtonOnce()
    {
        return get().isDownButtonOnce();
    }

    @Override
    public boolean isLeftButtonOnce()
    {
        return get().isLeftButtonOnce();
    }

    @Override
    public boolean isRightButtonOnce()
    {
        return get().isRightButtonOnce();
    }

    @Override
    public boolean isFireButton(Integer index)
    {
        return get().isFireButton(index);
    }

    @Override
    public boolean isFireButtonOnce(Integer index)
    {
        return get().isFireButtonOnce(index);
    }
}
