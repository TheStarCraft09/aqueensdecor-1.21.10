package dev.lumareth.aqueensdecor.Block;

import com.mojang.serialization.MapCodec;
import dev.lumareth.aqueensdecor.AQueensDecor;
import dev.lumareth.aqueensdecor.world.ModDimensions;
import net.minecraft.BlockUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.InsideBlockEffectApplier;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.level.border.WorldBorder;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.portal.PortalShape;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;

public class UnderworldPortalBlock extends Block implements Portal {
    public static final EnumProperty<Direction.Axis> AXIS = BlockStateProperties.HORIZONTAL_AXIS;
    public static final MapCodec<UnderworldPortalBlock> CODEC = simpleCodec(UnderworldPortalBlock::new);
    private static final Map<Direction.Axis, VoxelShape> SHAPES = Shapes.rotateHorizontalAxis(Block.column(4.0, 16.0, 0.0, 16.0));
    private static final BlockPos PLATTFORM_CENTER = new BlockPos(0, 49, 0);

    @Override
    public MapCodec<UnderworldPortalBlock> codec() {
        return CODEC;
    }

    public UnderworldPortalBlock(BlockBehaviour.Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(AXIS, Direction.Axis.X));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(AXIS);
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext ctx) {
        return SHAPES.get(state.getValue(AXIS));
    }

    @Override
    protected void entityInside(
            BlockState blockState, Level level, BlockPos blockPos, Entity entity, InsideBlockEffectApplier insideBlockEffectApplier, boolean bl
    ) {
        if (entity.canUsePortal(false)) {
            entity.setAsInsidePortal(this, blockPos);
        }
    }

    @Override
    public TeleportTransition getPortalDestination(ServerLevel serverLevel, Entity entity, BlockPos blockPos) {
        ResourceKey<Level> resourceKey = serverLevel.dimension() == ModDimensions.UNDERWORLD_KEY ? Level.OVERWORLD : ModDimensions.UNDERWORLD_KEY;
        ServerLevel serverLevel2 = serverLevel.getServer().getLevel(resourceKey);
        if(serverLevel2 == null) {
            return null;
        }

        boolean bl = serverLevel2.dimension() == ModDimensions.UNDERWORLD_KEY;
        WorldBorder worldBorder = serverLevel2.getWorldBorder();
        double d = DimensionType.getTeleportationScale(serverLevel.dimensionType(), serverLevel2.dimensionType());
        BlockPos blockPos2 = worldBorder.clampToBounds(entity.getX() * d, entity.getY(), entity.getZ() * d);
        return this.getExitPlatform(serverLevel2);
    }

    @Nullable
    private TeleportTransition getExitPlatform(ServerLevel serverLevel){
        createObsidianPlattform(serverLevel, PLATTFORM_CENTER);
        Vec3 exitPos = new Vec3(PLATTFORM_CENTER.getX() + 0.5, PLATTFORM_CENTER.getY() + 1, PLATTFORM_CENTER.getZ() + 0.5);
        return new TeleportTransition(serverLevel, exitPos, Vec3.ZERO, 0, 0.0F, Relative.union(Relative.DELTA, Relative.ROTATION), TeleportTransition.DO_NOTHING);
    }

    private static void createObsidianPlattform(ServerLevel serverLevel, BlockPos blockPos){
        BlockPos base = blockPos.below();
        BlockPos.betweenClosedStream(base.offset(-2, 0, -2), base.offset(2, 0, 2)).forEach(pos -> {
            serverLevel.setBlockAndUpdate(pos, Blocks.OBSIDIAN.defaultBlockState());
        });
        BlockPos.betweenClosedStream(base.offset(-2, 0, -2), base.offset(2, 2, 2)).forEach(pos ->{
            serverLevel.setBlockAndUpdate(pos, Blocks.AIR.defaultBlockState());
        });
    }

    private static TeleportTransition getDimensionTransitionFromExit(
            Entity entity,
            BlockPos blockPos,
            BlockUtil.FoundRectangle foundRectangle,
            ServerLevel serverLevel,
            TeleportTransition.PostTeleportTransition postTeleportTransition
    ){
        BlockState blockState = entity.level().getBlockState(blockPos);
        Direction.Axis axis;
        Vec3 vec3;
        if (blockState.hasProperty(BlockStateProperties.HORIZONTAL_AXIS)) {
            axis = blockState.getValue(BlockStateProperties.HORIZONTAL_AXIS);
            BlockUtil.FoundRectangle foundRectangle2 = BlockUtil.getLargestRectangleAround(
                    blockPos, axis, 21, Direction.Axis.Y, 21, blockPosx -> entity.level().getBlockState(blockPosx) == blockState
            );
            vec3 = entity.getRelativePortalPosition(axis, foundRectangle2);
        } else {
            axis = Direction.Axis.X;
            vec3 = new Vec3(0.5, 0.0, 0.0);
        }

        return createDimensionTransition(serverLevel, foundRectangle, axis, vec3, entity, postTeleportTransition);
    }

    private static TeleportTransition createDimensionTransition(
            ServerLevel serverLevel,
            BlockUtil.FoundRectangle foundRectangle,
            Direction.Axis axis,
            Vec3 vec3,
            Entity entity,
            TeleportTransition.PostTeleportTransition postTeleportTransition
    ) {
        BlockPos blockPos = foundRectangle.minCorner;
        BlockState blockState = serverLevel.getBlockState(blockPos);
        Direction.Axis axis2 = blockState.getOptionalValue(BlockStateProperties.HORIZONTAL_AXIS).orElse(Direction.Axis.X);
        double d = foundRectangle.axis1Size;
        double e = foundRectangle.axis2Size;
        EntityDimensions entityDimensions = entity.getDimensions(entity.getPose());
        int i = axis == axis2 ? 0 : 90;
        double f = entityDimensions.width() / 2.0 + (d - entityDimensions.width()) * vec3.x();
        double g = (e - entityDimensions.height()) * vec3.y();
        double h = 0.5 + vec3.z();
        boolean bl = axis2 == Direction.Axis.X;
        Vec3 vec32 = new Vec3(blockPos.getX() + (bl ? f : h), blockPos.getY() + g, blockPos.getZ() + (bl ? h : f));
        Vec3 vec33 = PortalShape.findCollisionFreePosition(vec32, serverLevel, entity, entityDimensions);
        return new TeleportTransition(serverLevel, vec33, Vec3.ZERO, i, 0.0F, Relative.union(Relative.DELTA, Relative.ROTATION), postTeleportTransition);
    }
    @Override
    public Portal.Transition getLocalTransition() {
        return Portal.Transition.CONFUSION;
    }

    @Override
    public void animateTick(BlockState blockState, Level level, BlockPos blockPos, RandomSource randomSource) {
        if (randomSource.nextInt(100) == 0) {
            level.playLocalSound(
                    blockPos.getX() + 0.5,
                    blockPos.getY() + 0.5,
                    blockPos.getZ() + 0.5,
                    SoundEvents.SCULK_CATALYST_BLOOM,
                    SoundSource.BLOCKS,
                    0.5F,
                    randomSource.nextFloat() * 0.4F + 0.8F,
                    false
            );
        }

        for (int i = 0; i < 4; i++) {
            double d = blockPos.getX() + randomSource.nextDouble();
            double e = blockPos.getY() + randomSource.nextDouble();
            double f = blockPos.getZ() + randomSource.nextDouble();
            double g = (randomSource.nextFloat() - 0.5) * 0.5;
            double h = (randomSource.nextFloat() - 0.5) * 0.5;
            double j = (randomSource.nextFloat() - 0.5) * 0.5;
            int k = randomSource.nextInt(2) * 2 - 1;
            if (!level.getBlockState(blockPos.west()).is(this) && !level.getBlockState(blockPos.east()).is(this)) {
                d = blockPos.getX() + 0.5 + 0.25 * k;
                g = randomSource.nextFloat() * 2.0F * k;
            } else {
                f = blockPos.getZ() + 0.5 + 0.25 * k;
                j = randomSource.nextFloat() * 2.0F * k;
            }

            level.addParticle(new DustParticleOptions(0x1AD9CC, 1.2F), d, e, f, g, h, j);
        }
    }

    @Override
    protected ItemStack getCloneItemStack(LevelReader levelReader, BlockPos blockPos, BlockState blockState, boolean bl) {
        return ItemStack.EMPTY;
    }

    @Override
    protected BlockState rotate(BlockState blockState, Rotation rotation) {
        switch (rotation) {
            case COUNTERCLOCKWISE_90:
            case CLOCKWISE_90:
                switch ((Direction.Axis)blockState.getValue(AXIS)) {
                    case X:
                        return blockState.setValue(AXIS, Direction.Axis.Z);
                    case Z:
                        return blockState.setValue(AXIS, Direction.Axis.X);
                    default:
                        return blockState;
                }
            default:
                return blockState;
        }
    }


}
