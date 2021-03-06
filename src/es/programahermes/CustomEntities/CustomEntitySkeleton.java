package es.programahermes.CustomEntities;


import net.minecraft.server.v1_6_R3.EntityCow;
import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntitySkeleton;
import net.minecraft.server.v1_6_R3.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_6_R3.PathfinderGoalNearestAttackableTarget;

public class CustomEntitySkeleton extends EntitySkeleton{

	public CustomEntitySkeleton(net.minecraft.server.v1_6_R3.World world) {
		super(world);
        this.targetSelector.a(2, new PathfinderGoalNearestAttackableTarget(this, EntityCow.class, 0, true));
        this.goalSelector.a(6, new PathfinderGoalLookAtPlayer(this, EntityCow.class, 8.0F));
	}
	
	@Override
    public void a(EntityLiving entityliving, float f){
        for (int i = 0; i < 2; ++i){
            super.a(entityliving, f);
        }
    }


	
	
}
