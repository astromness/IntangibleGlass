package astromness.intangibleglass.setup;

import java.util.function.Supplier;

import com.sun.corba.se.impl.activation.CommandHandler;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import astromness.intangibleglass.intangiglass.IntangibleModule;

public class ModSetup {
    private Logger logger;
    protected ItemGroup creativeTab;

    public ModSetup() {
        createTab("intangibleglass", () -> new ItemStack(IntangibleModule.INTANGIBLE_GLASS_BLOCK.get()));
    }

    public void init(FMLCommonSetupEvent e) {
        this.logger = LogManager.getLogger();
    }

    protected void createTab(String name, final Supplier<ItemStack> stack) {
        this.creativeTab = new ItemGroup(name) {
            public ItemStack createIcon() {
                return (ItemStack)stack.get();
            }
        };
    }

    public Logger getLogger() {
        return this.logger;
    }

    public ItemGroup getTab() {
        return this.creativeTab;
    }
}