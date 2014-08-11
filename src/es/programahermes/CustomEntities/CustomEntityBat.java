package es.programahermes.CustomEntities;


import net.minecraft.server.v1_6_R3.EntityCow;
import net.minecraft.server.v1_6_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_6_R3.PathfinderGoalNearestAttackableTarget;
import net.minecraft.server.v1_6_R3.World;

public class CustomEntityBat extends EntityCow{

	public CustomEntityBat(World world) {
		super(world);
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityCow.class, 0, true));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityCow.class, 8.0F));
	}

}
