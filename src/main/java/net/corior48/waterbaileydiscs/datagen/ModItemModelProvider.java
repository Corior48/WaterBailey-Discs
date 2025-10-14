package net.corior48.waterbaileydiscs.datagen;

import net.corior48.waterbaileydiscs.WaterBaileyDiscs;
import net.corior48.waterbaileydiscs.item.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, WaterBaileyDiscs.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.TEST_DISC.get());
    }
}
