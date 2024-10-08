package net.cebularz.droppedbuffs;

import com.mojang.logging.LogUtils;
import net.cebularz.droppedbuffs.entity.ModEntities;
import net.cebularz.droppedbuffs.entity.client.Absorption_Buff.Absorption_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Buff.Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Fire_Resistance_Buff.Fire_Resistance_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Haste_Buff.Haste_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Heal_Buff.Heal_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Invisibility_Buff.Invisibility_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Luck_Buff.Luck_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Meat_Buff.Meat_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Night_Vision_Buff.Night_Vision_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Resistance_Buff.Resistance_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Speed_Buff.Speed_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Strength_Buff.Strength_Buff_Renderer;
import net.cebularz.droppedbuffs.entity.client.Water_Breathing_Buff.Water_Breathing_Buff_Renderer;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(DroppedBuffs.MOD_ID)
public class DroppedBuffs
{
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "droppedbuffs";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MOD_ID);



    public DroppedBuffs()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::commonSetup);


        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
        ModEntities.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
        // Some common setup code
        LOGGER.info("HELLO FROM COMMON SETUP");




    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            EntityRenderers.register(ModEntities.MEAT_BUFF_ENTITY.get(), Meat_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.INVISIBILITY_BUFF_ENTITY.get(), Invisibility_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.RESISTANCE_BUFF_ENTITY.get(), Resistance_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.HEAL_BUFF_ENTITY.get(), Heal_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.STRENGTH_BUFF_ENTITY.get(), Strength_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.HASTE_BUFF_ENTITY.get(), Haste_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.SPEED_BUFF_ENTITY.get(), Speed_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.LUCK_BUFF_ENTITY.get(), Luck_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.ABSORPTION_BUFF_ENTITY.get(), Absorption_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.WATER_BREATHING_BUFF_ENTITY.get(), Water_Breathing_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.NIGHT_VISION_BUFF_ENTITY.get(), Night_Vision_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.FIRE_RESISTANCE_BUFF_ENTITY.get(), Fire_Resistance_Buff_Renderer::new);
            EntityRenderers.register(ModEntities.BUFF_ENTITY.get(), Buff_Renderer::new);
        }
    }
}
