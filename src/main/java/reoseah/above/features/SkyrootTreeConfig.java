package reoseah.above.features;

import java.util.ArrayList;

import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.types.DynamicOps;

import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;

public class SkyrootTreeConfig extends TreeFeatureConfig {
	public final SkyrootTreeShape skyrootTreeShape;

	public SkyrootTreeConfig(BlockStateProvider trunkProvider,
			BlockStateProvider leavesProvider,
			int baseHeight,
			SkyrootTreeShape skyrootTreeShape) {
		super(trunkProvider, leavesProvider, new ArrayList<>(), baseHeight);
		this.skyrootTreeShape = skyrootTreeShape;
	}

	public <T> Dynamic<T> serialize(DynamicOps<T> ops) {
		ImmutableMap<T, T> map = ImmutableMap.of(ops.createString("skyrootTreeShape"), ops.createInt(this.skyrootTreeShape.toInteger()));

		return super.serialize(ops).merge(new Dynamic<>(ops, ops.createMap(map)));
	}

	public static <T> SkyrootTreeConfig deserialize(Dynamic<T> dynamic) {
		TreeFeatureConfig base = TreeFeatureConfig.deserialize(dynamic);
		return new SkyrootTreeConfig(
				base.trunkProvider,
				base.leavesProvider,
				base.baseHeight,
				SkyrootTreeShape.fromInteger(dynamic.get("skyrootTreeShape").asInt(0)));
	}
}
