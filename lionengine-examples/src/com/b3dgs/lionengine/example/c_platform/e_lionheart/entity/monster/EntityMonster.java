package com.b3dgs.lionengine.example.c_platform.e_lionheart.entity.monster;

import com.b3dgs.lionengine.anim.AnimState;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.Context;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.effect.FactoryEffect;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.effect.TypeEffect;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.entity.Entity;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.entity.EntityMover;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.entity.TypeEntity;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.entity.TypeEntityMovement;
import com.b3dgs.lionengine.example.c_platform.e_lionheart.entity.TypeEntityState;

/**
 * Monster base implementation.
 */
public class EntityMonster
        extends EntityMover
{
    /** Effect factory. */
    private final FactoryEffect factoryEffect;

    /**
     * Constructor.
     * 
     * @param context The context reference.
     * @param type The entity type.
     */
    public EntityMonster(Context context, TypeEntity type)
    {
        super(context, type);
        factoryEffect = context.factoryEffect;
        setFrameOffsets(sprite.getFrameWidth() / 2, -4);
    }

    @Override
    protected void updateActions()
    {
        final TypeEntityState state = status.getState();
        if (state == TypeEntityState.TURN)
        {
            if (getAnimState() == AnimState.FINISHED)
            {
                side = -side;
                mirror(side < 0);
                if (getMovementType() == TypeEntityMovement.HORIZONTAL)
                {
                    movement.getForce().setForce(movementSpeedMax * side, 0.0);
                    teleportX(getLocationIntX() + side);
                }
                else if (getMovementType() == TypeEntityMovement.VERTICAL)
                {
                    movement.getForce().setForce(0.0, movementSpeedMax * side);
                }
            }
            else
            {
                movement.getForce().setForce(0.0, 0.0);
            }
        }
        else if (state == TypeEntityState.WALK)
        {
            final int x = getLocationIntX();
            if (x > posMax)
            {
                teleportX(posMax);
            }
            if (x < posMin)
            {
                teleportX(posMin);
            }
        }
    }

    @Override
    public void hitBy(Entity entity)
    {
        kill();
    }

    @Override
    public void hitThat(Entity entity)
    {
        // Nothing to do
    }

    @Override
    protected void updateStates()
    {
        final double diffHorizontal = getDiffHorizontal();
        final int x = getLocationIntX();
        if (hasPatrol && (x == posMin || x == posMax))
        {
            status.setState(TypeEntityState.TURN);
        }
        else if (diffHorizontal != 0.0)
        {
            status.setState(TypeEntityState.WALK);
        }
        else
        {
            status.setState(TypeEntityState.IDLE);
        }
    }

    @Override
    protected void updateDead()
    {
        factoryEffect.startEffect(TypeEffect.EXPLODE, (int) dieLocation.getX() + 10, (int) dieLocation.getY() - 5);
        // TODO: Play explode sound
        destroy();
    }

    @Override
    protected void updateAnimations(double extrp)
    {
        // Nothing to do
    }
}
