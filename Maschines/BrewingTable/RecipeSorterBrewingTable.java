package assets.brewing_a_beer_v2.Maschines.BrewingTable;

import java.util.Comparator;
import net.minecraft.item.crafting.IRecipe;

public class RecipeSorterBrewingTable implements Comparator
{
final CraftingManagerBrewingTable craftingManagerIngredientsTable;

public RecipeSorterBrewingTable(CraftingManagerBrewingTable par1craftingManagerIngredientsTable)
{
         this.craftingManagerIngredientsTable = par1craftingManagerIngredientsTable;
}

public int compareRecipes(IRecipe par1IRecipe, IRecipe par2IRecipe)
{
         return par1IRecipe instanceof ShapelessRecipesBrewingTable && par2IRecipe instanceof ShapedRecipesBrewingTable ? 1 : (par2IRecipe instanceof ShapelessRecipesBrewingTable && par1IRecipe instanceof ShapedRecipesBrewingTable ? -1 : (par2IRecipe.getRecipeSize() < par1IRecipe.getRecipeSize() ? -1 : (par2IRecipe.getRecipeSize() > par1IRecipe.getRecipeSize() ? 1 : 0)));
}

public int compare(Object par1Obj, Object par2Obj)
{
         return this.compareRecipes((IRecipe)par1Obj, (IRecipe)par2Obj);
}
}