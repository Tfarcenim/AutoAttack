package com.tfar.autoattack;

import net.minecraft.client.Minecraft;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.config.Config;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;


@Mod(modid = AutoAttack.MODID, name = AutoAttack.NAME, version = "@VERSION@",clientSideOnly = true)
@Mod.EventBusSubscriber(value = Side.CLIENT, modid = AutoAttack.MODID)
@Config(modid = AutoAttack.MODID)
public class AutoAttack {

    @Config.Ignore
    static final String MODID = "autoattack";
    @Config.Ignore
    static final String NAME = "Auto Attack";
    @Config.Ignore
    private static final Minecraft mc = Minecraft.getMinecraft();
    @Config.Ignore
    private static int tick = 0;

    @Config.Name("attack_empty")
    public static boolean attack_empty = false;

    @SubscribeEvent
    public static void onRenderTick(TickEvent.ClientTickEvent event) {
        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.player != null
                && mc.player.getCooledAttackStrength(0) >= 1){
            if (mc.objectMouseOver.entityHit == null && !attack_empty){
                tick = 0; return;
            }
            if (mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY || attack_empty) {
                if (tick < 1)tick++;
                else {
                    mc.clickMouse();
                    tick = 0;
                }
            }
        }
    }
}


