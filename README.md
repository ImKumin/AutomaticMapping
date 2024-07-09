This repo is a sandbox for the issue in: https://stackoverflow.com/questions/78717237/modelmapper-automatic-mapping

How can I automatically map models like the ones below?
----

```
public class Reward {
    private UserEntity owner; // UserEntity extends Entity which has a .getId()
}
```
Maps to
```
public class RewardResponseModel {
    private String ownerId;
}
```

It WORKS fine if I manually implement this:

```
Converter<Entity, String> entityToEntityIdConverter = context -> {
    Entity entity = context.getSource();
    return entity != null ? entity.getId() : null;
};

modelMapper.typeMap(Reward.class, RewardResponseModel.class)
        .addMappings(mapper -> mapper.using(entityToEntityIdConverter)
                .map(Reward::getOwner, RewardResponseModel::setOwnerId));
```

However, this type of mapping from Entity to String Id, is supposed to be done a lot of times, and I don't want to manually add each mapping like the one above. So I need to generalize a solution. Another example is like this:

```
private PlayerEntity player; -> private String playerId; // Another example
```

So basically, something like this pseudo code

```
modelMapper.typeMap(Object.class, Object.class)
        .addMappings(mapper -> mapper.using(entityToEntityIdConverter)
                .map(FIND HERE THE get FUNCTION, FIND HERE THE set FUNCTION));
```

I've tried multiple ways of achieving this with **converters and type maps using Reflection**, but without success. 

One of my last tries is here (mostly pseudocode): https://pastebin.com/gy5g5fXa

This seems so doable, but I've been having no luck. Any idea of how I can achieve this?
