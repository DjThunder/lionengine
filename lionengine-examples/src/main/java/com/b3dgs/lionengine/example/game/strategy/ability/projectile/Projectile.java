/*
 * Copyright (C) 2013-2015 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
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
package com.b3dgs.lionengine.example.game.strategy.ability.projectile;

import com.b3dgs.lionengine.core.Core;
import com.b3dgs.lionengine.core.Graphic;
import com.b3dgs.lionengine.core.Media;
import com.b3dgs.lionengine.drawable.Drawable;
import com.b3dgs.lionengine.drawable.SpriteTiled;
import com.b3dgs.lionengine.example.game.strategy.ability.entity.Entity;
import com.b3dgs.lionengine.example.game.strategy.ability.weapon.Weapon;
import com.b3dgs.lionengine.game.Camera;
import com.b3dgs.lionengine.game.object.Factory;
import com.b3dgs.lionengine.game.object.Services;
import com.b3dgs.lionengine.game.object.SetupSurface;
import com.b3dgs.lionengine.game.projectile.ProjectileGame;

/**
 * Projectile implementation base.
 * 
 * @author Pierre-Alexandre (contact@b3dgs.com)
 * @see com.b3dgs.lionengine.example.game.projectile
 */
public abstract class Projectile
        extends ProjectileGame<Entity, Weapon>
{
    /**
     * Get an entity configuration file.
     * 
     * @param type The config associated class.
     * @return The media config.
     */
    protected static Media getConfig(Class<? extends Projectile> type)
    {
        return Core.MEDIA.create(FactoryProjectile.PROJECTILE_DIR, type.getSimpleName() + "."
                + Factory.FILE_DATA_EXTENSION);
    }

    /** Surface. */
    private final SpriteTiled sprite;
    /** Frame. */
    private int frame;

    /**
     * Constructor.
     * 
     * @param setup The entity setup.
     */
    protected Projectile(SetupSurface setup)
    {
        super(setup);
        sprite = Drawable.loadSpriteTiled(setup.surface, getWidth(), getHeight());
        sprite.load(false);
    }

    /**
     * The projectile frame to set.
     * 
     * @param frame The frame.
     */
    public void setFrame(int frame)
    {
        this.frame = frame;
    }

    /*
     * ProjectileGame
     */

    @Override
    public void prepare(Services context)
    {
        // Nothing to do
    }

    @Override
    public void render(Graphic g, Camera camera)
    {
        sprite.render(g, frame, camera.getViewpointX(getLocationIntX()), camera.getViewpointY(getLocationIntY()));
    }

    @Override
    protected void updateMovement(double extrp, double vecX, double vecY)
    {
        // Apply a linear movement to the projectile with its vector
        moveLocation(extrp, vecX, vecY);
    }
}
