package astromness.intangibleglass.intangiglass;

import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.client.renderer.entity.layers.EnergyLayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.merchant.villager.VillagerEntity;
import net.minecraft.entity.monster.MonsterEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PigEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.DamageSource;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.world.NoteBlockEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class IntangibleGlass extends Block {

    private static final Material myMaterial = new Material(MaterialColor.AIR,false,false,false,false,false,false, PushReaction.IGNORE);

    public static final VoxelShape EMPTY = Block.makeCuboidShape(0.0D, 0.0D, 0.00D, 0.0D, 0.0D, 0.0D);
    private EntityEnum filter;
    private boolean dmgHostiles;

    public IntangibleGlass(EntityEnum filter_type) {

        super(Block.Properties.create(myMaterial)
                .hardnessAndResistance(0.3f)
                .sound(SoundType.GLASS)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)
                .setLightLevel((state) -> 15)
                .notSolid()
        );

        filter = filter_type;
        dmgHostiles = false;
    }

    public IntangibleGlass(EntityEnum filter_type, boolean damageHostiles) {

        super(Block.Properties.create(myMaterial)
                .hardnessAndResistance(0.3f)
                .sound(SoundType.GLASS)
                .harvestLevel(1)
                .harvestTool(ToolType.PICKAXE)
                .setLightLevel((state) -> 15)
                .notSolid()
        );

        filter = filter_type;
        dmgHostiles = damageHostiles;
    }

//    @Nonnull
//    @Override
//    public ActionResultType onBlockActivated(@Nonnull BlockState state, World worldIn, @Nonnull BlockPos pos, @Nonnull PlayerEntity player, @Nonnull Hand handIn, @Nonnull BlockRayTraceResult hit) {
//        if (worldIn.isRemote) {
//            return ActionResultType.SUCCESS;
//        }
//
//        ItemStack handStack = player.getHeldItem(handIn);
//        ElevatorTileEntity tile = getElevatorTile(worldIn, pos);
//        if (tile == null) {
//            return ActionResultType.FAIL;
//        }
//
//        if (isValidItem(handStack)) {
//            Block handBlock = Block.getBlockFromItem(handStack.getItem());
//            BlockState stateToApply = handBlock.getStateForPlacement(new FakeUseContext(player, handIn, hit));
//
//            // Try set camo
//            if (stateToApply != tile.getHeldState()) {
//                setCamoAndUpdate(stateToApply, tile, worldIn);
//                // If we successfully set camo, don't open the menu
//                return ActionResultType.SUCCESS;
//            }
//        }
//
//        // Remove camo
//        if (player.isCrouching() && tile.getHeldState() != null) {
//            NetworkHandler.INSTANCE.sendToServer(new RemoveCamoPacket(pos));
//            return ActionResultType.SUCCESS;
//        }
//
//        NetworkHooks.openGui((ServerPlayerEntity) player, tile, pos);
//        return ActionResultType.SUCCESS;
//    }

    @Override
    public VoxelShape getCollisionShape (BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {

        if (context.getEntity() instanceof PlayerEntity || (!(context.getEntity() instanceof PlayerEntity) && dmgHostiles) ) {
            return EMPTY;
        } else {
            switch (filter) {
                case PASSIVE:
                    if ( context.getEntity() instanceof AnimalEntity) { return EMPTY; }
                    break;
                case HOSTILE:
                    if ( context.getEntity() instanceof MonsterEntity) { return EMPTY; }
                    break;
                case VILLAGER:
                    if ( context.getEntity() instanceof VillagerEntity) { return EMPTY; }
                    break;
                case ITEM:
                    if ( context.getEntity() instanceof ItemEntity ) { return EMPTY; }
                    break;
            }
        }

        return super.getCollisionShape(state, world, pos, context);
    }

    @Override
    public void onEntityCollision(BlockState state, World worldIn, BlockPos pos, Entity entityIn) {
        if  (!( entityIn.getEntity() instanceof PlayerEntity || entityIn.getEntity() instanceof ItemEntity) && dmgHostiles ) {
            entityIn.attackEntityFrom(DamageSource.GENERIC, 10);
        }
    }
}
