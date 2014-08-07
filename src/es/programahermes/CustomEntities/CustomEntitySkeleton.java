package es.programahermes.CustomEntities;


import net.minecraft.server.v1_6_R3.EntityLiving;
import net.minecraft.server.v1_6_R3.EntitySkeleton;

public class CustomEntitySkeleton extends EntitySkeleton{

	public CustomEntitySkeleton(net.minecraft.server.v1_6_R3.World world) {
		super(world);
		// TODO Auto-generated constructor stub
	}
	
	@Override
    public void a(EntityLiving entityliving, float f){
        for (int i = 0; i < 2; ++i){
            super.a(entityliving, f);
        }
    }


	
	
}
