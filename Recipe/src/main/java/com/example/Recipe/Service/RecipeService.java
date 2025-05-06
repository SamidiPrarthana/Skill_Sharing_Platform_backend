package com.example.Recipe.Service;

import com.example.Recipe.Repo.RecipeRepo;
import com.example.Recipe.dto.RecipeDto;
import com.example.Recipe.entity.Recipe;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RecipeService {

    @Autowired
    private RecipeRepo recipeRepo;
    @Autowired
    private ModelMapper modelMapper;
    public RecipeDto saveRecipe(RecipeDto recipeDto){
        recipeRepo.save(modelMapper.map(recipeDto, Recipe.class));
        return recipeDto;
    }

    public List<RecipeDto> getAllRecipes(){
        List<Recipe>recipeList=recipeRepo.findAll();
        return modelMapper.map(recipeList,new TypeToken<List<RecipeDto>>(){}.getType());
    }

    public RecipeDto updateRecipe(RecipeDto recipeDto){
        recipeRepo.save(modelMapper.map(recipeDto,Recipe.class));
        return recipeDto;
    }



    public boolean deleteRecipe(String id) {
        recipeRepo.deleteById(id);
        return true;
    }

    public RecipeDto getRecipeById(String id) {
        Recipe r = recipeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Recipe not found"));
        return modelMapper.map(r, RecipeDto.class);
    }

}
