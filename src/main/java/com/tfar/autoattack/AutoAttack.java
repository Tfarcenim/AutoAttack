package com.tfar.autoattack;

import net.minecraft.client.Minecraft;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod(modid = AutoAttack.MODID, name = AutoAttack.NAME, version = "@VERSION@",clientSideOnly = true)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = AutoAttack.MODID)
public class AutoAttack {

    static final String MODID = "autoattack";
    static final String NAME = "Auto Attack";

    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public static void onRenderTick(TickEvent.ClientTickEvent event) {
        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.player != null && mc.player.getCooledAttackStrength(0) >= 1){
            if (mc.objectMouseOver.entityHit != null)
                mc.playerController.attackEntity(mc.player, mc.objectMouseOver.entityHit);
        }
    }
}


