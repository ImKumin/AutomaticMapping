package org.example.pseudoCode;

import org.example.Entity;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class SolutionPseudoCode {
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        Converter<Entity, String> entityToEntityIdConverter = context -> {
            Entity entity = context.getSource();
            return entity != null ? entity.getId() : null;
        };

        // My last try

//        modelMapper.typeMap(Object.class, Object.class).addMappings(mapper -> {
//            Class sourceClass = null; //TODO: HOW?
//            Class destinationClass = null; //TODO: HOW?
//
//            for (Method setMethod : destinationClass.getDeclaredMethods()) {
//                if (setMethod.getName().endsWith("Id") && setMethod.getReturnType() == String.class) {
//                    String propertyName = setMethod.getName().substring(3, setMethod.getName().length() - 2); // Remove "set" and "id"
//                    try {
//                        Method getMethod = sourceClass.getMethod("get" + propertyName, sourceClass);
//                        mapper.using(entityToEntityIdConverter).map(src -> {
//                            try {
//                                return getMethod.invoke(src);
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        }, (dest, v) -> {
//                            try {
//                                setMethod.invoke(dest, v);
//                            } catch (Exception e) {
//                                throw new RuntimeException(e);
//                            }
//                        });
//                    } catch (NoSuchMethodException e) {
//                        // Setter method not found, ignore
//                    }
//                }
//            }
//        });
//

        return modelMapper;
    }
}
