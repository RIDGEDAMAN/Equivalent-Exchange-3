package com.pahimar.ee3.core.helper;

import com.pahimar.ee3.emc.EmcRegistry;
import com.pahimar.ee3.item.OreStack;
import com.pahimar.ee3.item.WrappedStack;
import com.pahimar.ee3.item.crafting.RecipeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DebugHelper {

    public static void printOreDictionaryContents()
    {
        List<String> oreNames = Arrays.asList(OreDictionary.getOreNames());
        Collections.sort(oreNames);

        for (String oreName : oreNames)
        {
            for (ItemStack itemStack : OreDictionary.getOres(oreName))
            {
                LogHelper.debug(String.format("%s: %s", oreName, ItemHelper.toString(itemStack)));
            }
        }
    }

    public static void printOreStacksWithoutEmcValues()
    {
        List<String> oreNames = Arrays.asList(OreDictionary.getOreNames());
        Collections.sort(oreNames);

        for (String oreName : oreNames)
        {
            if (!EmcRegistry.hasEmcValue(new OreStack(oreName))) {
                LogHelper.debug(String.format("OreStack '%s' requires an EmcValue", oreName));
            }
        }
    }

    public static void printRecipeRegistryContents()
    {
        List<WrappedStack> outputStacks = new ArrayList<WrappedStack>(RecipeRegistry.getInstance().getRecipeMappings().keySet());
        Collections.sort(outputStacks);

        for (WrappedStack outputStack : outputStacks)
        {
            for (List<WrappedStack> inputStacks : RecipeRegistry.getInstance().getRecipeMappings().get(outputStack))
            {
                LogHelper.debug(String.format("%s <--- %s", outputStack, inputStacks));
            }
        }
    }
}
