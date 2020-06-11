package com.tfar.autoattack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;

import static net.minecraftforge.common.MinecraftForge.EVENT_BUS;

@Mod(modid = AutoAttack.MODID, name = AutoAttack.NAME, version = "@VERSION@", clientSideOnly = true)
@Mod.EventBusSubscriber(Side.CLIENT)
public class AutoAttack {

	static final String MODID = "autoattack";
	static final String NAME = "Auto Attack";
	private static final Minecraft mc = Minecraft.getMinecraft();

	@SubscribeEvent
	public static void tick(TickEvent.ClientTickEvent e) {
		if (e.phase == TickEvent.Phase.END && mc.player != null && mc.player.getCooledAttackStrength(0) >= 1
						&& mc.gameSettings.keyBindAttack.isKeyDown()) {
			if (mc.objectMouseOver.entityHit != null) {
				Entity entity = mc.objectMouseOver.entityHit;
				mc.playerController.attackEntity(mc.player, entity);
			}
		}
	}
}