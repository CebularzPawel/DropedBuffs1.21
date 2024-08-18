package net.cebularz.droppedbuffs.entity.custom;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class Fire_Resistance_Buff_Entity extends Basic_Buff_Entity {


    public Fire_Resistance_Buff_Entity(EntityType<?> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
        this.color=0xeb6410;
    }

    @Override
    protected void effect(Player player) {
        MobEffectInstance effect = new MobEffectInstance(MobEffects.FIRE_RESISTANCE,30*20,0);
        player.addEffect(effect);

    }
}
