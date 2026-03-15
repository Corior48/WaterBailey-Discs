package net.corior48.waterbaileydiscs.config;

import net.neoforged.neoforge.common.ModConfigSpec;

public class ModCommonConfig {
    public static final ModConfigSpec SPEC;

    static {
        ModConfigSpec.Builder builder = new ModConfigSpec.Builder();

        SPEC = builder.build();
    }
}