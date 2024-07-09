package org.example;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class Main {

    // Stackoverflow issue: https://stackoverflow.com/questions/78717237/modelmapper-automatic-mapping
    public static void main(String[] args) {
        ModelMapper modelMapper = setupModelMapper();

        manualSolution(modelMapper);
        //automaticSolution(modelMapper);

        Reward reward = new Reward();
        reward.setOwner(new UserEntity("1", "John"));

        RewardResponseModel responseModel = modelMapper.map(reward, RewardResponseModel.class);
        System.out.println("Mapped: " + responseModel);
    }

    /**
     * Manual solution works
     */
    public static void manualSolution(ModelMapper modelMapper) {
        Converter<Entity, String> entityToEntityIdConverter = context -> {
            Entity entity = context.getSource();
            return entity != null ? entity.getId() : null;
        };

        modelMapper.typeMap(Reward.class, RewardResponseModel.class)
                .addMappings(mapper -> mapper.using(entityToEntityIdConverter)
                        .map(Reward::getOwner, RewardResponseModel::setOwnerId));

        // MAIN PROBLEM: In the manual solution, the developer needs to add each specific mapping here
    }

    public static void automaticSolution(ModelMapper modelMapper) {
        //TODO: Implement here an automatic solution.
        // The normal strict mapping needs to execute as well for all of the other fields.
        // (I believe that Reflection needs to be used, check pseudoCode package to see my last example)
        // Take into account that this mapping can happen in a nested class/mapping.
        // Therefore, take performance also into account, since it is costly to loop through all of the fields once again after a normal mapping.
    }

    public static ModelMapper setupModelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        return modelMapper;
    }
}
