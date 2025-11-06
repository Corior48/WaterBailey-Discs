package net.corior48.waterbaileydiscs.event;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.item.ModItems;
import net.corior48.waterbaileydiscs.util.ModTags;
import net.corior48.waterbaileydiscs.villager.ModVillagers;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.logging.Level;

@EventBusSubscriber(modid = WaterBaileyDiscs.MODID, bus = EventBusSubscriber.Bus.GAME)
public class ModEvents {



    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.MUSICIAN.value()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //Level 1 Trades
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_CAT, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_BLOCKS, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_MALL, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_13, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_CHIRP, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_FAR, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_MELLOHI, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_STAL, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_STRAD, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_WAIT, 1), 1, 10, 0.05f));
            trades.get(1).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 1),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(Items.MUSIC_DISC_WARD, 1), 1, 10, 0.05f));
            //Level 2 Trades
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.SUMMER_TROPICALA.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.VILLAGER_LULLABY.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.GANGNAM_STYLE.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.A_MOTHERS_LOVE.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.BLAZING_HEART.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.DISCO_EGGMANS_ANNOUCNEMENT.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.DONT_MINE_AT_NIGHT.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.EMBERFIRE.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.ENDLESS_ENCORE.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.FURINA_THEME.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.KYLES_MOM.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.MAJIN_FOREST_ESCAPE.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.NEVER_GONNA_GIVE_YOU_UP.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.RESULTS_AND_CHILL.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.STILL_ALIVE.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.WANT_YOU_GONE.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.WATER_ME_DOWN.get(), 1), 1, 35, 0.05f));
            trades.get(2).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.VS_SONIC_EXE_RERUN.get(), 1), 1, 35, 0.05f));
           //Level 3 Trades
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.JAKA_JAAN_ALTERNATIVE.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.JAKA_JAAN.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.INTERSTELLAR_DRIFT.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.IRRESISTIBLE_FORCE.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.KITCHEN_GUN.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.LA_VAGUELETTE.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.PLEASABT_TIPSINESS.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.TAR_TAR_TAGLIA.get(), 1), 1, 55, 0.05f));
            trades.get(3).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 3),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.ASGORE_RUNS_OVER_DESS.get(), 1), 1, 55, 0.05f));
            //Level 4 Trades
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.ANGEL_HARE_SIDE_A.get(), 1), 1, 85, 0.05f));
            trades.get(4).add((entity, randomSource) -> new MerchantOffer(
                    new ItemCost(Items.EMERALD, 5),
                    Optional.of(new ItemCost(ModItems.BLANK_DISC, 1)),
                    new ItemStack(ModItems.NOD_KRAI.get(), 1), 1, 85, 0.05f));
        }
    }
}
