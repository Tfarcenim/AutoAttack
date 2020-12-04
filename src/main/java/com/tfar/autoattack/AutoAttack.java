package com.tfar.autoattack;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.util.math.EntityRayTraceResult;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@Mod(AutoAttack.MODID)
@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = AutoAttack.MODID)
public class AutoAttack {

    static final String MODID = "autoattack";
    private static final Minecraft mc = Minecraft.getInstance();

    @SubscribeEvent
    public static void tick(TickEvent.ClientTickEvent event) {

        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.player != null
                && mc.player.getCooledAttackStrength(0) >= 1) {
            if (mc.objectMouseOver instanceof EntityRayTraceResult) {
                Entity entity = ((EntityRayTraceResult) mc.objectMouseOver).getEntity();
                if (entity instanceof TameableEntity && ((TameableEntity) entity).getOwnerId() != null) return;
                mc.clickMouse();
            }
        }
    }
}


