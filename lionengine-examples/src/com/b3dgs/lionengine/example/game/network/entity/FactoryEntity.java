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
package com.b3dgs.lionengine.example.game.network.entity;

import com.b3dgs.lionengine.core.Media;
import com.b3dgs.lionengine.game.SetupSurfaceGame;
import com.b3dgs.lionengine.game.entity.FactoryEntityGame;

/**
 * Factory entity implementation. Any entity instantiation has to be made using a factory instance.
 */
final class FactoryEntity
        extends FactoryEntityGame<EntityType, SetupSurfaceGame, Entity>
{
    /** Main entity directory name. */
    private static final String ENTITY_DIR = "entities";
    /** Entity desired fps. */
    private final int desiredFps;
    /** Map reference. */
    private final Map map;

    /**
     * Standard constructor.
     * 
     * @param desiredFps The desired fps.
     * @param map The map reference.
     */
    FactoryEntity(int desiredFps, Map map)
    {
        super(EntityType.class, EntityType.values(), FactoryEntity.ENTITY_DIR);
        this.desiredFps = desiredFps;
        this.map = map;
        load();
    }

    /**
     * Create a new mario.
     * 
     * @param server <code>true</code> if is server, <code>false</code> if client.
     * @return The instance of mario.
     */
    public Mario createMario(boolean server)
    {
        return new Mario(getSetup(EntityType.MARIO), map, desiredFps, server);
    }

    /**
     * Create a new goomba.
     * 
     * @param server <code>true</code> if is server, <code>false</code> if client.
     * @return The instance of goomba.
     */
    public Goomba createGoomba(boolean server)
    {
        return new Goomba(getSetup(EntityType.GOOMBA), map, desiredFps, server);
    }

    /*
     * FactoryEntityGame
     */

    @Override
    public Entity createEntity(EntityType type)
    {
        switch (type)
        {
            case MARIO:
                return createMario(true);
            case GOOMBA:
                return createGoomba(true);
            default:
                return null;
        }
    }

    @Override
    protected SetupSurfaceGame createSetup(EntityType key, Media config)
    {
        return new SetupSurfaceGame(config);
    }
}
