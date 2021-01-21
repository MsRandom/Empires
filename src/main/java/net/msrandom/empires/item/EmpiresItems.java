package net.msrandom.empires.item;

import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.*;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.msrandom.empires.Empires;
import net.msrandom.empires.EmpiresRegistry;

@EmpiresRegistry(Item.class)
public class EmpiresItems {
    public static final ItemGroup GROUP = FabricItemGroupBuilder.build(new Identifier(Empires.MOD_ID, "tab"), () -> new ItemStack(EmpiresItems.STEEL_INGOT));
    public static final ToolMaterial STEEL_MATERIAL = new EmpiresToolMaterial(0, 1561, 0, 5, 0, () -> Ingredient.ofItems(EmpiresItems.STEEL_INGOT));
    public static final Item STEEL_INGOT = new Item(createSettings());
    public static final Item UNREFINED_STEEL = new Item(createSettings());
    public static final Item STEEL_SWORD = new SwordItem(STEEL_MATERIAL, 3, -2.4f, createSettings());
    public static final Item STEEL_LONGSWORD = new SteelLongSwordItem();

    public static Item.Settings createSettings() {
        return new FabricItemSettings().group(GROUP);
    }
}
