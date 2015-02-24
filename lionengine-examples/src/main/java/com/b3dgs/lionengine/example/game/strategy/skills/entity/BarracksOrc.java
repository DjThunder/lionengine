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
package com.b3dgs.lionengine.example.game.strategy.skills.entity;

import com.b3dgs.lionengine.core.Media;
import com.b3dgs.lionengine.example.game.strategy.skills.skill.ProducePeon;
import com.b3dgs.lionengine.game.object.Services;
import com.b3dgs.lionengine.game.object.SetupSurface;

/**
 * Barracks building implementation. This building allows to create new grunt.
 * 
 * @author Pierre-Alexandre (contact@b3dgs.com)
 */
public final class BarracksOrc
        extends BuildingProducer
{
    /** Class media. */
    public static final Media MEDIA = Entity.getConfig(BarracksOrc.class);

    /**
     * Constructor.
     * 
     * @param setup The setup reference.
     */
    public BarracksOrc(SetupSurface setup)
    {
        super(setup);
    }

    /*
     * BuildingProducer
     */

    @Override
    public void prepareEntity(Services context)
    {
        super.prepareEntity(context);
        addSkill(context.get(FactoryEntity.class), 0, ProducePeon.MEDIA, 0);
    }
}
