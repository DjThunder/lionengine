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
package com.b3dgs.lionengine.example.game.strategy.skills.skill;

import com.b3dgs.lionengine.core.Media;
import com.b3dgs.lionengine.example.game.strategy.skills.entity.BuildingProducer;
import com.b3dgs.lionengine.example.game.strategy.skills.entity.FactoryProduction;
import com.b3dgs.lionengine.example.game.strategy.skills.entity.Peon;
import com.b3dgs.lionengine.example.game.strategy.skills.entity.ProducibleEntity;
import com.b3dgs.lionengine.game.object.Services;
import com.b3dgs.lionengine.game.strategy.ControlPanelModel;
import com.b3dgs.lionengine.game.strategy.CursorStrategy;

/**
 * Produce peon implementation.
 * 
 * @author Pierre-Alexandre (contact@b3dgs.com)
 */
public final class ProducePeon
        extends Skill
{
    /** Class media. */
    public static final Media MEDIA = Skill.getConfig(ProducePeon.class);

    /** Production factory. */
    private FactoryProduction factoryProduction;

    /**
     * Constructor.
     * 
     * @param setup The setup skill reference.
     */
    public ProducePeon(SetupSkill setup)
    {
        super(setup);
        setOrder(false);
    }

    /*
     * Skill
     */

    @Override
    public void prepare(Services context)
    {
        super.prepare(context);
        factoryProduction = context.get(FactoryProduction.class);
    }

    @Override
    public void action(ControlPanelModel<?> panel, CursorStrategy cursor)
    {
        if (owner instanceof BuildingProducer)
        {
            final ProducibleEntity producible = factoryProduction.create(Peon.MEDIA);
            ((BuildingProducer) owner).addToProductionQueue(producible);
        }
    }
}
