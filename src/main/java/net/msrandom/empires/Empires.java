package net.msrandom.empires;

import net.fabricmc.api.ModInitializer;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.msrandom.empires.item.EmpiresItems;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

public class Empires implements ModInitializer {
    private static final Logger LOGGER = LogManager.getLogger(Empires.class.getSimpleName());
    public static final String MOD_ID = "empires";

    @Override
    public void onInitialize() {
        register(EmpiresItems.class, Registry.ITEM);
    }

    @SuppressWarnings("unchecked")
    private <T> void register(Class<?> cls, Registry<T> registry) {
        EmpiresRegistry annotation = cls.getAnnotation(EmpiresRegistry.class);
        if (annotation != null) {
            Class<?> type = annotation.value();
            for (Field field : cls.getDeclaredFields()) {
                if (type.isAssignableFrom(field.getType()) && Modifier.isStatic(field.getModifiers())) {
                    String name = field.getName().toLowerCase();
                    field.setAccessible(true);
                    try {
                        Registry.register(registry, new Identifier(MOD_ID, name), (T) field.get(null));
                    } catch (IllegalAccessException exception) {
                        LOGGER.error("Failed to register " + name + " in registry " + registry.getKey().getValue(), exception);
                    }
                }
            }
        }
    }
}
