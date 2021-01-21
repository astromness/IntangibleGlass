package astromness.intangibleglass.intangiglass;

import astromness.intangibleglass.setup.Registration;
import net.minecraft.block.Block;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.event.world.NoteBlockEvent;
import net.minecraftforge.fml.RegistryObject;

import static astromness.intangibleglass.setup.Registration.BLOCKS;
import static astromness.intangibleglass.setup.Registration.ITEMS;

public class IntangibleModule {
    public static final RegistryObject<Block> INTANGIBLE_GLASS_BLOCK = BLOCKS.register("intangible_glass", () -> new IntangibleGlass(EntityEnum.PLAYER));
    public static final RegistryObject<Item> INTANGIBLE_GLASS_ITEM = ITEMS.register("intangible_glass", () -> new BlockItem(INTANGIBLE_GLASS_BLOCK.get(), Registration.createStandardProperties()));

    public static final RegistryObject<Block> INTANGIBLE_GLASS_PASSIVE_BLOCK = BLOCKS.register("intangible_passive_glass", () -> new IntangibleGlass(EntityEnum.PASSIVE));
    public static final RegistryObject<Item> INTANGIBLE_GLASS_PASSIVE_ITEM = ITEMS.register("intangible_passive_glass", () -> new BlockItem(INTANGIBLE_GLASS_PASSIVE_BLOCK.get(), Registration.createStandardProperties()));

    public static final RegistryObject<Block> INTANGIBLE_GLASS_HOSTILE_BLOCK = BLOCKS.register("intangible_hostile_glass", () -> new IntangibleGlass(EntityEnum.HOSTILE));
    public static final RegistryObject<Item> INTANGIBLE_GLASS_HOSTILE_ITEM = ITEMS.register("intangible_hostile_glass", () -> new BlockItem(INTANGIBLE_GLASS_HOSTILE_BLOCK.get(), Registration.createStandardProperties()));

    public static final RegistryObject<Block> INTANGIBLE_GLASS_VILLAGER_BLOCK = BLOCKS.register("intangible_villager_glass", () -> new IntangibleGlass(EntityEnum.VILLAGER));
    public static final RegistryObject<Item> INTANGIBLE_GLASS_VILLAGER_ITEM = ITEMS.register("intangible_villager_glass", () -> new BlockItem(INTANGIBLE_GLASS_VILLAGER_BLOCK.get(), Registration.createStandardProperties()));

    public static final RegistryObject<Block> INTANGIBLE_GLASS_ITEM_BLOCK = BLOCKS.register("intangible_item_glass", () -> new IntangibleGlass(EntityEnum.ITEM));
    public static final RegistryObject<Item> INTANGIBLE_GLASS_ITEM_ITEM = ITEMS.register("intangible_item_glass", () -> new BlockItem(INTANGIBLE_GLASS_ITEM_BLOCK.get(), Registration.createStandardProperties()));

    public static final RegistryObject<Block> INTANGIBLE_GLASS_ITEM_DAMAGE_BLOCK = BLOCKS.register("intangible_item_damage_glass", () -> new IntangibleGlass(EntityEnum.ITEM,true));
    public static final RegistryObject<Item> INTANGIBLE_GLASS_ITEM_DAMAGE_ITEM = ITEMS.register("intangible_item_damage_glass", () -> new BlockItem(INTANGIBLE_GLASS_ITEM_DAMAGE_BLOCK.get(), Registration.createStandardProperties()));
}
