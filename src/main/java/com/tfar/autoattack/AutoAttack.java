package com.tfar.autoattack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod(modid = AutoAttack.MODID, name = AutoAttack.NAME, version = "@VERSION@",clientSideOnly = true)
public class AutoAttack {

    static final String MODID = "autoattack";
    static final String NAME = "Auto Attack";
    private static final Minecraft mc = Minecraft.getMinecraft();
    private static int tick = 0;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e){
        EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void mouse(InputEvent.KeyInputEvent e){
        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.player != null
                && mc.player.getCooledAttackStrength(0) < 1) {
            if (mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
                KeyBinding.setKeyBindState(Minecraft.getMinecraft().gameSettings.keyBindAttack.getKeyCode(), false);
            }
        }
    }

    @SubscribeEvent
    public void onRenderTick(TickEvent.ClientTickEvent event) {
        if (mc.gameSettings.keyBindAttack.isKeyDown() && mc.player != null
                && mc.player.getCooledAttackStrength(0) >= 1) {
            if (mc.objectMouseOver.typeOfHit == RayTraceResult.Type.ENTITY) {
                if (tick < 1)tick++;
                else {
                    mc.clickMouse();
                    tick = 0;
                }
            }
        }
    }
}


